package edu.sustech.cs307;

import edu.sustech.cs307.mapper.ModelMapper;
import edu.sustech.cs307.service.IInventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private IInventoryService iInventoryService;

    @Test
    void contextLoads() {
        iInventoryService.stockIn("");
    }

}
