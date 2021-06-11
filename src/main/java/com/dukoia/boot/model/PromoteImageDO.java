package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pico_promote_image")
public class PromoteImageDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 主题名称
     */
    private String subjectName;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 图片类型
     */
    private Integer imageType;

    /**
     * 状态 0可用 1过期
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
