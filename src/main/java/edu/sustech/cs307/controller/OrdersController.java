package edu.sustech.cs307.controller;

import edu.sustech.cs307.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final IOrdersService iOrdersService;

    public OrdersController(IOrdersService iOrdersService) {
        this.iOrdersService = iOrdersService;
    }

    @GetMapping("/placeOrder")
    public boolean placeOrder() {
        return iOrdersService.placeOrder("src/main/resources/static/testcases/task2_test_data_publish.csv");
    }

}
