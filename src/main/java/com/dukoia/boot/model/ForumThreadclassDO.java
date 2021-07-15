package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dukoia
 * @since 2021-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pico_forum_threadclass")
public class ForumThreadclassDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "typeid", type = IdType.AUTO)
    private Integer typeid;

    private Integer fid;

    private String name;

    private Integer displayorder;

    private String icon;

    private Integer moderators;


    public static final String TYPEID = "typeid";

    public static final String FID = "fid";

    public static final String NAME = "name";

    public static final String DISPLAYORDER = "displayorder";

    public static final String ICON = "icon";

    public static final String MODERATORS = "moderators";

}
