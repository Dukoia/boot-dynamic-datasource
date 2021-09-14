package com.dukoia.boot.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author FanShukui
 * @version 2018/9/18 18:22
 */
@Slf4j
public class MpGenerator {
    private static String author = "Dukoia";
    private static String dbUrl = "rm-2zen0a0g63p5r3xu5ho.mysql.rds.aliyuncs.com";
    private static String dbSchema = "db_pico_bbs_prod";
    private static String dbUserName = "bbs_prod";
    private static String dbPwd = "sDsd33$4h1dagHdsa@1hg";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        log.info(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器

        AutoGenerator mpg = new AutoGenerator();

        String modulePath = "/intellis-persistent";
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        String redirectPath = projectPath + modulePath;
//        String redirectPath = "E:/yonyou/intellis/intellis-modules/intellis-persistent";
        gc.setOutputDir(projectPath + "/src/main/java");
//        String redirectPathDpf = "/Users/duanpf/work/yonyoucloud/dataplatform/intellis-modules/intellis-persistent";
//        gc.setOutputDir(redirectPathDpf + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        gc.setActiveRecord(true);
        gc.setFileOverride(false);

        //默认TIME_PACK，LocalDateTime
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + dbUrl + "/" + dbSchema + "?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=PRC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dukoia.boot");
        pc.setXml("mapper");
        pc.setEntity("model");
        pc.setService("service");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityColumnConstant(true);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix(new String[] { "pico_", "tsys_" });

//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude("pico_forum_post");
//        if (pc.getModuleName() != null) {
//            strategy.setTablePrefix(pc.getModuleName() + "_");
//        }
        strategy.setLogicDeleteFieldName("dr");
        strategy.setVersionFieldName("version");

        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("ts", FieldFill.INSERT_UPDATE));
        strategy.setTableFillList(tableFillList);
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                if (pc.getModuleName() != null) {
                    return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                } else {
                    return projectPath + "/src/main/resources/mapper/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setXml(null);
        mpg.setTemplate(tc);
        mpg.execute();
    }


}
