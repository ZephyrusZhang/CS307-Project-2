package edu.sustech.cs307.controller;

import edu.sustech.cs307.service.IInventoryService;
import edu.sustech.cs307.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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
