package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.Center;
import edu.sustech.cs307.entity.Enterprise;
import edu.sustech.cs307.mapper.CenterMapper;
import edu.sustech.cs307.mapper.EnterpriseMapper;
import edu.sustech.cs307.service.IEnterpriseService;
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
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {

    private final CenterMapper centerMapper;

    public EnterpriseServiceImpl(CenterMapper centerMapper) {
        this.centerMapper = centerMapper;
    }

    @Override
    public boolean init() {
        List<Enterprise> enterpriseList = new LinkedList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/static/data/enterprise.csv");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Enterprise enterprise = new Enterprise();
                QueryWrapper<Center> wrapperCenter = new QueryWrapper<>();
                wrapperCenter.eq("name", line[4]);
                enterprise.setId(Integer.parseInt(line[0]));
                enterprise.setName(line[1]);
                enterprise.setCountry(line[2]);
                enterprise.setCity(line[3]);
                enterprise.setSupplyCenterId(centerMapper.selectOne(wrapperCenter).getId());
                enterprise.setIndustry(line[5]);
                enterpriseList.add(enterprise);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return this.saveBatch(enterpriseList);
    }

    @Override
    public Center getCenter(Enterprise enterprise) {
        QueryWrapper<Center> wrapper = new QueryWrapper<Center>().eq("id", enterprise.getSupplyCenterId());
        return centerMapper.selectOne(wrapper);
    }
}
