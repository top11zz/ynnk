package com.tgem.ynnk;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.tgem.ynnk.controller.BaseController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Code {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Test
    public void doCode(){

        //构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("lmx");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setServiceName("%sService");//去ServiceI前缀
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(driver);
        dsc.setUrl(url);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //设置包

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tgem.ynnk");
        pc.setModuleName("sys");
        pc.setController("controller");
        pc.setService("service");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        //策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("company");//表名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setSuperControllerClass(BaseController.class);
       //自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList<TableFill>();
        list.add(createTime);
        list.add(updateTime);
        sc.setTableFillList(list);
        sc.setVersionFieldName("update_time");//乐观锁
        sc.setRestControllerStyle(true);
        sc.setLogicDeleteFieldName("logic");
        sc.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(sc);

        mpg.execute();
    }
}
