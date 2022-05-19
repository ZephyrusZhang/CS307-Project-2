package edu.sustech.cs307.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.mapper.ContractMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractMapper contractMapper;

    public ContractController(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }

    @GetMapping("/getContractCount")
    public Result<?> getContractCount(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "20") Integer pageSize,
                                      @RequestParam(defaultValue = "") String type) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        return Result.success(contractMapper.getContractCount(page));
    }

}
