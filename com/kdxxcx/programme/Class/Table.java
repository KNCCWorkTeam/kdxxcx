package com.kdxxcx.programme.Class;

import com.kdxxcx.programme.ConstantAndVar;
import com.kdxxcx.programme.GUI.ChangeGUI;
import com.kdxxcx.util.Exception.NoXiaDianException;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.NoXiaDianExceptionDealing;

public class Table extends JPanel{
    JTable table;
    JTableHeader tableHeader;
    JButton[] insertButtons;
    String[][] list;
    String[] column;
    JScrollPane scrollPane1;

    public Table(JButton[] insertButtons,String[][] list,String[] column) {
        super(new GridBagLayout());
        this.insertButtons = insertButtons;
        this.list = list;
        this.column = column;

        GridBagLayout gridBagLayoutTable1=new GridBagLayout();
        this.setLayout(gridBagLayoutTable1);
        this.setPreferredSize(new Dimension(500,21984));
        GridBagConstraints gridBagConstraintsTable1=new GridBagConstraints();
        gridBagConstraintsTable1.fill=GridBagConstraints.BOTH;
        gridBagConstraintsTable1.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);


        table = new JTable(list, column);
        table.setRowHeight(22);
        table.setFont(table.getFont().deriveFont(20f));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(75);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (ConstantAndVar.selectedTable!=null&& ConstantAndVar.selectedTable!=table) {
                ConstantAndVar.selectedTable.clearSelection();
            }
            ConstantAndVar.selectedTable = table;
        });
        this.add(table);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraintsTable1,0, 1, 8, 1000,10, 1001, 0, 10, 10, 10, gridBagLayoutTable1,table);
        tableHeader = table.getTableHeader();
        this.add(tableHeader);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraintsTable1,0, 0, 8, 1,10, 1001, 10, 10, 0, 10, gridBagLayoutTable1,tableHeader);
        scrollPane1 = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(10, 10, 175, 90);
        for(int i=0;i<1000;i++) {
            if (i == 0) {
                insertButtons[i] = new JButton("-");
                insertButtons[i].addActionListener(l -> {
                    try {
                        throw new NoXiaDianException();
                    } catch (NoXiaDianException e) {
                        NoXiaDianExceptionDealing(e);
                    }
                });
            } else {
                insertButtons[i] = new JButton("更改");
                int finalI = i-1;
                insertButtons[i].addActionListener(l -> {
                    try {
                        insert(finalI);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            this.add(insertButtons[i]);
            insertButtons[i].setFont(insertButtons[i].getFont().deriveFont(15f));
            //insertButtons[i].setPreferredSize(new Dimension(insertButtons[i].getWidth(), 20));
            gridBagConstraintsSetSizeAndAdd(gridBagConstraintsTable1,8, i, 1, 1,10, 1001, 2, 0, 2, 0, gridBagLayoutTable1,insertButtons[i]);
        }
    }

    public JScrollPane output() {
        return scrollPane1;
    }

    public void insert(int input){
        new ChangeGUI(list[input][1],list[input][2],list[input][3],list[input][4]);
    }

    public void setList(String[][] list) {
        this.list = list;
    }

    public String[][] getList() {
        return list;
    }
}
