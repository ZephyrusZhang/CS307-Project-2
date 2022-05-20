package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Model;
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
public interface ModelMapper extends BaseMapper<Model> {

    @Select("select * from model where model_name = #{modelName}")
    Model selectByName(@Param("modelName") String modelName);

    @Update("update model set sales = sales + #{soldNum} where id = #{productModelId}")
    void updateSalesNum(@Param("soldNum") int soldNum, @Param("productModelId") int productModelId);

    Page<Map<String, Object>> getNeverSoldProductCount(Page<?> page);

    Page<Map<String, Object>> getFavoriteProductModel(Page<?> page);

    List<Map<String, Object>> getProductByNumber(@Param("productName") String productName);

    Page<Map<String, Object>> selectByModelNamePage(Page<?> page, @Param("modelName") String modelName);

    Page<Map<String, Object>> selectPage(Page<?> page);

    Page<Map<String, Object>> getProductByNumberPage(Page<?> page, @Param("number") String number);

    @Delete("delete from model " +
            "where model_name = #{modelName}")
    boolean deleteByModelName(@Param("modelName") String modelName);

    @Update("update model " +
            "set number = #{number}, " +
            "product_name = #{productName}, " +
            "unit_price = #{unitPrice}, " +
            "sales = #{sales} " +
            "where model_name = #{modelName}")
    int updateByModelName (Model model);

}
