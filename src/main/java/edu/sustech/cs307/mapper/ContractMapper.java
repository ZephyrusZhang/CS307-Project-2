package edu.sustech.cs307.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.entity.Contract;
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
public interface ContractMapper extends BaseMapper<Contract> {

    @Insert("insert into contract (contract_number, contract_date, contract_manager_id, contract_type)" +
            "values (#{contractNumber}, #{contractDate}, #{contractManagerId}, #{contractType}) on conflict do nothing;")
    void insert(@Param("contractNumber") String contractNumber, @Param("contractDate") LocalDate date,
                @Param("contractManagerId") int contractManagerId, @Param("contractType") String contractType);

    Page<Map<String, Object>> getContractCount(Page<?> page);

    Page<Map<String, Object>> getContractInfo(Page<?> page, @Param("contract_number") String contract_number);

    Page<Map<String, Object>> getOrderInfo(Page<?> page, @Param("contract_number") String contract_number);

}
