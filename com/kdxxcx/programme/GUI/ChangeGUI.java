package com.kdxxcx.programme.GUI;

import com.kdxxcx.util.Class.Compare;
import com.kdxxcx.util.Class.CompareArray;
import com.kdxxcx.util.Class.Equal;
import com.kdxxcx.util.Class.EqualArray;
import com.kdxxcx.util.Exception.ChangeFailException;
import com.kdxxcx.util.Exception.HadInfoException;
import com.kdxxcx.util.SQL.Select;
import com.kdxxcx.util.SQL.Update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.Properties;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Frame.changeFonts;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.*;
import static com.kdxxcx.util.SQL.Select.select;
import static com.kdxxcx.util.Time.GetTime.getDate;
import static java.lang.String.valueOf;

public class ChangeGUI extends JFrame implements ComponentListener {
    String name;
    String card;
    String telephone;
    String address;
    Component[] notContainsComponents;
    JTextField nameInput;
    JTextField cardInput;
    JTextField telephoneInput;
    JTextField addressInput;

    public ChangeGUI(String name, String card, String telephone, String address) {
        super("更改");
        this.name = name;
        this.card = card;
        this.telephone = telephone;
        this.address = address;
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
        this.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));


        JLabel nameOriginal = new JLabel("更改前的名字");
        JLabel cardOriginal = new JLabel("更改前的发票号");
        JLabel telephoneOriginal = new JLabel("更改前的电话");
        JLabel addressOriginal = new JLabel("更改前的地址");
        JLabel nameInputOriginal = new JLabel(name);
        JLabel cardInputOriginal = new JLabel(card);
        JLabel telephoneInputOriginal = new JLabel(telephone);
        JLabel addressInputOriginal = new JLabel(address);

        JLabel name = new JLabel("名字");
        JLabel card = new JLabel("发票号");
        JLabel telephone = new JLabel("电话");
        JLabel address = new JLabel("地址");
        JButton change = new JButton("确认更改");
        change.addActionListener(l->{
            change();
        });

        GridBagLayout gridBagLayout=new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

        this.add(nameOriginal);
        this.add(cardOriginal);
        this.add(telephoneOriginal);
        this.add(addressOriginal);
        this.add(nameInputOriginal);
        this.add(cardInputOriginal);
        this.add(telephoneInputOriginal);
        this.add(addressInputOriginal);
        this.add(name);
        this.add(card);
        this.add(telephone);
        this.add(address);
        this.add(nameInput);
        this.add(cardInput);
        this.add(telephoneInput);
        this.add(addressInput);
        this.add(change);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 1, 1, 1,12, 8,gridBagLayout,nameOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 1, 2, 1,12, 8,gridBagLayout,nameInputOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 1, 1, 1,12, 8,gridBagLayout,cardOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 1, 8, 1,12, 8,gridBagLayout,cardInputOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 2, 1, 1,12, 8,gridBagLayout,telephoneOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 2, 2, 1,12, 8,gridBagLayout,telephoneInputOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 2, 1, 1,12, 8,gridBagLayout,addressOriginal);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 2, 8, 1,12, 8,gridBagLayout,addressInputOriginal);

        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 3, 1, 1,12, 8,gridBagLayout,name);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 3, 2, 1,12, 8,gridBagLayout,nameInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 3, 1, 1,12, 8,gridBagLayout,card);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 3, 8, 1,12, 8,gridBagLayout,cardInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 5, 1, 1,12, 8,gridBagLayout,telephone);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 5, 2, 1,12, 8,gridBagLayout,telephoneInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,3, 5, 1, 1,12, 8,gridBagLayout,address);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,4, 5, 8, 1,12, 8,gridBagLayout,addressInput);
        gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 7, 12, 1,12, 8,gridBagLayout,change);


        this.setVisible(true);
        notContainsComponents = new Component[]{change};
    }

    void change() {
        Thread change = new Thread(()->{
            try {
                //get properties
                Properties properties = new Properties();
                properties.load(new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + "/info.properties")));
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                EqualArray congruent = new EqualArray(new Equal[]{new Equal("hzxm", nameInput.getText()),new Equal("card", cardInput.getText()),new Equal("telephone",telephoneInput.getText()),new Equal("address",addressInput.getText())});
                if (select(url, user, password,  "kdxxcx","hzxm", congruent).length==0) {
                    int number = Integer.parseInt(Select.select(url,user,password,"kdxxcx","number", new CompareArray(new Compare[]{new Compare("hzxm", "%" + name + "%", "like"), new Compare("card", "%" + card + "%", "like"), new Compare("telephone", "%" + telephone + "%", "like"), new Compare("address", "%" + address + "%", "like")}))[0]);
                    Update.update(url,user,password,"kdxxcx", new Equal("hzxm",nameInput.getText()), new Equal("number",valueOf(number)));
                    Update.update(url,user,password,"kdxxcx", new Equal("card",cardInput.getText()), new Equal("number",valueOf(number)));
                    Update.update(url,user,password,"kdxxcx", new Equal("telephone",telephoneInput.getText()), new Equal("number",valueOf(number)));
                    Update.update(url,user,password,"kdxxcx", new Equal("address",addressInput.getText()), new Equal("number",valueOf(number)));
                    Update.update(url,user,password,"kdxxcx", new Equal("updatetime",getDate()), new Equal("number",valueOf(number)));
                    if (changeCheck()) {
                        jOptionPanePrint("更改成功","");
                        this.setVisible(false);
                    } else {
                        jOptionPanePrint("更改失败","");
                        throw new ChangeFailException();
                    }
                } else {
                    throw new HadInfoException();
                }
            } catch (HadInfoException e) {
                HadInfoExceptionDealing(e);
            } catch (FileNotFoundException e) {
                FileNotFoundExceptionDealing(e);
            } catch (IOException e) {
                IOExceptionDealing(e);
            } catch (ChangeFailException e) {
                ChangeFailExceptionDealing(e);
            } catch (Exception e) {
                AllExceptionDealing(e);
            }
        });
        change.start();
    }

    boolean changeCheck() throws Exception {
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
        changeFonts(this, (float) Math.max(Math.sqrt(e.getComponent().getWidth()) - 10 ,  Math.sqrt(e.getComponent().getHeight()) - 10 ), notContainsComponents);
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
