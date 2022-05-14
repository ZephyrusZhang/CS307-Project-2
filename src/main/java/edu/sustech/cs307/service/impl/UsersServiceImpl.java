package edu.sustech.cs307.service.impl;

import edu.sustech.cs307.entity.Users;
import edu.sustech.cs307.mapper.UsersMapper;
import edu.sustech.cs307.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-14
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
