package edu.sustech.cs307.controller;

import edu.sustech.cs307.mapper.StaffMapper;
import edu.sustech.cs307.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
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
@RequestMapping("/staff")
public class StaffController {

    private final StaffMapper staffMapper;

    public StaffController(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @GetMapping("getAllStaffCount")
    public List<Map<String, Object>> getAllStaffCount() {
        List<Map<String, Object>> maps = staffMapper.getAllStaffCount();
        maps.forEach(map -> System.out.printf("type=%s, count=%d\n", map.get("key"), (int) map.get("value")));
        return maps;
    }

}
