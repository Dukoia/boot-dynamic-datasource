package com.dukoia.boot.service.impl;

import com.dukoia.boot.model.ForumPost;
import com.dukoia.boot.mapper.ForumPostMapper;
import com.dukoia.boot.service.IForumPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dukoia
 * @since 2021-08-10
 */
@Service
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements IForumPostService {

}
