package edu.sustech.cs307.controller;

import edu.sustech.cs307.entity.Center;
import edu.sustech.cs307.service.ICenterService;
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
@RequestMapping("/center")
public class CenterController {

    private final ICenterService centerService;

    public CenterController(ICenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping("/centerList")
    public List<Center> centerList() {
        return this.centerService.list();
    }
}
