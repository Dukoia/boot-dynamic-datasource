package com.dukoia.boot.mapper;

import com.dukoia.boot.model.ConfigInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * config_info Mapper 接口
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-16
 */
public interface ConfigInfoMapper extends BaseMapper<ConfigInfoDO> {

    List<ConfigInfoDO> selectAll(Collection<? extends Serializable> idList);
}
