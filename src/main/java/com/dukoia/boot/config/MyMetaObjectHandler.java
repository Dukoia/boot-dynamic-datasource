package com.dukoia.boot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: MyMetaObjectHandler
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        List<String> insertFieldNames = new ArrayList<>();
        insertFieldNames.add("createTime");
        insertFieldNames.add("ts");

        for (String fieldName : insertFieldNames) {
            Object fieldObj = getFieldValByName(fieldName, metaObject);
            if (fieldObj == null) {
                strictInsertFill(metaObject, fieldName, LocalDateTime.class, LocalDateTime.now());
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        List<String> updateFieldNames = new ArrayList<>();
        updateFieldNames.add("ts");
        if (updateFieldNames.size() > 0) {
            for (String fieldName : updateFieldNames) {
                strictInsertFill(metaObject, fieldName, LocalDateTime.class, LocalDateTime.now());
            }
        }
    }
}
