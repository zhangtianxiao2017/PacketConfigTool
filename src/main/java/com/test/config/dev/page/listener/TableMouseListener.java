package com.test.config.dev.page.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickCount = e.getClickCount();
        // 单击事件
        if(clickCount == 1){
            JTable source = (JTable) e.getSource();
            int i = source.rowAtPoint(e.getPoint());
            System.out.println(i);
        }
    }
}
