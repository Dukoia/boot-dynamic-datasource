package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2021-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pico_common_member")
public class CommonMember extends Model<CommonMember> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String email;

    private String username;

    private String password;

    private Integer status;

    private Integer emailstatus;

    private Integer avatarstatus;

    private Integer videophotostatus;

    private Integer adminid;

    private Integer groupid;

    private Integer groupexpiry;

    private String extgroupids;

    private Integer regdate;

    private Integer credits;

    private Integer notifysound;

    private String timeoffset;

    private Integer newpm;

    private Integer newprompt;

    private Integer accessmasks;

    private Integer allowadmincp;

    private Integer onlyacceptfriendpm;

    private Integer conisbind;

    private Integer freeze;

    private Integer allowbanuser;

    private Long bantime;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 唯一
     */
    private String uniqid;

    /**
     * ucenter表中的字段
     */
    private String regip;

    /**
     * ucenter表中的字段，注册手机号
     */
    private String phone;


    public static final String UID = "uid";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String STATUS = "status";

    public static final String EMAILSTATUS = "emailstatus";

    public static final String AVATARSTATUS = "avatarstatus";

    public static final String VIDEOPHOTOSTATUS = "videophotostatus";

    public static final String ADMINID = "adminid";

    public static final String GROUPID = "groupid";

    public static final String GROUPEXPIRY = "groupexpiry";

    public static final String EXTGROUPIDS = "extgroupids";

    public static final String REGDATE = "regdate";

    public static final String CREDITS = "credits";

    public static final String NOTIFYSOUND = "notifysound";

    public static final String TIMEOFFSET = "timeoffset";

    public static final String NEWPM = "newpm";

    public static final String NEWPROMPT = "newprompt";

    public static final String ACCESSMASKS = "accessmasks";

    public static final String ALLOWADMINCP = "allowadmincp";

    public static final String ONLYACCEPTFRIENDPM = "onlyacceptfriendpm";

    public static final String CONISBIND = "conisbind";

    public static final String FREEZE = "freeze";

    public static final String ALLOWBANUSER = "allowbanuser";

    public static final String BANTIME = "bantime";

    public static final String AVATAR = "avatar";

    public static final String UNIQID = "uniqid";

    public static final String REGIP = "regip";

    public static final String PHONE = "phone";

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
