package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
