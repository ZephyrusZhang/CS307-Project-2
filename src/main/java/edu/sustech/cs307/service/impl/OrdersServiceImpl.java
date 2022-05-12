package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.*;
import edu.sustech.cs307.mapper.*;
import edu.sustech.cs307.service.IEnterpriseService;
import edu.sustech.cs307.service.IOrdersService;
import edu.sustech.cs307.util.Util;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    private final IEnterpriseService iEnterpriseService;
    private final EnterpriseMapper enterpriseMapper;
    private final ModelMapper modelMapper;
    private final InventoryMapper inventoryMapper;
    private final StaffMapper staffMapper;
    private final ContractMapper contractMapper;
    private final OrdersMapper ordersMapper;
    private final CenterMapper centerMapper;

    public OrdersServiceImpl(IEnterpriseService iEnterpriseService,
                             EnterpriseMapper enterpriseMapper,
                             ModelMapper modelMapper,
                             InventoryMapper inventoryMapper,
                             StaffMapper staffMapper,
                             ContractMapper contractMapper,
                             OrdersMapper ordersMapper,
                             CenterMapper centerMapper) {
        this.iEnterpriseService = iEnterpriseService;
        this.enterpriseMapper = enterpriseMapper;
        this.modelMapper = modelMapper;
        this.inventoryMapper = inventoryMapper;
        this.staffMapper = staffMapper;
        this.contractMapper = contractMapper;
        this.ordersMapper = ordersMapper;
        this.centerMapper = centerMapper;
    }

    @Override
    public boolean placeOrder(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                int requiredQuantity = Integer.parseInt(line[3]);
                Enterprise enterprise = enterpriseMapper.selectByName(line[1]);
                Center center = iEnterpriseService.getCenter(enterprise);
                Model model = modelMapper.selectByName(line[2]);
                Inventory inventory = inventoryMapper.selectByCenterIdModelId(center.getId(), model.getId());
                Staff salesman = staffMapper.selectByNumber(line[8]);
                Staff manager = staffMapper.selectByNumber(line[4]);

                if (inventory == null || requiredQuantity > inventory.getCount()) continue;
                if (!salesman.getType().equals("Salesman")) continue;

                contractMapper.insert(line[0], Util.strToLocalDate(line[5]), manager.getId(), line[9]);

                ordersMapper.insert(line[0], enterprise.getId(), model.getId(), requiredQuantity,
                        Util.strToLocalDate(line[6]), Util.strToLocalDate(line[7]), salesman.getId());

                inventoryMapper.updateInventoryNum(requiredQuantity, center.getId(), model.getId());

                modelMapper.updateSalesNum(requiredQuantity, model.getId());

                centerMapper.updateRevenue(requiredQuantity * model.getUnitPrice(), center.getId());
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
