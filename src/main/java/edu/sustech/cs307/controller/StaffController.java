package edu.sustech.cs307.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sustech.cs307.common.Result;
import edu.sustech.cs307.entity.Staff;
import edu.sustech.cs307.mapper.StaffMapper;
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
@RequestMapping("/staff")
public class StaffController {

    private final StaffMapper staffMapper;

    public StaffController(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @PostMapping("/addOneStaff")
    public Result<?> addOneStaff(@RequestBody Staff staff) {
        if (staffMapper.insert(staff) == 1) {
            return Result.success();
        } else {
            return Result.error("233", "Insert failed");
        }
    }

    @GetMapping("/getAllStaffCount")
    public List<Map<String, Object>> getAllStaffCount() {
        List<Map<String, Object>> maps = staffMapper.getAllStaffCount();
        maps.forEach(map -> System.out.printf("staffType=%s, staffCount=%d\n", map.get("type"), (int) map.get("count")));
        return maps;
    }

    @GetMapping("/show")
    public Result<?> show(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "") String number) {
        Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(number)) {
            return Result.success(staffMapper.selectByNumberPage(page, number));
        } else {
            return Result.success(staffMapper.listPage(page));
        }
    }

    @GetMapping("/listStaffPage")
    public Result<?> listStaffPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "20") Integer pageSize,
                                   @RequestParam(defaultValue = "") String number) {
        QueryWrapper<Staff> wrapper = null;
        if (StrUtil.isNotBlank(number)) {
            wrapper = new QueryWrapper<Staff>().eq("number", number);
        }
        Page<Staff> page = staffMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }

    @DeleteMapping("/delete/{number}")
    public boolean deleteByNumber(@PathVariable String number) {
        return staffMapper.deleteByNumber(number);
    }

    @PutMapping("/updateStaff")
    public int updateStaff(@RequestBody Staff staff) {
        return staffMapper.updateByName(staff);
    }

}
