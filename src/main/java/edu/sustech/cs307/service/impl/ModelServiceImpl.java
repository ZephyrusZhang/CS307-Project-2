package edu.sustech.cs307.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.sustech.cs307.entity.Model;
import edu.sustech.cs307.mapper.ModelMapper;
import edu.sustech.cs307.service.IModelService;
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
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Override
    public boolean init() {
        List<Model> modelList = new LinkedList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/static/data/model.csv");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Model model = new Model();
                model.setNumber(line[1]);
                model.setModelName(line[2]);
                model.setProductName(line[3]);
                model.setUnitPrice(Integer.parseInt(line[4]));
                model.setSales(0);
                modelList.add(model);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return this.saveBatch(modelList);
    }
}
