package edu.sustech.cs307.service;

import edu.sustech.cs307.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
public interface IStaffService extends IService<Staff> {

    boolean init();

}
