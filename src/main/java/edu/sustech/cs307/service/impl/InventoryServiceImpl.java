package edu.sustech.cs307.service.impl;

import edu.sustech.cs307.entity.Inventory;
import edu.sustech.cs307.mapper.InventoryMapper;
import edu.sustech.cs307.service.IInventoryService;
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
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

}
