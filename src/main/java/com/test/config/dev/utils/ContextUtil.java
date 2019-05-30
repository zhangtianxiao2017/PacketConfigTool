package com.test.config.dev.utils;

import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.vo.FullPathElementDefVo;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public class ContextUtil {


    /**
     * 处理从数据库中查出的List集合数据，以parentPath分组后，组合成 FullPathElementDefVo 对象
     * 数据变成树结构的数据
     * @param list
     * @return
     */
    public static FullPathElementDefVo packFullPathElement(List<FullpathElementDef> list){
        // 分组结果集 已parentpath作为key
        Map<String,List<FullpathElementDef>> resultMap = new HashMap<String, List<FullpathElementDef>>();

        // 遍历List集合
        for (FullpathElementDef fullpathElementDef : list) {
            // 获取集合
            String parentPath = fullpathElementDef.getParentPath();

            if(resultMap.containsKey(parentPath)){
                resultMap.get(parentPath).add(fullpathElementDef);
            }else {
                List<FullpathElementDef> fullpathElementDefs = new LinkedList<FullpathElementDef>();
                fullpathElementDefs.add(fullpathElementDef);
                resultMap.put(parentPath,fullpathElementDefs);
            }
        }

        FullpathElementDef fullpathElementDef = new FullpathElementDef();
        fullpathElementDef.setJsonItemPath("root");
        FullPathElementDefVo fullPathElementDefVo = new FullPathElementDefVo(fullpathElementDef);

        convertToTree(fullPathElementDefVo, resultMap);

        return fullPathElementDefVo;
    }

    public static void convertToTree(FullPathElementDefVo fullPathElementDefVo, Map<String,List<FullpathElementDef>> alls){

        String jsonItemPath = fullPathElementDefVo.getOwnFullPathElement().getJsonItemPath();

        if(!alls.containsKey(jsonItemPath)){
            return;
        }

        List<FullpathElementDef> fullpathElementDefs = alls.get(jsonItemPath);

        for (FullpathElementDef fullpathElementDef : fullpathElementDefs) {

            FullPathElementDefVo fullPathElementDefVo1 = new FullPathElementDefVo(fullpathElementDef);

            convertToTree(fullPathElementDefVo1,alls);

            fullPathElementDefVo.getChildFullPathElement().add(fullPathElementDefVo1);
            fullPathElementDefVo.getChildFullPathElementSorted().add(fullPathElementDefVo1);
        }
    }

    public static void covertToNodeTree(FullPathElementDefVo fullPathElementDefVo, DefaultMutableTreeNode defaultMutableTreeNode){
        FullpathElementDef ownFullPathElement = fullPathElementDefVo.getOwnFullPathElement();
        DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode(fullPathElementDefVo);

        // 注册事件

        defaultMutableTreeNode.add(defaultMutableTreeNode1);

//        List<FullPathElementDefVo> childFullPathElement = fullPathElementDefVo.getChildFullPathElement();
//        for (FullPathElementDefVo pathElementDefVo : childFullPathElement) {
//            covertToNodeTree(pathElementDefVo, defaultMutableTreeNode1);
//        }

        TreeSet<FullPathElementDefVo> childFullPathElementSorted = fullPathElementDefVo.getChildFullPathElementSorted();
        for (FullPathElementDefVo pathElementDefVo : childFullPathElementSorted) {
            covertToNodeTree(pathElementDefVo, defaultMutableTreeNode1);
        }
    }

}
