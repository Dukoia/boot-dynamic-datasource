package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * config_info
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("config_info")
public class ConfigInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * data_id
     */
    private String dataId;

    private String groupId;

    /**
     * content
     */
    private String content;

    /**
     * md5
     */
    private String md5;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * source user
     */
    private String srcUser;

    /**
     * source ip
     */
    private String srcIp;

    private String appName;

    /**
     * 租户字段
     */
    private String tenantId;

    private String cDesc;

    private String cUse;

    private String effect;

    private String type;

    private String cSchema;


    public static final String ID = "id";

    public static final String DATA_ID = "data_id";

    public static final String GROUP_ID = "group_id";

    public static final String CONTENT = "content";

    public static final String MD5 = "md5";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    public static final String SRC_USER = "src_user";

    public static final String SRC_IP = "src_ip";

    public static final String APP_NAME = "app_name";

    public static final String TENANT_ID = "tenant_id";

    public static final String C_DESC = "c_desc";

    public static final String C_USE = "c_use";

    public static final String EFFECT = "effect";

    public static final String TYPE = "type";

    public static final String C_SCHEMA = "c_schema";

}
