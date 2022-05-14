package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    @Select("select * from enterprise where name = #{name}")
    Enterprise selectByName(@Param("name") String name);

    Page<Map<String, Object>> selectPage(Page<?> page);

    Page<Map<String, Object>> selectByNamePage(Page<?> page, @Param("name") String name);

    @Update("update enterprise " +
            "set country #{country}, " +
            "city = #{city}, " +
            "supply_center_id = #{supplyCenterId}, " +
            "industry = #{industry} " +
            "where name = #{name}")
    int updateByEnterpriseName(Enterprise enterprise);

    @Delete("delete from enterprise where name = #{enterpriseName}")
    boolean deleteByEnterpriseName(@Param("enterpriseName") String enterpriseName);

}
