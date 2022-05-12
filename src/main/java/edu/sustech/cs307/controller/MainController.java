package edu.sustech.cs307.controller;

import edu.sustech.cs307.service.ICenterService;
import edu.sustech.cs307.service.IEnterpriseService;
import edu.sustech.cs307.service.IModelService;
import edu.sustech.cs307.service.IStaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    private final ICenterService iCenterService;
    private final IEnterpriseService iEnterpriseService;
    private final IModelService iModelService;
    private final IStaffService iStaffService;

    public MainController(ICenterService iCenterService,
                          IEnterpriseService iEnterpriseService,
                          IModelService iModelService,
                          IStaffService iStaffService) {
        this.iCenterService = iCenterService;
        this.iEnterpriseService = iEnterpriseService;
        this.iModelService = iModelService;
        this.iStaffService = iStaffService;
    }

    @GetMapping("/init")
    public boolean init() {
        boolean isCenterInit = iCenterService.init();
        boolean isStaffInit = iStaffService.init();
        boolean isEnterpriseInit = iEnterpriseService.init();
        boolean isModelInit = iModelService.init();
        return isCenterInit && isStaffInit && isEnterpriseInit && isModelInit;
    }

}
