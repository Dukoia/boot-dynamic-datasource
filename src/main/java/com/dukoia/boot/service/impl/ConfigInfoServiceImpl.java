package com.dukoia.boot.service.impl;

import com.dukoia.boot.model.ConfigInfoDO;
import com.dukoia.boot.mapper.ConfigInfoMapper;
import com.dukoia.boot.service.ConfigInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * config_info 服务实现类
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-16
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfoDO> implements ConfigInfoService {

}
