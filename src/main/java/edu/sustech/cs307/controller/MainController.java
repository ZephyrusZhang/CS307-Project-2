package edu.sustech.cs307.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.mapper.ModelMapper;
import edu.sustech.cs307.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    private final ICenterService iCenterService;
    private final IEnterpriseService iEnterpriseService;
    private final IModelService iModelService;
    private final IStaffService iStaffService;
    private final IInventoryService iInventoryService;
    private final IOrdersService iOrdersService;

    private final ModelMapper modelMapper;

    public MainController(ICenterService iCenterService,
                          IEnterpriseService iEnterpriseService,
                          IModelService iModelService,
                          IStaffService iStaffService,
                          IInventoryService iInventoryService,
                          IOrdersService iOrdersService,
                          ModelMapper modelMapper) {
        this.iCenterService = iCenterService;
        this.iEnterpriseService = iEnterpriseService;
        this.iModelService = iModelService;
        this.iStaffService = iStaffService;
        this.iInventoryService = iInventoryService;
        this.iOrdersService = iOrdersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/init")
    public boolean init() {
        boolean isCenterInit = iCenterService.init();
        boolean isStaffInit = iStaffService.init();
        boolean isEnterpriseInit = iEnterpriseService.init();
        boolean isModelInit = iModelService.init();
        return isCenterInit && isStaffInit && isEnterpriseInit && isModelInit;
    }

    @RequestMapping("/stockIn")
    public Result<?> stockIn(@RequestParam("file") MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\stockInData.csv";
        try {
            FileUtil.writeBytes(file.getBytes(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(iInventoryService.stockIn("src/main/resources/files/stockInData.csv"));
    }

    @RequestMapping("/placeOrder")
    public Result<?> placeOrder(@RequestParam("file") MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\placeOrderData.csv";
        try {
            FileUtil.writeBytes(file.getBytes(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(iOrdersService.placeOrder("src/main/resources/files/placeOrderData.csv"));
    }

    @RequestMapping("/updateOrder")
    public Result<?> updateOrder(@RequestParam("file") MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\updateOrderData.csv";
        try {
            FileUtil.writeBytes(file.getBytes(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(iOrdersService.updateOrder("src/main/resources/files/updateOrderData.csv"));
    }

    @RequestMapping("/deleteOrder")
    public Result<?> deleteOrder(@RequestParam("file") MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\deleteOrderData.csv";
        try {
            FileUtil.writeBytes(file.getBytes(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(iOrdersService.deleteOrder("src/main/resources/files/deleteOrderData.csv"));
    }

    @GetMapping("/getProductByNumber")
    public Result<?> getProductByNumber(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "20") Integer pageSize,
                                        @RequestParam(defaultValue = "") String number) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(number)) {
            return Result.success(modelMapper.getProductByNumberPage(page, number));
        } else {
            return Result.success();
        }
    }

}
