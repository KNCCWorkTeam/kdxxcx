package com.kdxxcx.programme.GUI;

import com.kdxxcx.programme.ConstantAndVar;
import com.kdxxcx.programme.OPGUI.CommandGUI;

import javax.swing.*;

import java.awt.*;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.jOptionPanePrint;

public class PasswordGUI extends JFrame {
    public PasswordGUI() {
        super("密码输入");
        init();
    }

    void init() {
        //frame
        this.setLocationRelativeTo(null);
        this.setSize(300, 85);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));

        JTextField passwordInput = new JTextField();
        JButton submit = new JButton("确认");
        submit.addActionListener(l->{
            if (passwordInput.getText().equals(String.valueOf(ConstantAndVar.PASSWORD))) {
                new CommandGUI();
                this.setVisible(false);
            } else {
                jOptionPanePrint("密码错误");
            }
        });

        GridBagLayout gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        this.add(passwordInput);
        this.add(submit);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 2, 1,3, 1,gridBagLayout,passwordInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,2, 0, 1, 1,3, 1,gridBagLayout,submit);

        this.setVisible(true);
    }
}
