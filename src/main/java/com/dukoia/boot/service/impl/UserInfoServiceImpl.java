package com.dukoia.boot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.Master;
import com.dukoia.boot.model.UserInfo;
import com.dukoia.boot.mapper.UserInfoMapper;
import com.dukoia.boot.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dukoia
 * @since 2021-07-20
 */
@Service
@DS("master")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
