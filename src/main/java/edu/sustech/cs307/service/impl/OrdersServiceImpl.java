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
import java.time.LocalDate;
import java.util.Map;

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
    public boolean updateOrder(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {

                Model model = modelMapper.selectByName(line[1]);
                Staff staff = staffMapper.selectByNumber(line[2]);
                int quantity = Integer.parseInt(line[3]);
                LocalDate estimate_delivery_date = LocalDate.parse(line[4]);
                LocalDate lodgement_date = LocalDate.parse(line[5]);

                int number=ordersMapper.select(line[0],model.getId(),staff.getId());

                if(number!=0){
                    int quantity_pre = ordersMapper.selectQuantity(line[0],model.getId(),staff.getId());
                    int change = quantity-quantity_pre;

                    modelMapper.updateSalesNum(change,model.getId());
                    inventoryMapper.updateInventoryNum(change,staff.getSupplyCenterId(),model.getId());

                    if(quantity==0){
                        ordersMapper.delete(line[0],model.getId(),staff.getId());
                    }
                    else{
                        ordersMapper.update(quantity,estimate_delivery_date,lodgement_date,line[0],model.getId(),staff.getId());

                    }
                }

            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteOrder(String path){
        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Staff staff = staffMapper.selectByNumber(line[1]);
                int seq = Integer.parseInt(line[2]);

                Map<String, Object> map = ordersMapper.getDeleteOrder(line[0], staff.getId(), seq);
                if (map != null) {

                    String contract_number = (String) map.get("contract_number");
                    int product_model_id = (int) map.get("product_model_id");
                    int salesman_id = (int) map.get("salesman_id");


                    int quantity_pre = ordersMapper.selectQuantity(contract_number, product_model_id, salesman_id);
                    int change = -quantity_pre;
                    modelMapper.updateSalesNum(change, product_model_id);
                    inventoryMapper.updateInventoryNum(change, staff.getSupplyCenterId(), product_model_id);
                    ordersMapper.delete(line[0], product_model_id, staff.getId());


                }
            }


        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}


