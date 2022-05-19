package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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

    Page<Map<String, Object>> getOrderCount(Page<?> page);

    @Select("select count(*) from orders " +
            "where contract_number = #{contract_number} " +
            "and product_model_id = #{product_model_id} " +
            "and salesman_id = #{salesman_id};")
    int select(
            @Param("contract_number") String contract_number,
            @Param("product_model_id") int product_model_id,
            @Param("salesman_id") int salesman_id);

    @Delete("delete from orders " +
            "where contract_number = #{contract_number} " +
            "and product_model_id = #{product_model_id} " +
            "and salesman_id = #{salesman_id};")
    void delete(
            @Param("contract_number") String contract_number,
            @Param("product_model_id") int product_model_id,
            @Param("salesman_id") int salesman_id);

    @Update("update orders " +
            "set quantity= #{quantity} ,estimated_delivery_date= #{estimated_delivery_date}, lodgement_date = #{lodgement_date} " +
            "where contract_number = #{contract_number} and product_model_id = #{product_model_id} and salesman_id = #{salesman_id}; ")
    void update(
            @Param("quantity") int quantity,
            @Param("estimated_delivery_date") LocalDate estimated_delivery_date,
            @Param("lodgement_date") LocalDate lodgement_date,
            @Param("contract_number") String contract_number,
            @Param("product_model_id") int product_model_id,
            @Param("salesman_id") int salesman_id);

    @Select("select quantity from orders " +
            "where contract_number = #{contract_number} " +
            "and product_model_id = #{product_model_id} " +
            "and salesman_id = #{salesman_id};")
    int selectQuantity(
            @Param("contract_number") String contract_number,
            @Param("product_model_id") int product_model_id,
            @Param("salesman_id") int salesman_id);

    Map<String, Object> getDeleteOrder(
            @Param("contract_number") String contract_number,
            @Param("salesman_id") int salesman_id,
            @Param("seq") int seq
    );
}
