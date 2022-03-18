package com.kdxxcx.programme.GUI;

import com.kdxxcx.programme.Class.Table;
import com.kdxxcx.programme.ConstantAndVar;
import com.kdxxcx.programme.OPGUI.CommandGUI;
import com.kdxxcx.util.Class.Compare;
import com.kdxxcx.util.Class.CompareArray;
import com.kdxxcx.util.Class.Order;
import com.kdxxcx.util.Exception.ArrayLengthNotCongruent;
import com.kdxxcx.util.Exception.NotNullException;
import com.kdxxcx.util.Exception.TooMuchQueryResultException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import static com.kdxxcx.programme.ConstantAndVar.KDXXCX;
import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Frame.changeFonts;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.*;
import static com.kdxxcx.util.KDXXCX.ReadUpdate.readUpdateString;
import static com.kdxxcx.util.SQL.Select.select;
import static com.kdxxcx.util.String.AddBrackets.addBrackets;
import static com.kdxxcx.util.Time.GetTime.getDate;

public class MenuGUI extends JFrame implements ComponentListener {
    GridBagLayout gridBagLayout;
    Component[] notContainsComponents;
    Table table1;
    Table table2;
    JButton[] insertButtons1 = new JButton[1000];
    JButton[] insertButtons2 = new JButton[1000];
    String[] column1 = new String[]{"操作时间", "号码", "姓名", "发票号", "联系电话", "地址"};
    String[][] list1 = new String[1000][6];
    String[] column2 = new String[]{"操作时间", "号码", "姓名", "发票号", "联系电话", "地址"};
    String[][] list2 = new String[1000][6];
    JTextField nameInput = new JTextField(20);
    JTextField cardInput = new JTextField(20);
    JTextField telephoneInput = new JTextField(20);
    JTextField addressInput = new JTextField(20);

    JComboBox<String> comboBox = new JComboBox<>();
    boolean documentChangeLock = true;
    JTextField selectedTextField;

    public MenuGUI() {
        super(KDXXCX);
        init();
    }

