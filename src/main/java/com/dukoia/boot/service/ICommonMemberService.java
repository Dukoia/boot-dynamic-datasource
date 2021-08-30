package com.dukoia.boot.service;

import com.dukoia.boot.model.CommonMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dukoia
 * @since 2021-07-19
 */
public interface ICommonMemberService extends IService<CommonMember> {

    /**
     * 多个更新
     * @throws Exception
     */
    void mutiUpdate() throws Exception;

}
