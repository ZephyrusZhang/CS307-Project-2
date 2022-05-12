package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Center;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Mapper
public interface CenterMapper extends BaseMapper<Center> {

    @Update("update center set expenditure = expenditure + #{expenditure} where id = #{id}")
    void updateExpenditure(@Param("expenditure") int expenditure, @Param("id") int id);

    @Update("update center set revenue = revenue + #{money} where id = #{id}")
    void updateRevenue(@Param("money") int money, @Param("id") int id);

    @Select("select * from center where name = #{name}")
    Center selectByName(@Param("name") String name);

}
