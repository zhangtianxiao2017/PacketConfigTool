package com.test.config.test.render;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyTreeRender extends DefaultTreeCellRenderer {


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        setText(value.toString());

        if (sel)
        {
            setForeground(getTextSelectionColor());
        }
        else
        {
            setForeground(getTextNonSelectionColor());
        }




        return this;
    }
}
