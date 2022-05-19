package edu.sustech.cs307.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Model;
import edu.sustech.cs307.entity.Staff;
import edu.sustech.cs307.mapper.ModelMapper;
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
@RequestMapping("/model")
public class ModelController {

    private final ModelMapper modelMapper;

    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getNeverSoldProductCount")
    public Result<?> getNeverSoldProductCount(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "20") Integer pageSize,
                                              @RequestParam(defaultValue = "") String type) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        return Result.success(modelMapper.getNeverSoldProductCount(page));
    }

    @GetMapping("/getFavoriteProductModel")
    public Result<?> getFavoriteProductModel(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "20") Integer pageSize,
                                             @RequestParam(defaultValue = "") String type) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        return Result.success(modelMapper.getFavoriteProductModel(page));
    }

    @GetMapping("/getProductByNumber")
    public List<Map<String, Object>> getProductByNumber(@RequestParam("productName") String productName) {
        List<Map<String, Object>> maps = modelMapper.getProductByNumber(productName);
        maps.forEach(map -> System.out.printf("centerName=%s, modelName=%s, count=%d\n", map.get("centerName"), map.get("modelName"), (int) map.get("count")));
        return maps;
    }

    @GetMapping("/show")
    public Result<?> show(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "") String modelName) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(modelName)) {
            return Result.success(modelMapper.selectByModelNamePage(page, modelName));
        } else {
            return Result.success(modelMapper.selectPage(page));
        }
    }

    @DeleteMapping("/delete/{modelName}")
    public boolean deleteByModelName(@PathVariable String modelName) {
        return modelMapper.deleteByModelName(modelName);
    }

    @PutMapping("/updateModel")
    public int updateModel(@RequestBody Model model) {
        return modelMapper.updateByModelName(model);
    }

    @PostMapping("/addOneModel")
    public Result<?> addOneStaff(@RequestBody Model model) {
        if (modelMapper.insert(model) == 1) {
            return Result.success();
        } else {
            return Result.error("233", "Insert failed");
        }
    }

}
