package com.test.config.test;

import com.test.config.dev.dao.FullPathElementDefDao;
import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.utils.ContextUtil;
import com.test.config.dev.vo.FullPathElementDefVo;
import com.test.config.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Component
public class TbalePaneTest {


    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;

    /**
     * 启动选项卡页面
     */
    public void startTablePane(){
        System.out.println("hello pane");


        JFrame jFrame = new JFrame("config配置");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setSize(500,500);

        // 选项卡管理
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("test1",makeTextPanel("111") );

        jFrame.add(jTabbedPane);

        //jFrame.pack();
        jFrame.setVisible(true);
        //jFrame.setContentPane(jTabbedPane);
    }
    public  JComponent makeTextPanel(String text)
    {
        JPanel panelLeft=new JPanel(false);
//        JLabel filler=new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1,1));
//        panel.add(filler);



//
//        DefaultTreeCellRenderer cellRendere = new DefaultTreeCellRenderer();
//        cellRendere.setClosedIcon(new ImageIcon(""));
//
//        cellRendere.setClosedIcon(new Icon() {
//            @Override
//            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
//
//            }
//
//            @Override
//            public int getIconWidth() {
//                return 0;
//            }
//
//            @Override
//            public int getIconHeight() {
//                return 0;
//            }
//        });

        List<FullpathElementDef> all = fullPathElementDefDao.findAll();

        // 打包分类
        FullPathElementDefVo fullPathElementDefVo = ContextUtil.packFullPathElement(all);

        // 生成树
        DefaultMutableTreeNode defaultMutableTreeNodeReal = new DefaultMutableTreeNode("realRoot");


        ContextUtil.covertToNodeTree(fullPathElementDefVo,defaultMutableTreeNodeReal);



        // 生成节点
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("根节点");


        for(int i = 0; i < 4; i++){
            DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(new User());
            for(int j = 0; j < 4; j++){
                DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode("test"+i+"."+j);
                defaultMutableTreeNode.add(defaultMutableTreeNode1);
            }
            root.add(defaultMutableTreeNode);
        }



        // 放入Jtree
        JTree jTree = new JTree(defaultMutableTreeNodeReal);


        panelLeft.add(jTree);
        JScrollPane jScrollPane = new JScrollPane(panelLeft);

        // fullpathelementdef 信息展示或者修改界面
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new GridLayout(2,1));

        JPanel jPanelRight_Top = new JPanel();
        jPanelRight_Top.setLayout(new GridLayout(7,2));
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

        jPanelRight.add(jPanelRight_Top);
        jPanelRight.add(new JPanel());




        // 注册事件
        jTree.addTreeSelectionListener((e)->{
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

        jTree.expandRow(1);


//        Class<FullpathElementDef> fullpathElementDefClass = FullpathElementDef.class;
//        Field[] declaredFields = fullpathElementDefClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            JLabel jLabel = new JLabel(declaredField.getName());
//            JTextField jTextField = new JTextField("testValue");
//            jPanelRight.add(jLabel);
//            jPanelRight.add(jTextField);
//        }


        JPanel mainJpanel = new JPanel();
        mainJpanel.setLayout(new GridLayout(1,2));
        mainJpanel.add(jScrollPane);
        mainJpanel.add(jPanelRight);


        return mainJpanel;
    }

    public static void main(String[] args){
        JFrame frame=new JFrame("Java文本框组件示例");    //创建Frame窗口
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new GridLayout(2,2));
        jPanelRight.add(new JButton("1"));
        jPanelRight.add(new JButton("2"));
        jPanelRight.add(new JButton("3"));





        frame.setContentPane(jPanelRight);
        frame.setBounds(300,200,400,100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
