package com.dukoia.boot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("pico_forum_thread")
public class ForumThreadDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    private Integer fid;

    private Integer posttableid;

    private Integer typeid;

    private Integer sortid;

    private Integer readperm;

    private Integer price;

    private String author;

    private Integer authorid;

    private String subject;

    private Integer dateline;

    private Integer lastpost;

    private String lastposter;

    private Integer views;

    private Integer replies;

    private Integer displayorder;

    private Integer highlight;

    private Integer digest;

    private Integer rate;

    private Integer special;

    private Integer attachment;

    private Integer moderated;

    private Integer closed;

    private Integer stickreply;

    private Integer recommends;

    private Integer recommendAdd;

    private Integer recommendSub;

    private Integer heats;

    private Integer status;

    private Integer isgroup;

    private Integer favtimes;

    private Integer sharetimes;

    private Integer stamp;

    private Integer icon;

    private Integer pushedaid;

    private Integer cover;

    private Integer replycredit;

    private String relatebytag;

    private Integer maxposition;

    private String bgcolor;

    private Integer comments;

    private Integer hidden;

    private Date topstarttime;

    private Date topendtime;

    private Integer isdeleted;

    /**
     * ????????????
     */
    private String deletedreason;

    /**
     * ????????????
     */
    private String topreason;

    /**
     * ??????????????????
     */
    private String digestreason;

    /**
     * ??????????????????
     */
    private String recommendreason;

    /**
     * ???????????????0???????????????3????????????
     */
    private Integer recommend;

    /**
     * ??????????????????
     */
    private Integer likes;

    /**
     * ????????????
     */
    private String pictureurl;

    /**
     * ????????????url
     */
    private String videourl;

    /**
     * ????????????pc->0;app->1;
     */
    private Integer type;

    /**
     * ??????????????????????????????????????????
     */
    private Integer publishstatus;

    /**
     * ??????????????????
     */
    private String url;

    /**
     * ???????????????
     */
    @TableField("auditName")
    private String auditName;

    /**
     * ?????????ID
     */
    @TableField("auditId")
    private Integer auditId;

    @TableField("auditTime")
    private Date auditTime;

    /**
     * ????????????
     */
    @TableField("topicName")
    private String topicName;

    /**
     * ??????ID
     */
    @TableField("topicId")
    private Integer topicId;


    public static final String TID = "tid";

    public static final String FID = "fid";

    public static final String POSTTABLEID = "posttableid";

    public static final String TYPEID = "typeid";

    public static final String SORTID = "sortid";

    public static final String READPERM = "readperm";

    public static final String PRICE = "price";

    public static final String AUTHOR = "author";

    public static final String AUTHORID = "authorid";

    public static final String SUBJECT = "subject";

    public static final String DATELINE = "dateline";

    public static final String LASTPOST = "lastpost";

    public static final String LASTPOSTER = "lastposter";

    public static final String VIEWS = "views";

    public static final String REPLIES = "replies";

    public static final String DISPLAYORDER = "displayorder";

    public static final String HIGHLIGHT = "highlight";

    public static final String DIGEST = "digest";

    public static final String RATE = "rate";

    public static final String SPECIAL = "special";

    public static final String ATTACHMENT = "attachment";

    public static final String MODERATED = "moderated";

    public static final String CLOSED = "closed";

    public static final String STICKREPLY = "stickreply";

    public static final String RECOMMENDS = "recommends";

    public static final String RECOMMEND_ADD = "recommend_add";

    public static final String RECOMMEND_SUB = "recommend_sub";

    public static final String HEATS = "heats";

    public static final String STATUS = "status";

    public static final String ISGROUP = "isgroup";

    public static final String FAVTIMES = "favtimes";

    public static final String SHARETIMES = "sharetimes";

    public static final String STAMP = "stamp";

    public static final String ICON = "icon";

    public static final String PUSHEDAID = "pushedaid";

    public static final String COVER = "cover";

    public static final String REPLYCREDIT = "replycredit";

    public static final String RELATEBYTAG = "relatebytag";

    public static final String MAXPOSITION = "maxposition";

    public static final String BGCOLOR = "bgcolor";

    public static final String COMMENTS = "comments";

    public static final String HIDDEN = "hidden";

    public static final String TOPSTARTTIME = "topstarttime";

    public static final String TOPENDTIME = "topendtime";

    public static final String ISDELETED = "isdeleted";

    public static final String DELETEDREASON = "deletedreason";

    public static final String TOPREASON = "topreason";

    public static final String DIGESTREASON = "digestreason";

    public static final String RECOMMENDREASON = "recommendreason";

    public static final String RECOMMEND = "recommend";

    public static final String LIKES = "likes";

    public static final String PICTUREURL = "pictureurl";

    public static final String VIDEOURL = "videourl";

    public static final String TYPE = "type";

    public static final String PUBLISHSTATUS = "publishstatus";

    public static final String URL = "url";

    public static final String AUDITNAME = "auditName";

    public static final String AUDITID = "auditId";

    public static final String AUDITTIME = "auditTime";

    public static final String TOPICNAME = "topicName";

    public static final String TOPICID = "topicId";

}
