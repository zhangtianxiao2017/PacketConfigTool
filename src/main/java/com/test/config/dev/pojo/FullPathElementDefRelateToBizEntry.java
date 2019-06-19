package com.test.config.dev.pojo;

import com.test.config.dev.pojo.BizFullpathElementRela;
import com.test.config.dev.pojo.FullpathElementDef;
import lombok.Data;

/**
 * 报文入口对应的报文实体
 */
@Data
public class FullPathElementDefRelateToBizEntry extends FullpathElementDef{
    private BizFullpathElementRela bizFullpathElementRela;
}
