package com.test.config.dev.vo;

import lombok.Data;

import java.util.TreeSet;

@Data
public class PacketConfigFullPathElementConfigRelVo <T extends FullPathElementDefVo> {

    private PacketConfigFullPathElementConfigRelVo packetConfigFullPathElementConfigRelVo;
    private T ownElementDefRelateToBizEntry;
    private TreeSet<PacketConfigFullPathElementConfigRelVo> childPacketConfigFullPathElementConfigRelVo = new TreeSet<>((o1,o2)->{
        int size1 = o1.getChildPacketConfigFullPathElementConfigRelVo().size();
        int size2 = o2.getChildPacketConfigFullPathElementConfigRelVo().size();
        if(size1 == size2){
            return 1;
        }else {
            return size1 - size2;
        }
    });
}
