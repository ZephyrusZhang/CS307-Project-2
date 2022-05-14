package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-14
 */
public interface UsersMapper extends BaseMapper<Users> {

    @Select("select * from users where username = #{username}")
    Users selectByName(@Param("username") String username);

}
