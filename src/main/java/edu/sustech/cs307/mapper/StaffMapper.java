package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

    @Select("select * from staff where number = #{number}")
    Staff selectByNumber(@Param("number") String number);

    @Select("select c.name as center_name from staff " +
            "join center c on c.id = staff.supply_center_id " +
            "where number = #{number}")
    String getCenterName(@Param("number") String number);

    List<Map<String, Object>> getAllStaffCount();

}
