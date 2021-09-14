package com.dukoia.boot;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: MyListener
 * @Author: jiangze.He
 * @Date: 2021-09-10
 * @Version: v1.0
 */
public class MyListener extends AnalysisEventListener<BatchGold> {

    List<BatchGold> list = new ArrayList<BatchGold>();

    @Override
    public void invoke(BatchGold batchGold, AnalysisContext analysisContext) {
        list.add(batchGold);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
