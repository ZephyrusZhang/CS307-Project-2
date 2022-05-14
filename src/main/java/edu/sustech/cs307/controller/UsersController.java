package edu.sustech.cs307.controller;

import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Users;
import edu.sustech.cs307.mapper.UsersMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-14
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersMapper usersMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Users user) {
        Users res = usersMapper.selectByName(user.getUsername());
        if (res == null) {
            return Result.error("114", "User \"" + user.getUsername() + "\" doesn't exit");
        } else if (!user.getPassword().equals(res.getPassword())) {
            return Result.error("514", "Wrong password");
        }
        return Result.success();
    }

}
