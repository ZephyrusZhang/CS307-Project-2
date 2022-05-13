package edu.sustech.cs307.controller;

import edu.sustech.cs307.mapper.OrdersMapper;
import edu.sustech.cs307.service.IOrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/orders")
public class OrdersController {

    private final IOrdersService iOrdersService;
    private final OrdersMapper ordersMapper;

    public OrdersController(IOrdersService iOrdersService, OrdersMapper ordersMapper) {
        this.iOrdersService = iOrdersService;
        this.ordersMapper = ordersMapper;
    }

    @GetMapping("/placeOrder")
    public boolean placeOrder() {
        return iOrdersService.placeOrder("src/main/resources/static/testcases/task2_test_data_publish.csv");
    }

    @GetMapping("/getOrderCount")
    public Map<String, Integer> getOrderCount() {
        Map<String, Integer> map = ordersMapper.getOrderCount();
        System.out.printf("orderCount=%d\n", map.get("count"));
        return map;
    }

}
