package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Center;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int updateExpenditure(@Param("expenditure") int expenditure, @Param("id") int id);

}
