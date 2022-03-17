package com.kdxxcx.util.AWTAndSwing;

import java.awt.*;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;

public class Layout {
    public static void gridBagConstraintsSetSizeAndAdd(GridBagConstraints gridBagConstraints,int x, int y, int width, int height, int breadth, int length,GridBagLayout gridBagLayout,Component comp) {
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints, x, y, width, height, breadth, length, gridBagInset, gridBagInset, gridBagInset, gridBagInset, gridBagLayout, comp);
    }

    public static void gridBagConstraintsSetSizeAndAdd(GridBagConstraints gridBagConstraints,int x, int y, int width, int height, int breadth, int length,int top, int left, int bottom, int right, GridBagLayout gridBagLayout,Component comp) {
        gridBagConstraints.insets = new Insets(top, left, bottom, right);
        gridBagConstraints.gridx=x;
        gridBagConstraints.gridy=y;
        gridBagConstraints.gridwidth=width;
        gridBagConstraints.gridheight=height;
        gridBagConstraints.weightx=(double)width/breadth;
        gridBagConstraints.weighty=(double)height/length;
        gridBagLayout.setConstraints(comp, gridBagConstraints);
    }
}
