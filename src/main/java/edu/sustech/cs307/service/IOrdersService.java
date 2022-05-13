package edu.sustech.cs307.service;

import edu.sustech.cs307.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
public interface IOrdersService extends IService<Orders> {

    boolean placeOrder(String path);
    boolean updateOrder(String path);
    boolean deleteOrder(String path);

}
