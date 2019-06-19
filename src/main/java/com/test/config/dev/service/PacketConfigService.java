package com.test.config.dev.service;

import com.test.config.dev.dao.BizEntryDefDao;
import com.test.config.dev.dao.BizFactorDefDao;
import com.test.config.dev.dao.BizFullPathElementRelaDao;
import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.pojo.*;
import com.test.config.dev.utils.ContextUtil;
import com.test.config.dev.vo.FullPathElementDefVo;
import com.test.config.dev.vo.PacketConfigVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class PacketConfigService {


    @Autowired
    private BizEntryDefDao bizEntryDefDao;
    @Autowired
    private BizFactorDefDao bizFactorDefDao;
    @Autowired
    private BizFullPathElementRelaDao bizFullPathElementRelaDao;
    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;

    public List<PacketConfigVo> getPacketConfigInfoAll(){
        // 获取报文入口对应的所有报文数据

        // 结果集
        List<PacketConfigVo> packetConfigVoList = new ArrayList<>();

        // 1. 获取报文入口数据
        List<BizEntryDef> all = bizEntryDefDao.findAll();
        for (BizEntryDef bizEntryDef : all) {

            PacketConfigVo packetConfigVo = new PacketConfigVo();
            packetConfigVo.setBizEntryDef(bizEntryDef);

            // 2. 查询报文入口对应的渠道批改类型等数据
            BizFactorDef bizFactorDef = new BizFactorDef();
            bizFactorDef.settEntryId(bizEntryDef.getId());
            Example<BizFactorDef> bizFactorDefExample = Example.of(bizFactorDef);
            List<BizFactorDef> all1 = bizFactorDefDao.findAll(bizFactorDefExample);
            for (BizFactorDef factorDef : all1) {
                packetConfigVo.getBizFactorDefList().add(factorDef);
            }

            // 3. 查询报文数据
            List<FullpathElementDef> fullPathElementDefRelateToBizEntries = new ArrayList<>();

            BizFullpathElementRela bizFullpathElementRela = new BizFullpathElementRela();
            bizFullpathElementRela.settBizEntryDefId(bizEntryDef.getId());
            Example<BizFullpathElementRela> of = Example.of(bizFullpathElementRela);
            List<BizFullpathElementRela> all2 = bizFullPathElementRelaDao.findAll(of);
            for (BizFullpathElementRela fullpathElementRela : all2) {
                FullpathElementDef fullpathElementDef = new FullpathElementDef();
                fullpathElementDef.setId(fullpathElementRela.gettFullpathElementDefId());
                Example<FullpathElementDef> of1 = Example.of(fullpathElementDef);
                FullpathElementDef one = fullPathElementDefDao.findOne(of1);

                FullPathElementDefRelateToBizEntry fullPathElementDefRelateToBizEntry = new FullPathElementDefRelateToBizEntry();

                BeanUtils.copyProperties(one,fullPathElementDefRelateToBizEntry, FullpathElementDef.class);
                // 组装成 FullPathElementDefRelateToBizEntry
                fullPathElementDefRelateToBizEntry.setBizFullpathElementRela(fullpathElementRela);

                fullPathElementDefRelateToBizEntries.add(fullPathElementDefRelateToBizEntry);
            }

            // 4. 报文集合转换成PacketConfigFullPathElementConfigVo
            FullPathElementDefVo fullPathElementDefVo = ContextUtil.packFullPathElement(fullPathElementDefRelateToBizEntries);
            packetConfigVo.setFullPathElementDefVo(fullPathElementDefVo);


            packetConfigVoList.add(packetConfigVo);
        }

        return packetConfigVoList;
    }

}
