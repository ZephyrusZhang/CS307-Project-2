package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

    void addInventory(Inventory inventory);

    @Select("select * from inventory where supply_center_id = #{supplyCenterId} and product_model_id = #{productModelId}")
    Inventory selectByCenterIdModelId(@Param("supplyCenterId") int supplyCenterId, @Param("productModelId") int productModelId);

    @Update("update inventory set count = count - #{soldNum} where supply_center_id = #{supplyCenterId} and product_model_id = #{productModelId}")
    void updateInventoryNum(@Param("soldNum") int soldNum, @Param("supplyCenterId") int supplyCenterId, @Param("productModelId") int productModelId);

}
