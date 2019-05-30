package com.test.config.dev.page;

import com.test.config.dev.page.tabpage.FullPathElementDefOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * 主界面 JFrame 中放入 选项卡实现不同界面的切换
 */
@Component
public class MainPage {

    @Autowired
    private FullPathElementDefOperate fullPathElementDefOperate;

    public void pageStart(){
        // 创建JFrame 用来放入 JTabbedPane 选项卡
        JFrame jFrame = new JFrame("config配置");

        // 设置页面关闭行为
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 在JFrame中放入选项卡
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("FullPathElement",fullPathElementDefOperate.createFullPathElementDefTab());


        // 设置页面大小
        jFrame.setSize(500,500);
        // 设置页面可见性
        jFrame.setVisible(true);
    }

}
