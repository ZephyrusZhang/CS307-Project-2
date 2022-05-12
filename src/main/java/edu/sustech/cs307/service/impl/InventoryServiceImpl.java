package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.*;
import edu.sustech.cs307.mapper.*;
import edu.sustech.cs307.service.IInventoryService;
import edu.sustech.cs307.service.IStaffService;
import edu.sustech.cs307.util.Util;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    private final StaffMapper staffMapper;
    private final CenterMapper centerMapper;
    private final ModelMapper modelMapper;
    private final CenterRecordMapper centerRecordMapper;
    private final InventoryMapper inventoryMapper;
    private final IStaffService iStaffService;

    public InventoryServiceImpl(StaffMapper staffMapper,
                                CenterMapper centerMapper,
                                ModelMapper modelMapper,
                                IStaffService iStaffService,
                                CenterRecordMapper centerRecordMapper,
                                InventoryMapper inventoryMapper) {
        this.staffMapper = staffMapper;
        this.centerMapper = centerMapper;
        this.modelMapper = modelMapper;
        this.iStaffService = iStaffService;
        this.centerRecordMapper = centerRecordMapper;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public boolean stockIn(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            List<String> allCenterName = centerMapper.selectList(null).stream().map(Center::getName).toList();
            List<String> allModelName = modelMapper.selectList(null).stream().map(Model::getModelName).toList();
            List<String> allStaffNumber = staffMapper.selectList(null).stream().map(Staff::getNumber).toList();
            while ((line = reader.readNext()) != null) {
                QueryWrapper<Staff> wrapperStaff = new QueryWrapper<Staff>().eq("number", line[3]);
                Staff staff = staffMapper.selectOne(wrapperStaff);
                if (staff == null) continue;
                if (!iStaffService.getSupplyCenter(staff).getName().equals(line[1])) continue;
                if (!staff.getType().equals("Supply Staff")) continue;
                if (!allCenterName.contains(line[1])) continue;
                if (!allModelName.contains(line[2])) continue;
                if (!allStaffNumber.contains(line[3])) continue;

                QueryWrapper<Model> wrapperModel = new QueryWrapper<Model>().eq("model_name", line[2]);
                QueryWrapper<Center> wrapperCenter = new QueryWrapper<Center>().eq("name", line[1]);
                int product_model_id = modelMapper.selectOne(wrapperModel).getId();
                int supply_center_id = centerMapper.selectOne(wrapperCenter).getId();
                int staff_id = staffMapper.selectOne(wrapperStaff).getId();

                CenterRecord centerRecord = new CenterRecord();
                centerRecord.setSupplyCenterId(supply_center_id);
                centerRecord.setProductModelId(product_model_id);
                centerRecord.setStaffId(staff_id);
                centerRecord.setDate(LocalDate.parse(Util.formatDate(line[4].replaceAll("/", "-"))));
                centerRecord.setPurchasePrice(Integer.parseInt(line[5]));
                centerRecord.setQuantity(Integer.parseInt(line[6]));
                centerRecordMapper.addCenterRecord(centerRecord);

                Inventory inventory = new Inventory();
                inventory.setSupplyCenterId(supply_center_id);
                inventory.setProductModelId(product_model_id);
                inventory.setCount(Integer.parseInt(line[6]));
                inventoryMapper.addInventory(inventory);

                centerMapper.updateExpenditure(Integer.parseInt(line[6]) * Integer.parseInt(line[5]), supply_center_id);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
