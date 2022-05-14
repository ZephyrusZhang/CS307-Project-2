package edu.sustech.cs307.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Enterprise;
import edu.sustech.cs307.mapper.EnterpriseMapper;
import edu.sustech.cs307.service.IEnterpriseService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/show")
    public Result<?> selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "20") Integer pageSize,
                                @RequestParam(defaultValue = "") String enterpriseName) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(enterpriseName)) {
            return Result.success(enterpriseMapper.selectByNamePage(page, enterpriseName));
        } else {
            return Result.success(enterpriseMapper.selectPage(page));
        }
    }

    @PostMapping("/addOneEnterprise")
    public Result<?> addOneEnterprise(@RequestBody Enterprise enterprise) {
        if (enterpriseMapper.insert(enterprise) == 1) {
            return Result.success();
        } else {
            return Result.error("233", "Insert failed");
        }
    }

    @DeleteMapping("/delete/{enterpriseName}")
    public boolean deleteByEnterpriseName(@PathVariable String enterpriseName) {
        return enterpriseMapper.deleteByEnterpriseName(enterpriseName);
    }

    @PutMapping("/updateEnterprise")
    public int updateEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseMapper.updateByEnterpriseName(enterprise);
    }

}
