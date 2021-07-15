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
@TableName("pico_forum_forum")
public class ForumForumDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    private Integer fup;

    /**
     * 板主uid
     */
    @TableField("moderatorUid")
    private Integer moderatorUid;

    private String type;

    private String name;

    private Integer status;

    private Integer displayorder;

    private Integer styleid;

    private Integer threads;

    private Integer posts;

    private Integer todayposts;

    private Integer yesterdayposts;

    private Integer rank;

    private Integer oldrank;

    private String lastpost;

    private String domain;

    private Integer allowsmilies;

    private Integer allowhtml;

    private Integer allowbbcode;

    private Integer allowimgcode;

    private Integer allowmediacode;

    private Integer allowanonymous;

    private Integer allowpostspecial;

    private Integer allowspecialonly;

    private Integer allowappend;

    private Integer alloweditrules;

    private Integer allowfeed;

    private Integer allowside;

    private Integer recyclebin;

    private Integer modnewposts;

    private Integer jammer;

    private Integer disablewatermark;

    private Integer inheritedmod;

    private Integer autoclose;

    private Integer forumcolumns;

    private Integer catforumcolumns;

    private Integer threadcaches;

    private Integer alloweditpost;

    private Integer simple;

    private Integer modworks;

    private Integer allowglobalstick;

    private Integer level;

    private Integer commoncredits;

    private Integer archive;

    private Integer recommend;

    private Integer favtimes;

    private Integer sharetimes;

    private Integer disablethumb;

    private Integer disablecollect;

    /**
     * 图片，或者板块封面url
     */
    @TableField("pictureUrl")
    private String pictureUrl;

    /**
     * FAQ主键
     */
    private Integer faqid;


    public static final String FID = "fid";

    public static final String FUP = "fup";

    public static final String MODERATORUID = "moderatorUid";

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String DISPLAYORDER = "displayorder";

    public static final String STYLEID = "styleid";

    public static final String THREADS = "threads";

    public static final String POSTS = "posts";

    public static final String TODAYPOSTS = "todayposts";

    public static final String YESTERDAYPOSTS = "yesterdayposts";

    public static final String RANK = "rank";

    public static final String OLDRANK = "oldrank";

    public static final String LASTPOST = "lastpost";

    public static final String DOMAIN = "domain";

    public static final String ALLOWSMILIES = "allowsmilies";

    public static final String ALLOWHTML = "allowhtml";

    public static final String ALLOWBBCODE = "allowbbcode";

    public static final String ALLOWIMGCODE = "allowimgcode";

    public static final String ALLOWMEDIACODE = "allowmediacode";

    public static final String ALLOWANONYMOUS = "allowanonymous";

    public static final String ALLOWPOSTSPECIAL = "allowpostspecial";

    public static final String ALLOWSPECIALONLY = "allowspecialonly";

    public static final String ALLOWAPPEND = "allowappend";

    public static final String ALLOWEDITRULES = "alloweditrules";

    public static final String ALLOWFEED = "allowfeed";

    public static final String ALLOWSIDE = "allowside";

    public static final String RECYCLEBIN = "recyclebin";

    public static final String MODNEWPOSTS = "modnewposts";

    public static final String JAMMER = "jammer";

    public static final String DISABLEWATERMARK = "disablewatermark";

    public static final String INHERITEDMOD = "inheritedmod";

    public static final String AUTOCLOSE = "autoclose";

    public static final String FORUMCOLUMNS = "forumcolumns";

    public static final String CATFORUMCOLUMNS = "catforumcolumns";

    public static final String THREADCACHES = "threadcaches";

    public static final String ALLOWEDITPOST = "alloweditpost";

    public static final String SIMPLE = "simple";

    public static final String MODWORKS = "modworks";

    public static final String ALLOWGLOBALSTICK = "allowglobalstick";

    public static final String LEVEL = "level";

    public static final String COMMONCREDITS = "commoncredits";

    public static final String ARCHIVE = "archive";

    public static final String RECOMMEND = "recommend";

    public static final String FAVTIMES = "favtimes";

    public static final String SHARETIMES = "sharetimes";

    public static final String DISABLETHUMB = "disablethumb";

    public static final String DISABLECOLLECT = "disablecollect";

    public static final String PICTUREURL = "pictureUrl";

    public static final String FAQID = "faqid";

}
