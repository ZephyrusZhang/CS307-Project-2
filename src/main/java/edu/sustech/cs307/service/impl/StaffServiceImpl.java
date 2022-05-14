package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.Center;
import edu.sustech.cs307.entity.Staff;
import edu.sustech.cs307.mapper.CenterMapper;
import edu.sustech.cs307.mapper.StaffMapper;
import edu.sustech.cs307.service.IStaffService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    private final CenterMapper centerMapper;

    public StaffServiceImpl(CenterMapper centerMapper) {
        this.centerMapper = centerMapper;
    }

    @Override
    public boolean init() {
        List<Staff> staffList = new LinkedList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/static/data/staff.csv");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Staff staff = new Staff();
                QueryWrapper<Center> wrapperCenter = new QueryWrapper<>();
                wrapperCenter.eq("name", line[5]);
//                staff.setId(Integer.parseInt(line[0]));
                staff.setName(line[1]);
                staff.setAge(Integer.parseInt(line[2]));
                staff.setGender(line[3]);
                staff.setNumber(line[4]);
                staff.setMobileNumber(line[6]);
                staff.setType(line[7]);
                staff.setSupplyCenterId(centerMapper.selectOne(wrapperCenter).getId());
                staffList.add(staff);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return this.saveBatch(staffList);
    }

    @Override
    public Center getSupplyCenter(Staff staff) {
        QueryWrapper<Center> wrapper = new QueryWrapper<Center>().eq("id", staff.getSupplyCenterId());
        return centerMapper.selectOne(wrapper);
    }
}
