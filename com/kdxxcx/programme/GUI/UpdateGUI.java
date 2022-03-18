package com.kdxxcx.programme.GUI;

import javax.swing.*;

public class UpdateGUI {

    private static JPanel panel;
    private static JFrame frame;
    private static JTextArea textArea;
    private static JScrollPane scrollPane;

    public UpdateGUI(String updateText) {
        panel = new JPanel();
        frame = new JFrame("更新日志");
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);
        panel.setLayout(null);
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        frame.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));

        textArea = new JTextArea();
        scrollPane = new JScrollPane();

        textArea.setBounds(10, 10, 980, 710);
        textArea.setFont(textArea.getFont().deriveFont(20F));
        scrollPane.setBounds(10, 10, 980, 710);
        scrollPane.add(textArea);
        scrollPane.setViewportView(textArea);
        panel.add(scrollPane);

        textArea.setText(updateText);
        textArea.setEditable(false);
    }
}
