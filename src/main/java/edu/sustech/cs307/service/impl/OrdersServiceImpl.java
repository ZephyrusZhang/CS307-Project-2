package edu.sustech.cs307.service.impl;

import edu.sustech.cs307.entity.Orders;
import edu.sustech.cs307.mapper.OrdersMapper;
import edu.sustech.cs307.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
