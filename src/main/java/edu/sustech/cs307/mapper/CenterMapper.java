package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Center;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.Map;

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

    @Update("update center " +
            "set expenditure = #{expenditure}, " +
            "revenue = #{revenue} " +
            "where name = #{name}")
    boolean updateCenterByName(Center center);

    @Delete("delete from center where name = #{name}")
    int deleteByName(@Param("name") String name);

    Page<Map<String, Object>> selectByNamePage(Page<?> page, @Param("name") String name);

    Page<Map<String, Object>> listPage(Page<?> page);

}
