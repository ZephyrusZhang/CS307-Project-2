package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.Contract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

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

}