    void init(){
        //frame
        this.setLocationRelativeTo(null);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addComponentListener(this);
        this.setLocationRelativeTo(null);
        this.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));

        //components new
        JLabel name = new JLabel("姓名");
        JLabel card = new JLabel("发票号");
        JLabel telephone = new JLabel("联系电话");
        JLabel address = new JLabel("地址");
        JButton addressQuery = new JButton("地址查询");
        addressQuery.addActionListener(l->{
            new AddressQueryGUI(addressInput);
        });
        JPanel buttonPanel = new JPanel();
        JButton query = new JButton("查询");
        query.addActionListener(l->{
            query();
        });
        JButton change = new JButton("更改");
        change.addActionListener(l->{
            change();
        });
        JButton add = new JButton("新增");
        add.addActionListener(l->{
            add();
        });

        JTextField[] textFields = new JTextField[]{nameInput,cardInput,telephoneInput,addressInput};
        table1 = new Table(insertButtons1,list1,column1);
        table2 = new Table(insertButtons2,list2,column2);

        //layout
        gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        GridBagLayout buttonPanelGridBagLayout=new GridBagLayout();
        buttonPanel.setLayout(buttonPanelGridBagLayout);
        GridBagConstraints buttonPanelGridBagConstraints=new GridBagConstraints();
        buttonPanelGridBagConstraints.fill=GridBagConstraints.BOTH;
        buttonPanelGridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        //Menu
        MenuItem debugMode = new MenuItem("打开调试模式");
        MenuItem command = new MenuItem("打开命令行");
        MenuItem splitLine1 = new MenuItem("__________");
        MenuItem addressEdit = new MenuItem("设置地址查询");
        MenuItem splitLine2 = new MenuItem("__________");
        MenuItem versionInfo = new MenuItem("版本信息");
        MenuItem updateInfo = new MenuItem("更新日志");
        Menu baseMenu = new Menu("基础信息");
        Menu updateMenu = new Menu("更新");
        MenuBar menuBar = new MenuBar();

        //Menu add
        baseMenu.add(debugMode);
        baseMenu.add(command);
        baseMenu.add(splitLine1);
        baseMenu.add(addressEdit);
        baseMenu.add(splitLine2);
        baseMenu.add(versionInfo);
        updateMenu.add(updateInfo);
        menuBar.add(baseMenu);
        menuBar.add(updateMenu);

        //Menu Button Listener
        debugMode.addActionListener(l->{
            if (ConstantAndVar.debugMode) {
                ConstantAndVar.debugMode = false;
                debugMode.setLabel("打开调试模式");
            } else {
                ConstantAndVar.debugMode = true;
                debugMode.setLabel("关闭调试模式");
            }
        });
        command.addActionListener(l->{
            new PasswordGUI();
        });
        addressEdit.addActionListener(l->{
            try {
                new EditAreaGUI();
            } catch (IOException e) {
                IOExceptionDealing(e);
            }
        });
        versionInfo.addActionListener(l->{
            jOptionPanePrint("2.0.1", "版本");
        });
        updateInfo.addActionListener(l->{
            try {
                new UpdateGUI(readUpdateString());
            } catch (IOException e) {
                IOExceptionDealing(e);
            }
        });

        //add
        this.add(name);
        this.add(nameInput);
        this.add(card);
        this.add(cardInput);
        this.add(telephone);
        this.add(telephoneInput);
        this.add(address);
        this.add(addressInput);
        this.add(addressQuery);
        this.add(buttonPanel);
        this.add(table1.output());
        this.add(table2.output());

        buttonPanel.add(query);
        buttonPanel.add(change);
        buttonPanel.add(add);

        //layout set
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 1, 1,12, 28,gridBagLayout,name);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 0, 2, 1,12, 28,gridBagLayout,nameInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 0, 1, 1,12, 28,gridBagLayout,card);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 0, 8, 1,12, 28,gridBagLayout,cardInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 1, 1, 1,12, 28,gridBagLayout,telephone);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 1, 2, 1,12, 28,gridBagLayout,telephoneInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 1, 1, 1,12, 28,gridBagLayout,address);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 1, 7, 1,12, 28,gridBagLayout,addressInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,11, 1, 1, 1,12, 28,gridBagLayout,addressQuery);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 2, 12, 2,12, 28,gridBagLayout,buttonPanel);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 4, 12, 12,12, 28,gridBagLayout,table1.output());
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 16, 12, 12,12, 28,gridBagLayout,table2.output());

        gridBagConstraintsSetSizeAndAdd(buttonPanelGridBagConstraints,0, 0, 4, 2,12, 2,buttonPanelGridBagLayout,query);
        gridBagConstraintsSetSizeAndAdd(buttonPanelGridBagConstraints,4, 0, 4, 2,12, 2,buttonPanelGridBagLayout,change);
        gridBagConstraintsSetSizeAndAdd(buttonPanelGridBagConstraints,8, 0, 4, 2,12, 2,buttonPanelGridBagLayout,add);

        //end
        nameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("hzxm", nameInput);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("hzxm", nameInput);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        cardInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("card", cardInput);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("card", cardInput);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        telephoneInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("telephone", telephoneInput);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("telephone", telephoneInput);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        addressInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("address", addressInput);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (documentChangeLock) {
                    recommendInput("address", addressInput);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.setMenuBar(menuBar);
        this.setVisible(true);
        notContainsComponents = new Component[]{query,change,add};
        historyRefresh();
    }

    void query() {
        Thread query = new Thread(()->{
            try {
                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                CompareArray compareArray = new CompareArray(new Compare[]{new Compare("hzxm", "%" + nameInput.getText() + "%", "like"), new Compare("card", "%" + cardInput.getText() + "%", "like"), new Compare("telephone", "%" + telephoneInput.getText() + "%", "like"), new Compare("address", "%" + addressInput.getText() + "%", "like")});

                String[] updateTimeArray = select(url, user, password, "kdxxcx", "updatetime", compareArray);
                String[] numberArray = select(url, user, password, "kdxxcx", "number", compareArray);
                String[] nameArray = select(url, user, password, "kdxxcx", "hzxm", compareArray);
                String[] cardArray = select(url, user, password, "kdxxcx", "card", compareArray);
                String[] telephoneArray = select(url, user, password, "kdxxcx", "telephone", compareArray);
                String[] addressArray = select(url, user, password, "kdxxcx", "address", compareArray);

                if (updateTimeArray.length!=numberArray.length&&numberArray.length!=nameArray.length&&nameArray.length!=cardArray.length&&cardArray.length!=telephoneArray.length&&telephoneArray.length!=addressArray.length&&addressArray.length!=updateTimeArray.length) {
                    throw new ArrayLengthNotCongruent(new String[][]{numberArray, nameArray, cardArray, telephoneArray, addressArray}, "Arrays length aren't equal:");
                }
                int rowCount = numberArray.length;
                if (rowCount >= 500) {
                    rowCount = 500;
                }
                for (int i=0;i<500;i++) {
                    for (int k=0;k<6;k++) {
                        list2 = table2.getList();
                        list2[i][k] = null;
                        table2.setList(list2);
                        repaint();
                    }
                }
                //ThreadQuery
                list2 = table2.getList();
                int rowCountRemainder;
                rowCountRemainder = rowCount%2;
                int finalRowCount = rowCount;
                if (rowCount!=0) {

                    Thread queryThread1 = new Thread(() -> {
                        for (int i = 0;i<(finalRowCount - rowCountRemainder)/2;i++) {
                            list2[i][0] = updateTimeArray[i];
                            list2[i][1] = numberArray[i];
                            list2[i][2] = nameArray[i];
                            list2[i][3] = cardArray[i];
                            list2[i][4] = telephoneArray[i];
                            list2[i][5] = addressArray[i];
                            repaint();
                        }
                    });
                    queryThread1.start();
                    Thread queryThread2 = new Thread(() -> {
                        for (int i = (finalRowCount - rowCountRemainder)/2-1;i<finalRowCount - rowCountRemainder;i++) {
                            list2[i][0] = updateTimeArray[i];
                            list2[i][1] = numberArray[i];
                            list2[i][2] = nameArray[i];
                            list2[i][3] = cardArray[i];
                            list2[i][4] = telephoneArray[i];
                            list2[i][5] = addressArray[i];
                            repaint();
                        }
                    });
                    queryThread2.start();
                    Thread queryThreadRemainder = new Thread(() -> {
                        if (rowCountRemainder != 0) {
                            for (int i = finalRowCount - rowCountRemainder; i < finalRowCount; i++) {
                                list2[i][0] = updateTimeArray[i];
                                list2[i][1] = numberArray[i];
                                list2[i][2] = nameArray[i];
                                list2[i][3] = cardArray[i];
                                list2[i][4] = telephoneArray[i];
                                list2[i][5] = addressArray[i];
                                repaint();
                            }
                        }
                    });
                    queryThreadRemainder.start();
                    table2.setList(list2);
                    repaint();
                }
            } catch (ArrayLengthNotCongruent e) {
                ArrayLengthNotCongruentExceptionDealing(e);
            } catch (FileNotFoundException e) {
                FileNotFoundExceptionDealing(e);
            } catch (IOException e) {
                IOExceptionDealing(e);
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        });
        query.start();
    }

    void change() {
        Thread change = new Thread(()->{
            try {
                if (nameInput.getText().equals("") || cardInput.getText().equals("") || telephoneInput.getText().equals("") || addressInput.getText().equals("")) {
                    if (ConstantAndVar.selectedTable!=null) {
                        JTable selectedTable = ConstantAndVar.selectedTable;
                        int selectedRow = selectedTable.getSelectedRow();
                        TableModel tableModel = selectedTable.getModel();
                        try {
                            new ChangeGUI(tableModel.getValueAt(selectedRow, 2).toString(), tableModel.getValueAt(selectedRow, 3).toString(), tableModel.getValueAt(selectedRow, 4).toString(), tableModel.getValueAt(selectedRow, 5).toString());
                        } catch (NullPointerException e) {
                            throw new NotNullException();
                        }
                    } else {
                        //get properties
                        Properties properties = new Properties();
                        properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                        String url = properties.getProperty("url");
                        String user = properties.getProperty("user");
                        String password = properties.getProperty("password");
                        CompareArray compareArray = new CompareArray(new Compare[]{new Compare("hzxm", "%" + nameInput.getText() + "%", "like"), new Compare("card", "%" + cardInput.getText() + "%", "like"), new Compare("telephone", "%" + telephoneInput.getText() + "%", "like"), new Compare("address", "%" + addressInput.getText() + "%", "like")});

                        String[] nameArray = select(url, user, password, "kdxxcx", "hzxm", compareArray);
                        String[] cardArray = select(url, user, password, "kdxxcx", "card", compareArray);
                        String[] telephoneArray = select(url, user, password, "kdxxcx", "telephone", compareArray);
                        String[] addressArray = select(url, user, password, "kdxxcx", "address", compareArray);

                        int rowCount = nameArray.length;
                        if (rowCount == 0) {
                            throw new NotNullException();
                        } else if (rowCount == 1) {
                            new ChangeGUI(nameArray[0], cardArray[0], telephoneArray[0], addressArray[0]);
                        } else {
                            throw new TooMuchQueryResultException("Query result larger or equals 2 : ", new String[][]{nameArray, cardArray, telephoneArray, addressArray});
                        }
                    }
                } else {
                    new ChangeGUI(nameInput.getText(), cardInput.getText(), telephoneInput.getText(), addressInput.getText());
                    nameInput.setText("");
                    cardInput.setText("");
                    telephoneInput.setText("");
                    addressInput.setText("");
                    historyRefresh();
                }
            } catch (TooMuchQueryResultException e) {
                TooMuchQueryResultExceptionDealing(e);
            } catch (NotNullException e) {
                NotNullExceptionDealing(e);
            } catch (FileNotFoundException e) {
                FileNotFoundExceptionDealing(e);
            } catch (IOException e) {
                IOExceptionDealing(e);
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        });
        change.start();
    }

    void add() {
        try {
            if (nameInput.getText().equals("") || cardInput.getText().equals("") || telephoneInput.getText().equals("") || addressInput.getText().equals("")) {
                if (ConstantAndVar.selectedTable!=null) {
                    JTable selectedTable = ConstantAndVar.selectedTable;
                    int selectedRow = selectedTable.getSelectedRow();
                    TableModel tableModel = selectedTable.getModel();
                    try {
                        new AddGUI(tableModel.getValueAt(selectedRow, 2).toString(), tableModel.getValueAt(selectedRow, 3).toString(), tableModel.getValueAt(selectedRow, 4).toString(), tableModel.getValueAt(selectedRow, 5).toString());
                        nameInput.setText("");
                        cardInput.setText("");
                        telephoneInput.setText("");
                        addressInput.setText("");
                    } catch (NullPointerException e) {
                        throw new NotNullException();
                    }
                } else {
                    throw new NotNullException();
                }
            } else {
                new AddGUI(nameInput.getText(), cardInput.getText(), telephoneInput.getText(), addressInput.getText());
                nameInput.setText("");
                cardInput.setText("");
                telephoneInput.setText("");
                addressInput.setText("");
            }
        } catch (NotNullException e) {
            NotNullExceptionDealing(e);
        }
    }

    void historyRefresh() {
        Thread historyRefresh = new Thread(() -> {
            try {
                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                Compare dateLike = new Compare("updatetime",addBrackets(getDate(), "%"),"like");
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("updatetime", "ASC");
                Order order = new Order(hashMap);

                String[] updateTimeArray = select(url,user,password,"kdxxcx","updatetime",dateLike, order);
                String[] numberArray = select(url,user,password,"kdxxcx","number",dateLike, order);
                String[] nameArray = select(url,user,password,"kdxxcx","hzxm",dateLike, order);
                String[] cardArray = select(url,user,password,"kdxxcx","card",dateLike, order);
                String[] telephoneArray = select(url,user,password,"kdxxcx","telephone",dateLike, order);
                String[] addressArray = select(url,user,password,"kdxxcx","address",dateLike, order);

                int todayCount = numberArray.length;
                //ThreadQuery
                list1 = table1.getList();
                int todayCountRemainder;
                todayCountRemainder = todayCount%2;
                if (todayCount!=0) {

                    Thread queryThread1 = new Thread(() -> {
                        for (int i = 0; i<(todayCount - todayCountRemainder)/2; i++) {
                            list1[i][0] = updateTimeArray[i];
                            list1[i][1] = numberArray[i];
                            list1[i][2] = nameArray[i];
                            list1[i][3] = cardArray[i];
                            list1[i][4] = telephoneArray[i];
                            list1[i][5] = addressArray[i];
                            repaint();
                        }
                    });
                    queryThread1.start();
                    Thread queryThread2 = new Thread(() -> {
                        for (int i = (todayCount - todayCountRemainder)/2-1; i< todayCount - todayCountRemainder; i++) {
                            list1[i][0] = updateTimeArray[i];
                            list1[i][1] = numberArray[i];
                            list1[i][2] = nameArray[i];
                            list1[i][3] = cardArray[i];
                            list1[i][4] = telephoneArray[i];
                            list1[i][5] = addressArray[i];
                            repaint();
                        }
                    });
                    queryThread2.start();
                    Thread queryThreadRemainder = new Thread(() -> {
                        if (todayCountRemainder != 0) {
                            for (int i = todayCount - todayCountRemainder; i < todayCount; i++) {
                                list1[i][0] = updateTimeArray[i];
                                list1[i][1] = numberArray[i];
                                list1[i][2] = nameArray[i];
                                list1[i][3] = cardArray[i];
                                list1[i][4] = telephoneArray[i];
                                list1[i][5] = addressArray[i];
                                repaint();
                            }
                        }
                    });
                    queryThreadRemainder.start();
                    table1.setList(list1);
                    repaint();
                }

            } catch (FileNotFoundException e) {
                FileNotFoundExceptionDealing(e);
            } catch (IOException e) {
                IOExceptionDealing(e);
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        });
        historyRefresh.start();
    }

    void recommendInput(String colName,JTextField textField) {
        new Thread(()->{
            try {
                this.selectedTextField = textField;

                comboBox.removeAllItems();
                comboBox.setVisible(false);
                this.repaint();

                this.setLayout(null);
                comboBox.setBounds(selectedTextField.getX(), selectedTextField.getY() + selectedTextField.getHeight(), nameInput.getWidth(), 20);

                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                Compare compare = new Compare(colName,textField.getText() + "%","like");
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("updatetime", "ASC");
                Order order = new Order(hashMap);
                String[] results = select(url,user,password,"kdxxcx",colName, compare, order);

                for (String result : results) {
                    comboBox.addItem(result);
                }

                this.add(comboBox);
                comboBox.setVisible(true);
                repaint();

                comboBox.addActionListener(l -> {
                    documentChangeLock = false;
                    try {
                        Object selectedItem = comboBox.getSelectedItem();
                        assert selectedItem != null;
                        String selectedString = selectedItem.toString();
                        selectedTextField.setText(selectedString);
                    } catch (NullPointerException ignored) {

                    }
                    documentChangeLock = true;
                });
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        }).start();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.setLayout(this.gridBagLayout);
        changeFonts(this, (float) Math.max(Math.sqrt(e.getComponent().getWidth()) - 15, Math.sqrt(e.getComponent().getHeight()) - 15), notContainsComponents);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
