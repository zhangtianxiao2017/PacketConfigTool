package com.test.config.dev.vo;

import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.pojo.BizEntryDef;
import com.test.config.dev.pojo.BizFactorDef;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PacketConfigVo {

    /**
     * 报文入口信息
     */
    private BizEntryDef bizEntryDef;

    /**
     * 批改类型对应的报文入口
     */
    private List<BizFactorDef> bizFactorDefList = new ArrayList<>();


    /**
     * 批单类型信息
     */
    private FullPathElementDefVo fullPathElementDefVo;
}
