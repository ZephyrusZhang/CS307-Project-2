package edu.sustech.cs307.controller;

import edu.sustech.cs307.service.IInventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public InventoryController(IInventoryService iInventoryService) {
        this.iInventoryService = iInventoryService;
    }

    @GetMapping("/stockIn")
    public boolean stockIn() {
        return iInventoryService.stockIn("src/main/resources/static/testcases/task1_in_stoke_test_data_publish.csv");
    }
}
