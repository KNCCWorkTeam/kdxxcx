package com.kdxxcx.programme.GUI;

import com.kdxxcx.util.Class.Equal;
import com.kdxxcx.util.Class.EqualArray;
import com.kdxxcx.util.Exception.AddFailException;
import com.kdxxcx.util.Exception.HadInfoException;
import com.kdxxcx.util.Exception.NotNullException;
import com.kdxxcx.util.SQL.Insert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.Properties;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Frame.changeFonts;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.Array.ArrayMax.arrayMax;
import static com.kdxxcx.util.Array.ArrayRead.arrayRead;
import static com.kdxxcx.util.Array.ArrayTransform.stringArrayTransformToIntArray;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.*;
import static com.kdxxcx.util.SQL.Select.select;
import static com.kdxxcx.util.String.AddBrackets.addBrackets;
import static com.kdxxcx.util.Time.GetTime.getDate;

public class AddGUI extends JFrame implements ComponentListener {
        Component[] notContainsComponents;
        JTextField nameInput;
        JTextField cardInput;
        JTextField telephoneInput;
        JTextField addressInput;

    public AddGUI(String name, String card, String telephone, String address) {
        super("新增");
        nameInput = new JTextField(name, 20);
        cardInput = new JTextField(card, 20);
        telephoneInput = new JTextField(telephone, 20);
        addressInput = new JTextField(address, 20);
        init();
    }

    void init(){
        //frame
        this.setLocationRelativeTo(null);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.addComponentListener(this);
        this.setLocationRelativeTo(null);

        JLabel name = new JLabel("姓名");
        JLabel card = new JLabel("发票号");
        JLabel telephone = new JLabel("联系电话");
        JLabel address = new JLabel("地址");
        JButton add = new JButton("新增");
        add.addActionListener(l->{
            add();
        });

        GridBagLayout gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        this.add(name);
        this.add(card);
        this.add(telephone);
        this.add(address);
        this.add(nameInput);
        this.add(cardInput);
        this.add(telephoneInput);
        this.add(addressInput);
        this.add(add);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 2, 1,12, 3,gridBagLayout,name);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,6, 0, 2, 1,12, 3,gridBagLayout,card);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 1, 2, 1,12, 3,gridBagLayout,telephone);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,6, 1, 2, 1,12, 3,gridBagLayout,address);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,2, 0, 4, 1,12, 3,gridBagLayout,nameInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,8, 0, 4, 1,12, 3,gridBagLayout,cardInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,2, 1, 4, 1,12, 3,gridBagLayout,telephoneInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,8, 1, 4, 1,12, 3,gridBagLayout,addressInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 2, 12, 1,12, 3,gridBagLayout,add);


        this.setVisible(true);
        notContainsComponents = new Component[]{add};
    }

    void add() {
        Thread add = new Thread(()->{
            try {
                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                EqualArray congruent = new EqualArray(new Equal[]{new Equal("hzxm", nameInput.getText()), new Equal("card", cardInput.getText()), new Equal("telephone", telephoneInput.getText()), new Equal("address", addressInput.getText())});
                if (select(url, user, password, "kdxxcx", "hzxm", congruent).length == 0) {
                    int nextNumber = arrayMax(stringArrayTransformToIntArray(select(url, user, password, "kdxxcx", "number")));
                    Insert.insert(url, user, password, "kdxxcx", new String[]{"number", "hzxm", "card", "telephone", "address", "updatetime"}, new String[]{addBrackets(String.valueOf(nextNumber + 1), "'"), addBrackets(nameInput.getText(), "'"), addBrackets(cardInput.getText(), "'"), addBrackets(telephoneInput.getText(), "'"), addBrackets(addressInput.getText(), "'"), addBrackets(getDate(), "'")});
                    if (check()) {
                        jOptionPanePrint("更改成功", "");
                        this.setVisible(false);
                    } else {
                        jOptionPanePrint("更改失败", "");
                        throw new AddFailException();
                    }
                } else {
                    throw new HadInfoException();
                }
            } catch (NotNullException e) {
                NotNullExceptionDealing(e);
            } catch (HadInfoException e) {
                HadInfoExceptionDealing(e);
            } catch (AddFailException e) {
                AddFailExceptionDealing(e);
            } catch (FileNotFoundException e) {
                FileNotFoundExceptionDealing(e);
            } catch (IOException e) {
                IOExceptionDealing(e);
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        });
        add.start();
    }

    boolean check() throws Exception {
        //get properties
        Properties properties = new Properties();
        properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Equal[] equals = new Equal[]{new Equal("hzxm", nameInput.getText()),new Equal("card", cardInput.getText()),new Equal("telephone", telephoneInput.getText()),new Equal("address", addressInput.getText())};
        EqualArray equalArray = new EqualArray(equals);
        String[] selectResult = select(url,user,password,"kdxxcx","number",equalArray);
        return selectResult.length != 0;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        changeFonts(this, (float) Math.max(  Math.sqrt(e.getComponent().getWidth()) - 10 ,  Math.sqrt(e.getComponent().getHeight()) - 10 ), notContainsComponents);
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
