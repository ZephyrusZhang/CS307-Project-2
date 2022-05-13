package edu.sustech.cs307.controller;

import edu.sustech.cs307.mapper.InventoryMapper;
import edu.sustech.cs307.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final IInventoryService iInventoryService;
    private final InventoryMapper inventoryMapper;

    public InventoryController(IInventoryService iInventoryService, InventoryMapper inventoryMapper) {
        this.iInventoryService = iInventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    @GetMapping("/stockIn")
    public boolean stockIn() {
        return iInventoryService.stockIn("src/main/resources/static/testcases/task1_in_stoke_test_data_publish.csv");
    }

    @GetMapping("/getAvgStockByCenter")
    public List<Map<String, Object>> getAvgStockByCenter() {
        List<Map<String, Object>> maps = inventoryMapper.getAvgStockByCenter();
        maps.forEach(map -> System.out.printf("centerName=%s, avg=%.1f\n", map.get("centerName"), (double) map.get("avg")));
        return maps;
    }
}
