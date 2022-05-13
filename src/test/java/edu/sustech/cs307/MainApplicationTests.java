package edu.sustech.cs307;

import edu.sustech.cs307.mapper.ModelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
        List<Map<String, Object>> maps = modelMapper.getFavoriteProductModel();
        maps.forEach(map -> {});
    }

}
