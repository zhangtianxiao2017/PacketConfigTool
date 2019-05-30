package com.test.config.dev.page.tabpage;



import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.utils.ContextUtil;
import com.test.config.dev.vo.FullPathElementDefVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.List;

/**
 * 生成 jTabbedPane 中的 Component 组件 FullPathElementDef 数据的预览和操作
 */
@Component
public class FullPathElementDefOperate {

    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;

    public java.awt.Component createFullPathElementDefTab(){
        // JPanel采用网络布局 一分为2 左边放 FullPathElementTree 右边显示 点击节点的数据 （和操作按钮）
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1,2));

        // 左边FullPathElement tree 生成操作
        // 获取数据
        List<FullpathElementDef> all = fullPathElementDefDao.findAll();
        // 打包分类
        FullPathElementDefVo fullPathElementDefVo = ContextUtil.packFullPathElement(all);
        // 生成树的节点
        DefaultMutableTreeNode defaultMutableTreeNodeReal = new DefaultMutableTreeNode("realRoot");
        // 用节点创建树
        JTree jTree = new JTree(defaultMutableTreeNodeReal);


        return null;
    }
}
