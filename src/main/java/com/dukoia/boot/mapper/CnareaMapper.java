package com.dukoia.boot.mapper;

import com.dukoia.boot.model.CnareaDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 中国行政地区表 Mapper 接口
 * </p>
 *
 * @author Dukoia
 * @since 2021-09-14
 */
@Mapper
public interface CnareaMapper extends BaseMapper<CnareaDO> {

    /**
     *  通过areaCode 查询本身及其子节点
     * @param areaCode
     * @return
     */
    List<CnareaDO> queryByAreaCode(Long areaCode);

    /**
     *  通过areaCode 查询本身及其子节点
     * @param areaCode
     * @return
     */
    List<CnareaDO> queryByAreaCodeAsParentCode(Long areaCode);
}
