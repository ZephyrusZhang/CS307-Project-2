package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Inventory;
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
public interface InventoryMapper extends BaseMapper<Inventory> {

    @Insert("insert into inventory (supply_center_id, product_model_id, count) " +
            "values (#{supplyCenterId}, #{productModelId}, #{count}) " +
            "on conflict (supply_center_id, product_model_id) do update set count = excluded.count + inventory.count;")
    void addInventory(Inventory inventory);

    @Select("select * from inventory where supply_center_id = #{supplyCenterId} and product_model_id = #{productModelId}")
    Inventory selectByCenterIdModelId(@Param("supplyCenterId") int supplyCenterId, @Param("productModelId") int productModelId);

    @Update("update inventory set count = count - #{soldNum} where supply_center_id = #{supplyCenterId} and product_model_id = #{productModelId}")
    void updateInventoryNum(@Param("soldNum") int soldNum, @Param("supplyCenterId") int supplyCenterId, @Param("productModelId") int productModelId);

    Page<Map<String, Object>> getAvgStockByCenter(Page<?> page);

}
