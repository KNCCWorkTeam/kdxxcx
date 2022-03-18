package com.kdxxcx.programme.OPGUI;

import com.kdxxcx.util.SQL.General;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.Array.ArrayRead.arrayRead;
import static com.kdxxcx.util.SQL.General.insertInto;
import static com.kdxxcx.util.SQL.General.selectOut;
import static com.kdxxcx.util.SQL.ResultSetAnalyze.resultSetAnalyze;
import static com.kdxxcx.util.String.GetWords.getWords;
import static com.kdxxcx.util.String.GetWords.getWordsCount;

public class CommandGUI extends JFrame implements KeyListener {
    public CommandGUI() {
        super("命令行");
        init();
    }

    JTextArea commandOutput = new JTextArea();
    JTextField commandInput = new JTextField();
    ArrayList<String> commands = new ArrayList<>();
    int selectCommandNumber = 0;

    void init() {
        //frame
        this.setLocationRelativeTo(null);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));

        JScrollPane scrollPane = new JScrollPane(commandOutput);
        commandOutput.setEditable(false);
        commandOutput.setFont(commandOutput.getFont().deriveFont(20F));
        commandOutput.setForeground(new Color(15, 115, 15));
        JButton submit = new JButton("确认");
        submit.addActionListener(l->{
            submit();
        });

        //layout
        GridBagLayout gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        this.add(scrollPane);
        this.add(commandInput);
        this.add(submit);
        commandInput.addKeyListener(this);
        submit.addKeyListener(this);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 10, 9,10, 10,gridBagLayout,scrollPane);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 9, 9, 1,10, 10,gridBagLayout,commandInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,9, 9, 1, 1,10, 10,gridBagLayout,submit);

        this.setVisible(true);
    }

    void submit() {
        commands.add(commandInput.getText());
        selectCommandNumber = commands.size();
        submit(commandInput.getText());
        commandInput.setText("");
        commandInput.requestFocus();
    }

    void submit(String text) {
        String command = text.trim();
        if (!command.equals("")) {
            output("<User>:" , command);
        }
        if (getWordsCount(command)<=1) {
            switch (command.toLowerCase()) {
                case "select":
                    output("Wrong grammar:"+command+",please read the helper below");
                    submit("help select");
                    break;
                case "insert":
                    output("Wrong grammar:"+command+",please read the helper below");
                    submit("help insert");
                    break;
                case "update":
                    output("Wrong grammar:"+command+",please read the helper below");
                    submit("help update");
                    break;
                case "delete":
                    output("Wrong grammar:"+command+",please read the helper below");
                    submit("help delete");
                    break;
                case "help":
                    submit("");
                    break;
                case "cls":
                    commandOutput.setText("");
                    break;
                default:
                    output("unknown command:"+command);
                    submit("help select");
                    submit("help insert");
                    submit("help update");
                    submit("help delete");
                    submit("help help");
                    submit("help cls");
            }
        } else {
            String commandFirst = getWords(command, 1);
            String commandSecond = getWords(command, 2);
            switch (commandFirst.toLowerCase()) {
                case "select":
                    if (commandSecond.equals("*")) {
                        output(commandSecond + ":Try to type in only one colName");
                    } else {
                        new Thread(()->{
                            try {
                                //get properties
                                Properties properties = new Properties();
                                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                                String url = properties.getProperty("url");
                                String user = properties.getProperty("user");
                                String password = properties.getProperty("password");
                                output(arrayRead(resultSetAnalyze(selectOut(url, user, password, command), commandSecond), "\n"));
                            } catch (FileNotFoundException e) {
                                output("Can't find properties with:"+e);
                            } catch (IOException e) {
                                output("IOException for:"+command+" with:"+e);
                            } catch (Exception e) {
                                output("Exception for:"+command+" with:"+e);
                            }
                        });
                    }
                    break;
                case "insert":
                    if (!commandSecond.equals("into")||getWordsCount(command)<=2) {
                        output("Wrong grammar:" + command);
                        submit("help insert");
                    } else {
                        insertInto(command);
                    }
                    break;
                case "update":
                    insertInto(command);
                    break;
                case "delete":
                    if (!commandSecond.equals("from")||getWordsCount(command)<=2) {
                        output("Wrong grammar:" + command);
                        submit("help delete");
                    } else {
                        insertInto(command);
                    }
                    break;
                case "help":
                    switch (commandSecond.toLowerCase()) {
                        case "select":
                            output("select grammar:\n" +
                                    "select {colName}\n" +
                                    " from {tableName}\n" +
                                    "( where {compare (and|or compare ...)})\n" +
                                    "( order {colName (ASC|DESC)(,colName (ASC|DESC)) ...})");
                            break;
                        case "insert":
                            output("insert grammar:\n" +
                                    "insert into {tableName}\n" +
                                    "( {colName,colName,...})\n" +
                                    " values {{colValue,colValue,...}}");
                            break;
                        case "update":
                            output("update grammar:\n" +
                                    "update {tableName}\n" +
                                    " set {colName} = {colValue}\n" +
                                    "( where {compare (and|or compare ...)})");
                            break;
                        case "delete":
                            output("delete grammar:\n" +
                                    "delete from {tableName}\n" +
                                    "( where {compare (and|or compare ...)})");
                            break;
                        case "help":
                            output("Get help of every commands");
                            break;
                        case "cls":
                            output("Clean the command output");
                            break;
                        default:
                            output(commandSecond + ":Doesn't have this command");
                    }
                    break;
            }
        }
    }

    void insertInto(String command) {
        new Thread(() -> {
            try {
                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                General.insertInto(url, user, password, command);
                output("Command has been run");
            } catch (FileNotFoundException e) {
                output("Can't find properties with:" + e);
            } catch (IOException e) {
                output("IOException for:" + command + " with:" + e);
            } catch (Exception e) {
                output("Exception for:" + command + " with:" + e);
            }
        });
    }

    void output(String user,String command) {
        String enter = "";
        if (!commandOutput.getText().equals("")) {
            enter = "\r\n\n";
        }
        commandOutput.setText(commandOutput.getText()+enter+user+command);
    }

    void output(String output) {
        String enter = "";
        if (!commandOutput.getText().equals("")) {
            enter = "\r\n";
        }
        commandOutput.setText(commandOutput.getText()+enter+output);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()==KeyEvent.VK_UP) {
            System.out.println(123);
            selectCommandNumber--;
            commandInput.setText(commands.get(selectCommandNumber));
        }
        if (e.getKeyChar()==KeyEvent.VK_DOWN) {
            if (commands.size()!=selectCommandNumber) {
                commandInput.setText(commands.get(selectCommandNumber));
                selectCommandNumber++;
            }
        }
        if (e.getKeyChar()==KeyEvent.VK_ENTER) {
            submit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
