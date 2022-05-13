package edu.sustech.cs307.controller;

import edu.sustech.cs307.mapper.ContractMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Map<String, Integer> getContractCount() {
        Map<String, Integer> map = contractMapper.getContractCount();
        System.out.printf("count=%d\n", map.get("count"));
        return map;
    }

}
