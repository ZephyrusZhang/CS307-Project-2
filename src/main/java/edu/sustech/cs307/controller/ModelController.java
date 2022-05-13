package edu.sustech.cs307.controller;

import edu.sustech.cs307.mapper.ModelMapper;
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
@RequestMapping("/model")
public class ModelController {

    private final ModelMapper modelMapper;

    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getNeverSoldProductCount")
    public Map<String, Integer> getNeverSoldProductCount() {
        Map<String, Integer> map = modelMapper.getNeverSoldProductCount();
        if (map.size() == 0) return null;
        System.out.printf("neverSoldProductCount=%d\n", map.get("count"));
        return map;
    }

    @GetMapping("/getFavoriteProductModel")
    public List<Map<String, Object>> getFavoriteProductModel() {
        List<Map<String, Object>> maps = modelMapper.getFavoriteProductModel();
        maps.forEach(map -> System.out.printf("modelName=%s, modelSales=%d\n", map.get("modelName"), (int) map.get("sales")));
        return maps;
    }

    @GetMapping("/getProductByNumber")
    public List<Map<String, Object>> getProductByNumber(@RequestParam("productName") String productName) {
        List<Map<String, Object>> maps = modelMapper.getProductByNumber(productName);
        maps.forEach(map -> System.out.printf("centerName=%s, modelName=%s, count=%d\n", map.get("centerName"), map.get("modelName"), (int) map.get("count")));
        return maps;
    }

}
