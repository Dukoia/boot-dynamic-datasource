package com.dukoia.boot.service.impl;

import com.dukoia.boot.config.TransactionComponent;
import com.dukoia.boot.mapper.ConfigInfoMapper;
import com.dukoia.boot.model.CommonMember;
import com.dukoia.boot.mapper.CommonMemberMapper;
import com.dukoia.boot.model.ConfigInfoDO;
import com.dukoia.boot.service.ICommonMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dukoia
 * @since 2021-07-19
 */
@Service
public class CommonMemberServiceImpl extends ServiceImpl<CommonMemberMapper, CommonMember> implements ICommonMemberService {

    @Autowired
    ConfigInfoMapper configInfoMapper;

    @Autowired
    TransactionComponent transactionComponent;

    @Override
    public void mutiUpdate() throws Exception {
        CommonMember commonMember = new CommonMember();
        ConfigInfoDO configInfoDO = new ConfigInfoDO();
        String s = transactionComponent.doTransactional(() -> this.updateConfigAndMember(commonMember, configInfoDO));
    }

    public String updateConfigAndMember(CommonMember commonMember, ConfigInfoDO configInfoDO) {
        baseMapper.updateById(commonMember);
        configInfoMapper.updateById(configInfoDO);
        return "success";
    }
}
