package com.kdxxcx.programme.GUI;

import com.kdxxcx.util.File.FileReader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.Array.ArrayRead.arrayRead;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.IOExceptionDealing;

public class EditAreaGUI extends JFrame {
    public EditAreaGUI() throws HeadlessException, IOException {
        super("地址文件编辑器");
        init();
    }

    void init() throws IOException {
        //frame
        this.setLocationRelativeTo(null);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\.area");
        JTextArea textArea = new JTextArea(arrayRead(fileReader.readResult(),"\n"));
        textArea.setFont(textArea.getFont().deriveFont(20f));
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton save = new JButton("保存");
        save.addActionListener(l->{
            save(textArea.getText());
        });
        JButton saveAndExit = new JButton("保存并退出");
        saveAndExit.addActionListener(l->{
            save(textArea.getText());
            this.setVisible(false);
        });

        GridBagLayout gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        this.add(scrollPane);
        this.add(save);
        this.add(saveAndExit);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 2, 9,2, 10,gridBagLayout,scrollPane);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 9, 1, 1,2, 10,gridBagLayout,save);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 9, 1, 1,2, 10,gridBagLayout,saveAndExit);

        this.setVisible(true);
    }

    public static void save(String saveInfo) {
        try {
            File file = new File(System.getProperty("user.dir")+"\\.area");
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            writer.write(saveInfo);
            writer.flush();
        } catch (IOException e) {
            IOExceptionDealing(e);
        }
    }
}
