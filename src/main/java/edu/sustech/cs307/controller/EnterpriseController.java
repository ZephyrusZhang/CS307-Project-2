package edu.sustech.cs307.controller;

import edu.sustech.cs307.entity.Enterprise;
import edu.sustech.cs307.service.IEnterpriseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    public EnterpriseController(IEnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/init")
    public boolean init() {
        return enterpriseService.init();
    }

    @GetMapping("/listEnterprise")
    public List<Enterprise> listEnterprise() {
        return this.enterpriseService.list();
    }

}
