package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.*;
import edu.sustech.cs307.mapper.*;
import edu.sustech.cs307.service.IInventoryService;
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

    public InventoryServiceImpl(StaffMapper staffMapper,
                                CenterMapper centerMapper,
                                ModelMapper modelMapper,
                                CenterRecordMapper centerRecordMapper,
                                InventoryMapper inventoryMapper) {
        this.staffMapper = staffMapper;
        this.centerMapper = centerMapper;
        this.modelMapper = modelMapper;
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
                Staff staff = staffMapper.selectByNumber(line[3]);
                if (!line[1].equals(staffMapper.getCenterName(line[3]))) continue;
                if (staff == null || !staff.getType().equals("Supply Staff")) continue;
                if (!allCenterName.contains(line[1])) continue;
                if (!allModelName.contains(line[2])) continue;
                if (!allStaffNumber.contains(line[3])) continue;

                int product_model_id = modelMapper.selectByName(line[2]).getId();
                int supply_center_id = centerMapper.selectByName(line[1]).getId();
                int staff_id = staff.getId();

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
