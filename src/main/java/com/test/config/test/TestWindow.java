package com.test.config.test;

import javax.swing.*;

public class TestWindow {

    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        //JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        frame.setSize(500,500);

        // 显示窗口
        // frame.pack();
        frame.setVisible(true);
    }

}
