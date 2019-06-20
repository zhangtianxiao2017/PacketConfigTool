package com.test.config.dev.page.tabpage;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.utils.ContextUtil;
import com.test.config.dev.utils.StringUtil;
import com.test.config.dev.vo.FullPathElementDefVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

/**
 * 生成 jTabbedPane 中的 Component 组件 FullPathElementDef 数据的预览和操作
 */
@Component
public class FullPathElementDefOperateTab {

    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;


    private JTextArea jPanel_Right_Bottom_TextArea;


    public java.awt.Component createFullPathElementDefTab(){
        // test 报文配置结果
        List<FullpathElementDef> configPacket = new ArrayList<>();

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
        // FullPathElementDefVo 转成 TreeNode
        ContextUtil.covertToNodeTree(fullPathElementDefVo,defaultMutableTreeNodeReal);
        // 用节点创建树
        JTree jTree = new JTree(defaultMutableTreeNodeReal);
        // 用带有滑动条的容器来装JTree
        JScrollPane jScrollPane_Left = new JScrollPane(new JPanel().add(jTree));


        // 生成右边数据展示和操作页面
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new GridLayout(2,1));

        JPanel jPanelRight_Top = new JPanel();
        jPanelRight_Top.setLayout(new GridLayout(8,2));
        Class<FullpathElementDef> fullpathElementDefClass = FullpathElementDef.class;
        Field[] declaredFields = fullpathElementDefClass.getDeclaredFields();

        List<JLabel> jLabelList = new ArrayList<>();
        List<JTextField> jTextFields = new ArrayList<>();

        for (Field declaredField : declaredFields) {
            JLabel jLabel = new JLabel(declaredField.getName());
            JTextField jTextField = new JTextField("testValue");
            jLabelList.add(jLabel);
            jPanelRight_Top.add(jLabel);
            jTextFields.add(jTextField);
            jPanelRight_Top.add(jTextField);
        }

        // 添加生成json报文和excel文件的按钮
        JButton jButtonJsonCreate = new JButton("生成Json报文");
        JButton JButtonExcelCreate = new JButton("生成Excel");
        JPanel jPanelGenerate = new JPanel();
        jPanelGenerate.add(jButtonJsonCreate);
        jPanelGenerate.add(JButtonExcelCreate);



        // 添加删除报文记录按钮
        JButton jButtonJsonClear = new JButton("CLEAR");
        // 注册删除报文记录的事件
        jButtonJsonClear.addActionListener(e -> {
            // 清空报文记录集合
            configPacket.clear();
            // 报文显示置为空
            jPanel_Right_Bottom_TextArea.setText("");
        });

        // 注册生成Json报文的事件
        jButtonJsonCreate.addActionListener(e -> {
            // 对结果进行打包处理
            FullPathElementDefVo fullPathElementDefVo1 = ContextUtil.packFullPathElement(configPacket);

            // 转成json
            String packetJson = ContextUtil.convertFullPathElementDefVoToJson(fullPathElementDefVo1);

            System.out.println(packetJson);

            // 生成报文文件 packet.json
            File file = new File("F:\\packetconfig\\packetconfig"+ StringUtil.getNowDateString() +".json");
            try {
                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write(packetJson);

                fileWriter.close();
            } catch (Exception  e1) {
                e1.printStackTrace();
            }

        });

        // 注册生成excel报文的事件
        JButtonExcelCreate.addActionListener( e -> {
            // 根据数据生成Excel
            ContextUtil.convertFullPathElementDefVoToExcel(configPacket);
        });

        jPanelRight_Top.add(jPanelGenerate);
        jPanelRight_Top.add(jButtonJsonClear);

        // 生成右侧下半部分
        JPanel jPanelRight_Bottom = new JPanel();
        jPanel_Right_Bottom_TextArea = new JTextArea(50,50);

        jPanelRight_Bottom.add(jPanel_Right_Bottom_TextArea);



        jPanelRight.add(jPanelRight_Top);
        jPanelRight.add(new JScrollPane(jPanelRight_Bottom));


        // 给JTree注册点击事件
        // 注册事件
        jTree.addTreeSelectionListener((e)->{

            System.out.println("单击事件");

            JTree tree = (JTree) e.getSource();
            // 利用JTree的getLastSelectedPathComponent()方法取得目前选取的节点.
            DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) tree
                    .getLastSelectedPathComponent();
            FullPathElementDefVo pathElementDefVo = (FullPathElementDefVo) selectionNode.getUserObject();
            // 赋值
            for (int i = 0; i < jLabelList.size(); i++) {
                String text1 = jLabelList.get(i).getText();
                Class<FullPathElementDefVo> fullPathElementDefVoClass = FullPathElementDefVo.class;
                try {
                    Field declaredField = fullpathElementDefClass.getDeclaredField(text1);
                    declaredField.setAccessible(true);
                    jTextFields.get(i).setText(declaredField.get(pathElementDefVo.getOwnFullPathElement()).toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });



        // jTree 注册鼠标点击事件
        jTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    // 双击事件
                    System.out.println("mouseClick双击事件");
                    JTree tree = (JTree) e.getSource();

                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) tree
                            .getLastSelectedPathComponent();
                    FullPathElementDefVo pathElementDefVo = (FullPathElementDefVo) selectionNode.getUserObject();

                    // 存放被点击的
                    configPacket.add(pathElementDefVo.getOwnFullPathElement());

                    // 被点击后生成报文，放到TextArea中
                    // 对结果进行打包处理
                    FullPathElementDefVo fullPathElementDefVo1 = ContextUtil.packFullPathElement(configPacket);

                    // 转成json
                    String packetJson = ContextUtil.convertFullPathElementDefVoToJson(fullPathElementDefVo1);

                    jPanel_Right_Bottom_TextArea.setText(packetJson);

                    System.out.println(configPacket);
                }
                if(e.getClickCount() == 1){
                    System.out.println("mouseClick单击事件");
                }
            }
        });

        // 展开首个节点信息
        jTree.expandRow(1);

        jPanel.add(jScrollPane_Left);
        jPanel.add(jPanelRight);
        return jPanel;
    }
}
