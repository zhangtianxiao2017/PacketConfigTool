package com.test.config.dev.vo;

import com.test.config.dev.pojo.FullpathElementDef;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class FullPathElementDefVo {

    private FullPathElementDefVo parentFullPathElement;

    private FullpathElementDef ownFullPathElement;

    private List<FullPathElementDefVo> childFullPathElement = new LinkedList<FullPathElementDefVo>();

    private TreeSet<FullPathElementDefVo> childFullPathElementSorted = new TreeSet<FullPathElementDefVo>((o1,o2)->{
        int size2 = o1.getChildFullPathElementSorted().size();
        int size1 = o2.getChildFullPathElementSorted().size();
        if(size1 == size2){
            return 1;
        }else {
            return size1 - size2;
        }
    });



    public TreeSet<FullPathElementDefVo> getChildFullPathElementSorted() {
        return childFullPathElementSorted;
    }

    public void setChildFullPathElementSorted(TreeSet<FullPathElementDefVo> childFullPathElementSorted) {
        this.childFullPathElementSorted = childFullPathElementSorted;
    }

    public FullPathElementDefVo(FullpathElementDef fullpathElementDef){
        this.ownFullPathElement = fullpathElementDef;
    }

    public FullPathElementDefVo getParentFullPathElement() {
        return parentFullPathElement;
    }

    public void setParentFullPathElement(FullPathElementDefVo parentFullPathElement) {
        this.parentFullPathElement = parentFullPathElement;
    }

    public List<FullPathElementDefVo> getChildFullPathElement() {
        return childFullPathElement;
    }

    public void setChildFullPathElement(List<FullPathElementDefVo> childFullPathElement) {
        this.childFullPathElement = childFullPathElement;
    }

    public FullpathElementDef getOwnFullPathElement() {
        return ownFullPathElement;
    }

    public void setOwnFullPathElement(FullpathElementDef ownFullPathElement) {
        this.ownFullPathElement = ownFullPathElement;
    }

    @Override
    public String toString() {
        String keys = ownFullPathElement.getJsonItemPath() + "(" +ownFullPathElement.getDesc() +")";
        return keys;
    }
}
