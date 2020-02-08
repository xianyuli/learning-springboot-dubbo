package com.xianyuli.dubbo.bootdubboapi;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.H2Query;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneratorApplicationTests {

    @Test
    void test() {
        String dbUrl = "jdbc:h2:file:~/.h2/user";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.H2)
                .setDbQuery(new H2Query())
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("1234")
                .setDriverName("org.h2.Driver");
        generate("com.baomidou.mp.h2", dataSourceConfig, "user");
    }

    void generate(String packageName, DataSourceConfig dataSourceConfig, String tableNames) {
        boolean serviceClassNameStartWithI = false;
        boolean enableTableFieldAnnotation = true;
        GlobalConfig config = new GlobalConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("K神带你飞")
                .setOutputDir("./")
                .setFileOverride(true)
                .setOpen(false);//禁掉弹窗
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
