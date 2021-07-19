package com.dukoia.boot.mapper;

import com.dukoia.boot.model.ConfigInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface ConfigInfoMapper extends BaseMapper<ConfigInfoDO> {

}
