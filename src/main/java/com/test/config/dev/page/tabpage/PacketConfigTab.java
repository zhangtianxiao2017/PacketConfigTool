package com.test.config.dev.page.tabpage;

import com.sun.deploy.panel.PropertyTreeModel;
import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.page.listener.TableMouseListener;
import com.test.config.dev.pojo.BizEntryDef;
import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.service.PacketConfigService;
import com.test.config.dev.utils.ContextUtil;
import com.test.config.dev.utils.RefelcetUtil;
import com.test.config.dev.vo.FullPathElementDefVo;
import com.test.config.dev.vo.PacketConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.sun.webkit.perf.WCFontPerfLogger.log;


@Component
@Slf4j
public class PacketConfigTab {

    @Autowired
    private PacketConfigService packetConfigService;

    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;

    public java.awt.Component createPacketConfigTab(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1,3));

        // 获取左边报文入口信息
        List<PacketConfigVo> packetConfigInfoAll = packetConfigService.getPacketConfigInfoAll();
        // 生成左边栏报文入口信息
        JPanel jPanelLeft = new JPanel();

        List<String> fieldNames = RefelcetUtil.getFieldNames(BizEntryDef.class);// 列名
        // 列名转成数组
        String[] fieldNamesString = fieldNames.toArray(new String[0]);

        int column = fieldNames.size(); // 列数
        int row = packetConfigInfoAll.size(); // 行数

        // JTable的数据值
        Object[][] jTableData = new Object[row][column];
        // 赋值
        for (int i = 0; i < packetConfigInfoAll.size(); i++) {
            PacketConfigVo packetConfigVo = packetConfigInfoAll.get(i);
            BizEntryDef bizEntryDef = packetConfigVo.getBizEntryDef(); // 报文入口信息

            // 列赋值
            for (int i1 = 0; i1 < column; i1++) {
                // 根据列名获取属性值
                String fieldValue = RefelcetUtil.getFieldValues(bizEntryDef, fieldNamesString[i1]).toString();
                jTableData[i][i1] = fieldValue;
            }
        }

        // 生成JTable
        JTable jTable = new JTable(jTableData,fieldNamesString);
        jTable.setPreferredScrollableViewportSize(new Dimension(500,300));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //jTable.setRowMargin(50);


        JScrollPane jScrollPaneTable = new JScrollPane(new JPanel().add(jTable),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanelLeft.add(jScrollPaneTable);

        JPanel jPanelCenter = new JPanel();
        final DefaultMutableTreeNode[] defaultMutableTreeNodeTest = {new DefaultMutableTreeNode("报文树结构")};
        DefaultTreeModel jTreePacketModel = new DefaultTreeModel(defaultMutableTreeNodeTest[0]);
        JTree jTreePacket = new JTree(jTreePacketModel);
        jPanelCenter.add(jTreePacket);

        // 注册鼠标点击事件
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    log.info("点击事件发生！");
                    // 获取点击的index
                    JTable source = (JTable) e.getSource();
                    int i = source.rowAtPoint(e.getPoint());
                    // 生成报文树
                    PacketConfigVo packetConfigVo = packetConfigInfoAll.get(i);
                    log.info(packetConfigVo.getBizEntryDef().getEntryName()+"开始加载报文。。。。");
                    DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("Tree");
                    ContextUtil.covertToNodeTree(packetConfigVo.getFullPathElementDefVo(),defaultMutableTreeNode);

                    // 移除原始节点数据
                    defaultMutableTreeNodeTest[0].removeAllChildren();
                    // 添加新的报文树节点
                    defaultMutableTreeNodeTest[0].add(defaultMutableTreeNode);

//                    JTree jTree = new JTree(defaultMutableTreeNode);
//                    jPanelCenter.removeAll();
//                    // 展开首个节点信息
//                    jTree.expandRow(1);
//                    jPanelCenter.add(jTree);

                    // 重新加载报文树结构
                    jTreePacketModel.reload();
                    // 展开首个节点
                    jTreePacket.expandRow(2);
                    log.info(packetConfigVo.getBizEntryDef().getEntryName()+"加载报文完成。。。。渲染数据。。。");
                }
            }
        });

        JScrollPane jScrollPaneCenter = new JScrollPane(jPanelCenter);

        // 右侧全量的报文树界面
        // 左边FullPathElement tree 生成操作
        // 获取数据
        List<FullpathElementDef> all = fullPathElementDefDao.findAll();
        // 打包分类
        FullPathElementDefVo fullPathElementDefVo = ContextUtil.packFullPathElement(all);
        // 生成树的节点
        DefaultMutableTreeNode defaultMutableTreeNodeReal = new DefaultMutableTreeNode("全量报文数据");
        // FullPathElementDefVo 转成 TreeNode
        ContextUtil.covertToNodeTree(fullPathElementDefVo,defaultMutableTreeNodeReal);
        // 用节点创建树
        DefaultTreeModel jTreeFullPacketModel = new DefaultTreeModel(defaultMutableTreeNodeReal);
        JTree jTree = new JTree(jTreeFullPacketModel);
        // 用带有滑动条的容器来装JTree
        JScrollPane jScrollPane_Left = new JScrollPane(new JPanel().add(jTree));
        // 给jTree添加点击事件（）


        // 实现全量报文向配置报文子树的数据添加
        jTreePacket.setEditable(true);
        jTree.setEditable(true);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(jTree, DnDConstants.ACTION_COPY_OR_MOVE, e -> {

        });

        jPanel.add(jPanelLeft);
        jPanel.add(jScrollPaneCenter);
        jPanel.add(jScrollPane_Left);

        return jPanel;
    }
}
