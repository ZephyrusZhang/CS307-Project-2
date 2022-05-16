package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.Center;
import edu.sustech.cs307.mapper.CenterMapper;
import edu.sustech.cs307.service.ICenterService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class CenterServiceImpl extends ServiceImpl<CenterMapper, Center> implements ICenterService {

    @Override
    public boolean init() {
        List<Center> centers = new LinkedList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/static/data/center.csv");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Center center = new Center();
                center.setName(line[1]);
                center.setExpenditure(0);
                center.setRevenue(0);
                centers.add(center);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return this.saveBatch(centers);
    }
}
