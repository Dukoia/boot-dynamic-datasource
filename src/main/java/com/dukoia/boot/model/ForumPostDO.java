package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("pico_forum_post")
public class ForumPostDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 父id,插入评论时候用
     */
    private Integer parentid;

    private Integer fid;

    private Integer tid;

    private Integer first;

    private String author;

    private Integer authorid;

    private String subject;

    private Integer dateline;

    private String message;

    /**
     * 去除html后的帖子内容
     */
    private String content;

    private String useip;

    private Integer port;

    private Integer invisible;

    private Integer anonymous;

    private Integer usesig;

    private Integer htmlon;

    private Integer bbcodeoff;

    private Integer smileyoff;

    private Integer parseurloff;

    private Integer attachment;

    private Integer rate;

    private Integer ratetimes;

    private Integer status;

    private String tags;

    private Integer comment;

    private Integer replycredit;

    private Integer position;

    private Integer isdeleted;

    /**
     * 1标识在PC发表帖子或评论，2标识在APP发表帖子或评论
     */
    private Integer type;

    /**
     * 评论时候图片
     */
    @TableField("commentPictureUrl")
    private String commentPictureUrl;

    /**
     * app发帖时候内容图
     */
    @TableField("appPictureUrl")
    private String appPictureUrl;

    /**
     * 删除帖子操作人
     */
    private Integer operaid;

    /**
     * 删除帖子操作人名称
     */
    private String operaname;

    /**
     * 重新发布修改人
     */
    @TableField("modifyUser")
    private String modifyUser;

    /**
     * 重新发布修改时间
     */
    @TableField("modifyTime")
    private String modifyTime;


    public static final String PID = "pid";

    public static final String PARENTID = "parentid";

    public static final String FID = "fid";

    public static final String TID = "tid";

    public static final String FIRST = "first";

    public static final String AUTHOR = "author";

    public static final String AUTHORID = "authorid";

    public static final String SUBJECT = "subject";

    public static final String DATELINE = "dateline";

    public static final String MESSAGE = "message";

    public static final String CONTENT = "content";

    public static final String USEIP = "useip";

    public static final String PORT = "port";

    public static final String INVISIBLE = "invisible";

    public static final String ANONYMOUS = "anonymous";

    public static final String USESIG = "usesig";

    public static final String HTMLON = "htmlon";

    public static final String BBCODEOFF = "bbcodeoff";

    public static final String SMILEYOFF = "smileyoff";

    public static final String PARSEURLOFF = "parseurloff";

    public static final String ATTACHMENT = "attachment";

    public static final String RATE = "rate";

    public static final String RATETIMES = "ratetimes";

    public static final String STATUS = "status";

    public static final String TAGS = "tags";

    public static final String COMMENT = "comment";

    public static final String REPLYCREDIT = "replycredit";

    public static final String POSITION = "position";

    public static final String ISDELETED = "isdeleted";

    public static final String TYPE = "type";

    public static final String COMMENTPICTUREURL = "commentPictureUrl";

    public static final String APPPICTUREURL = "appPictureUrl";

    public static final String OPERAID = "operaid";

    public static final String OPERANAME = "operaname";

    public static final String MODIFYUSER = "modifyUser";

    public static final String MODIFYTIME = "modifyTime";

}
