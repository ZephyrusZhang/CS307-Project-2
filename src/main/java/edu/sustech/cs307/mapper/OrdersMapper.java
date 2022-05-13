package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
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
public interface OrdersMapper extends BaseMapper<Orders> {

    @Insert("insert into orders (contract_number, enterprise_id, product_model_id, quantity, estimated_delivery_date, lodgement_date, salesman_id) " +
            "values (#{contractNumber}, #{enterpriseId}, #{productModelId}, #{quantity}, #{estimatedDeliveryDate}, #{lodgementDate}, #{salesmanId});")
    void insert(@Param("contractNumber") String contractNumber,
                @Param("enterpriseId") int enterpriseId,
                @Param("productModelId") int productModelId,
                @Param("quantity") int quantity,
                @Param("estimatedDeliveryDate") LocalDate estimatedDeliveryDate,
                @Param("lodgementDate") LocalDate lodgementDate,
                @Param("salesmanId") int salesmanId);

    Map<String, Integer> getOrderCount();

}
