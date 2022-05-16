package edu.sustech.cs307;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class GenerateTemplate {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://localhost:5432/spring_cs307", "postgres", "2996362441")
                .globalConfig(builder -> builder.author("Zephyrus")
                        .disableOpenDir()
                        .outputDir(System.getProperty("user.dir") + "/src/main/java"))
                .packageConfig(builder -> builder.parent("edu.sustech.cs307")
                        .entity("entity")
                        .service("service")
                        .mapper("mapper")
                        .serviceImpl("service.impl")
                        .controller("controller"))
                .strategyConfig(builder -> builder.addInclude("users")
                        .addInclude("center_record")
                        .addInclude("contract")
                        .addInclude("enterprise")
                        .addInclude("inventory")
                        .addInclude("model")
                        .addInclude("orders")
                        .addInclude("staff"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
