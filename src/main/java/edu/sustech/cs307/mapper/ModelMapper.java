package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Model;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface ModelMapper extends BaseMapper<Model> {

    @Select("select * from model where model_name = #{modelName}")
    Model selectByName(@Param("modelName") String modelName);

    @Update("update model set sales = sales + #{soldNum} where id = #{productModelId}")
    void updateSalesNum(@Param("soldNum") int soldNum, @Param("productModelId") int productModelId);

    Map<String, Integer> getNeverSoldProductCount();

    List<Map<String, Object>> getFavoriteProductModel();

    List<Map<String, Object>> getProductByNumber(@Param("productName") String productName);

}
