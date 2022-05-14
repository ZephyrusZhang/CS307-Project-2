package edu.sustech.cs307.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Enterprise;
import edu.sustech.cs307.mapper.EnterpriseMapper;
import edu.sustech.cs307.service.IEnterpriseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
@RequestMapping("/enterprise")
public class EnterpriseController {

    private final IEnterpriseService enterpriseService;
    private final EnterpriseMapper enterpriseMapper;

    public EnterpriseController(IEnterpriseService enterpriseService,
                                EnterpriseMapper enterpriseMapper) {
        this.enterpriseService = enterpriseService;
        this.enterpriseMapper = enterpriseMapper;
    }

    @GetMapping("/listEnterprise")
    public List<Enterprise> listEnterprise() {
        return this.enterpriseService.list();
    }

    @GetMapping("/selectPage")
    public Result<?> selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "20") Integer pageSize,
                                @RequestParam(defaultValue = "") String name) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        return Result.success(enterpriseMapper.selectPage(page));
    }

}
