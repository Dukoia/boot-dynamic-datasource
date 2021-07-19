package com.dukoia.boot.cache;

import com.dukoia.boot.model.ConfigInfoDO;
import com.dukoia.boot.service.ConfigInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ConfigCache
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
@Component
@Slf4j
public class ConfigCache extends AbstractCacheManager<Integer, ConfigInfoDO> {

    @Autowired
    private ConfigInfoService configInfoService;

    @Override
    protected void initCacheFields() {
        // 缓存每5min进行重新加载，如果超过50min没有刷新，那么则让缓存失效
        refreshDuration = 5;
        expiredDration = 20;
        durationTimeUnit = TimeUnit.MINUTES;
        cacheMaximumSize = 5000;
    }

    @Override
    protected ConfigInfoDO getValueWhenExpire(Integer key) throws Exception {
        ConfigInfoDO byId = configInfoService.getById(key);
        if (byId == null) {
            return null;
        }
        return byId;
    }

    @Override
    public ConfigInfoDO getValue(Integer key) {
        try {
            ConfigInfoDO configInfoDO = fetchDataFromCache(key);
            if (configInfoDO == null) {
                ConfigInfoDO byId = configInfoService.getById(key);
                if (byId != null) {
                    putDataToCache(key, byId);
                }
            }
            return configInfoDO;
        } catch (ExecutionException e) {
            log.error("装载缓存异常", e);
            return null;
        }
    }
}
