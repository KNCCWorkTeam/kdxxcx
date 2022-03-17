package com.kdxxcx.util.AWTAndSwing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame {
    public static void changeFonts(JFrame frame, Font font, Component[] components) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (components!=null) {
            for (Component co : components) {
                co.setFont(font);
            }
        }
    }

    public static void changeFonts(JFrame frame,float font, Component[] components) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (components!=null) {
            for (Component co : components) {
                co.setFont(co.getFont().deriveFont(font));
            }
        }
    }

    public static void changeFonts(JFrame frame, Font font, ArrayList<?extends Component> arrayList) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (arrayList!=null) {
            for (Component co : arrayList) {
                co.setFont(font);
            }
        }
    }

    public static void changeFonts(JFrame frame,float font, ArrayList<?extends Component> arrayList) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (arrayList!=null) {
            for (Component co : arrayList) {
                co.setFont(co.getFont().deriveFont(font));
            }
        }
    }

    public static void changeFonts(JPanel panel, Font font, Component[] components) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (components!=null) {
            for (Component co : components) {
                co.setFont(font);
            }
        }
    }

    public static void changeFonts(JPanel panel,float font, Component[] components) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (components!=null) {
            for (Component co : components) {
                co.setFont(co.getFont().deriveFont(font));
            }
        }
    }

    public static void changeFonts(JPanel panel, Font font, ArrayList<?extends Component> arrayList) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (arrayList!=null) {
            for (Component co : arrayList) {
                co.setFont(font);
            }
        }
    }

    public static void changeFonts(JPanel panel,float font, ArrayList<?extends Component> arrayList) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (arrayList!=null) {
            for (Component co : arrayList) {
                co.setFont(co.getFont().deriveFont(font));
            }
        }
    }


    public static void changeFonts(JFrame frame, Font font, Component component) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (component!=null) {
            component.setFont(font);
        }
    }

    public static void changeFonts(JFrame frame,float font, Component component) {
        for(Component co:frame.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (component!=null) {
            component.setFont(component.getFont().deriveFont(font));
        }
    }

    public static void changeFonts(JPanel panel, Font font, Component component) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(font);
        }
        if (component!=null) {
            component.setFont(font);
        }
    }

    public static void changeFonts(JPanel panel,float font, Component component) {
        for(Component co:panel.getRootPane().getContentPane().getComponents()) {
            co.setFont(co.getFont().deriveFont(font));
        }
        if (component!=null) {
            component.setFont(component.getFont().deriveFont(font));
        }
    }
}
