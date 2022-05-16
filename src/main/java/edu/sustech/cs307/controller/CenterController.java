package edu.sustech.cs307.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Center;
import edu.sustech.cs307.mapper.CenterMapper;
import edu.sustech.cs307.service.ICenterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/center")
public class CenterController {

    private final ICenterService centerService;
    private final CenterMapper centerMapper;

    public CenterController(ICenterService centerService,
                            CenterMapper centerMapper) {
        this.centerService = centerService;
        this.centerMapper = centerMapper;
    }

    @GetMapping("/centerList")
    public List<Center> centerList() {
        return this.centerService.list();
    }

    @GetMapping("/show")
    public Result<?> show(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "") String name) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(name)) {
            return Result.success(centerMapper.selectByNamePage(page, name));
        } else {
            return Result.success(centerMapper.listPage(page));
        }
    }

    @PostMapping("/addOneCenter")
    public Result<?> addOneCenter(@RequestBody Center center) {
        if (centerMapper.insert(center) == 1) {
            return Result.success();
        } else {
            return Result.error("233", "Insert failed");
        }
    }

    @PutMapping("/updateCenter")
    public boolean updateCenter(@RequestBody Center center) {
        return centerMapper.updateCenterByName(center);
    }

    @DeleteMapping("/delete/{name}")
    public int deleteCenterByName(@PathVariable String name) {
        return centerMapper.deleteByName(name);
    }
}
