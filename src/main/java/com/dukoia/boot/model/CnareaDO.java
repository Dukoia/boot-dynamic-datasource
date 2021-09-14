package com.dukoia.boot.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 中国行政地区表
 * </p>
 *
 * @author Dukoia
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cnarea")
public class CnareaDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级行政代码
     */
    private Long parentCode;

    /**
     * 行政代码
     */
    private Long areaCode;

    /**
     * 邮政编码
     */
    private Integer zipCode;

    /**
     * 区号
     */
    private String cityCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 组合名
     */
    private String mergerName;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 子子孙孙
     */
    private List<CnareaDO> cnareaDOS;


    public static final String ID = "id";

    public static final String LEVEL = "level";

    public static final String PARENT_CODE = "parent_code";

    public static final String AREA_CODE = "area_code";

    public static final String ZIP_CODE = "zip_code";

    public static final String CITY_CODE = "city_code";

    public static final String NAME = "name";

    public static final String SHORT_NAME = "short_name";

    public static final String MERGER_NAME = "merger_name";

    public static final String PINYIN = "pinyin";

    public static final String LNG = "lng";

    public static final String LAT = "lat";

}
