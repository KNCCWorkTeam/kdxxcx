package com.kdxxcx.programme.GUI;

import com.kdxxcx.util.File.FileReader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.kdxxcx.programme.ConstantAndVar.gridBagInset;
import static com.kdxxcx.util.AWTAndSwing.Layout.gridBagConstraintsSetSizeAndAdd;
import static com.kdxxcx.util.KDXXCX.ExceptionDealing.IOExceptionDealing;

public class AddressQueryGUI extends JFrame {
    JTextField resultName;
    JComboBox<String> provinceComboBox = new JComboBox<>();
    JComboBox<String> cityComboBox = new JComboBox<>();
    JComboBox<String> districtAndCountyComboBox = new JComboBox<>();
    JComboBox<String> roadComboBox = new JComboBox<>();
    JTextField roadNumber = new JTextField("门牌号");

    JTextField textField;

    boolean districtAndCountyLock = true;
    boolean cityLock = true;
    boolean roadLock = true;

    public AddressQueryGUI(JTextField textField) throws HeadlessException {
        super("地址查询");
        this.textField = textField;
        init();
    }


    void init(){
        new Thread(()-> {
            //frame
            this.setLocationRelativeTo(null);
            this.setSize(1000, 500);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setIconImage(new com.kdxxcx.util.File.Image().getImagePath("qunli.jpg"));


            try {
                new File(System.getProperty("user.dir")+"\\.area").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            resultName = new JTextField();
            resultName.setPreferredSize(new Dimension(resultName.getPreferredSize().width, 20));
            resultName.setEditable(false);

            JButton submit = new JButton("提交并插入");
            submit.addActionListener(l->{
                this.textField.setText(this.resultName.getText());
                this.setVisible(false);
            });

            GridBagLayout gridBagLayout=new GridBagLayout();
            this.setLayout(gridBagLayout);
            GridBagConstraints gridBagConstraints=new GridBagConstraints();
            gridBagConstraints.fill=GridBagConstraints.BOTH;
            gridBagConstraints.insets = new Insets(gridBagInset, gridBagInset, gridBagInset, gridBagInset);

            provinceComboBox.setEnabled(true);
            provinceComboBox.addItem("选择省级名称");
            for (String province:getAddress().keySet()) {
                provinceComboBox.addItem(province);
            }
            refresh();

            roadNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    setRoad(notNullString(provinceComboBox.getSelectedItem()),notNullString(cityComboBox.getSelectedItem()),notNullString(districtAndCountyComboBox.getSelectedItem()),notNullString(roadComboBox.getSelectedItem()));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    setRoad(notNullString(provinceComboBox.getSelectedItem()),notNullString(cityComboBox.getSelectedItem()),notNullString(districtAndCountyComboBox.getSelectedItem()),notNullString(roadComboBox.getSelectedItem()));
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });

            this.add(resultName);
            this.add(provinceComboBox);
            this.add(cityComboBox);
            this.add(districtAndCountyComboBox);
            this.add(roadComboBox);
            this.add(roadNumber);
            this.add(submit);


            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 0, 2, 1,2, 5,gridBagLayout,resultName);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 1, 1, 1,2, 5,gridBagLayout,provinceComboBox);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 1, 1, 1,2, 5,gridBagLayout,cityComboBox);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 2, 1, 1,2, 5,gridBagLayout,districtAndCountyComboBox);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,1, 2, 1, 1,2, 5,gridBagLayout,roadComboBox);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 3, 2, 1,2, 5,gridBagLayout,roadNumber);
            gridBagConstraintsSetSizeAndAdd(gridBagConstraints,0, 4, 2, 1,2, 5,gridBagLayout,submit);

            this.setVisible(true);
        }).start();
    }

    void refresh() {
        cityComboBox.setEnabled(false);
        districtAndCountyComboBox.setEnabled(false);
        roadComboBox.setEnabled(false);
        provinceComboBox.addItemListener(l->{

            cityLock = false;
            districtAndCountyLock = false;
            roadLock = false;
            cityComboBox.setEnabled(false);
            districtAndCountyComboBox.setEnabled(false);
            roadComboBox.setEnabled(false);
            cityComboBox.removeAllItems();
            districtAndCountyComboBox.removeAllItems();
            roadComboBox.removeAllItems();
            cityLock = true;
            districtAndCountyLock = true;
            roadLock = true;

            cityComboBox.setEnabled(true);
            cityLock = false;
            cityComboBox.removeAllItems();
            cityComboBox.addItem("选择市级名称");
            for (String city:getAddress().get(provinceComboBox.getSelectedItem().toString()).keySet()) {
                cityComboBox.addItem(city);
            }
            cityLock = true;
            setRoad(notNullString(provinceComboBox.getSelectedItem()),notNullString(cityComboBox.getSelectedItem()),notNullString(districtAndCountyComboBox.getSelectedItem()),notNullString(roadComboBox.getSelectedItem()));
        });
        cityComboBox.addItemListener(l->{
            if (cityLock) {

                districtAndCountyLock = false;
                roadLock = false;
                districtAndCountyComboBox.setEnabled(false);
                roadComboBox.setEnabled(false);
                districtAndCountyComboBox.removeAllItems();
                roadComboBox.removeAllItems();
                districtAndCountyLock = true;
                roadLock = true;

                districtAndCountyComboBox.setEnabled(true);
                districtAndCountyLock = false;
                districtAndCountyComboBox.removeAllItems();
                districtAndCountyComboBox.addItem("选择区级名称");
                for (String districtAndCounty:getAddress().get(provinceComboBox.getSelectedItem().toString()).get(cityComboBox.getSelectedItem().toString()).keySet()) {
                    districtAndCountyComboBox.addItem(districtAndCounty);
                }
                districtAndCountyLock = true;
                setRoad(notNullString(provinceComboBox.getSelectedItem()), notNullString(cityComboBox.getSelectedItem()), notNullString(districtAndCountyComboBox.getSelectedItem()), notNullString(roadComboBox.getSelectedItem()));
            }
        });
        districtAndCountyComboBox.addItemListener(l->{
            if (districtAndCountyLock) {

                roadLock = false;
                roadComboBox.setEnabled(false);
                roadComboBox.removeAllItems();
                roadLock = true;

                roadComboBox.setEnabled(true);
                roadLock = false;
                roadComboBox.removeAllItems();
                roadComboBox.addItem("选择路名");
                for (String road:getAddress().get(provinceComboBox.getSelectedItem().toString()).get(cityComboBox.getSelectedItem().toString()).get(districtAndCountyComboBox.getSelectedItem().toString())) {
                    roadComboBox.addItem(road);
                }
                roadLock = true;
                setRoad(notNullString(provinceComboBox.getSelectedItem()), notNullString(cityComboBox.getSelectedItem()), notNullString(districtAndCountyComboBox.getSelectedItem()), notNullString(roadComboBox.getSelectedItem()));
            }
        });
        roadComboBox.addActionListener(l->{
            if (roadLock) {
                setRoad(notNullString(provinceComboBox.getSelectedItem()), notNullString(cityComboBox.getSelectedItem()), notNullString(districtAndCountyComboBox.getSelectedItem()), notNullString(roadComboBox.getSelectedItem()));
            }
        });
    }

    void setRoad(String province,String city,String districtAndCounty,String road) {
        String provinceString = "";
        if (province!=null&&!province.equals("选择省级名称")) {
            if (!province.equals("直辖")&&!province.equals("无")) {
                if (province.contains("省")) {
                    provinceString = province;
                } else {
                    provinceString = province + "省";
                }
            }
        }

        String cityString = "";
        if (city!=null&&!city.equals("选择市级名称")) {
            if (!city.equals("无")){
                if (city.contains("市")) {
                    cityString = city;
                } else {
                    cityString = city + "市";
                }
            }
        }
        String districtAndCountyString = "";
        if (districtAndCounty!=null&&!districtAndCounty.equals("选择区级名称")) {
            if (!districtAndCounty.equals("无")) {
                if (districtAndCounty.contains("区") || districtAndCounty.contains("县")) {
                    districtAndCountyString = districtAndCounty;
                } else {
                    districtAndCountyString = districtAndCounty + "区";
                }
            }
        }
        String roadString = "";
        if (road!=null&&!road.equals("选择路名")) {
            if (!road.equals("无")) {
                if (road.contains("路")) {
                    roadString = road;
                } else {
                    roadString = road + "路";
                }
            }
        }
        String roadNumberString = "";
        if (!roadNumber.getText().equals("")&&!roadNumber.getText().equals("门牌号")) {
            roadNumberString = roadNumber.getText() + "号";
        }
        this.resultName.setText(provinceString+cityString+districtAndCountyString+roadString+roadNumberString);
        
    }

    String notNullString(Object input) {
        if (input==null) {
            return "";
        } else {
            return input.toString();
        }
    }

    HashMap<String,HashMap<String,HashMap<String,String[]>>> getAddress() {
        try {
            HashMap<String, HashMap<String, HashMap<String, String[]>>> provincesMap = new HashMap<>();
            FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\.area");
            for (String fileResult : fileReader.readResult()) {
                String provinceName = fileResult;
                String[] cities = new String[]{"无"};
                if (fileResult.contains("{") && fileResult.contains("}")) {
                    int minProvince = getMinInt(indexOfChar(fileResult, '{'));
                    int maxProvince = getMaxInt(indexOfChar(fileResult, '}'));
                    provinceName = fileResult.substring(0, minProvince);
                    cities = fileResult.substring(minProvince + 1, maxProvince).split(",");
                }

                HashMap<String, HashMap<String, String[]>> citiesMap = new HashMap<>();
                for (String city : cities) {
                    String cityName = city;
                    String[] districtsAndCounties = new String[]{"无"};
                    if (city.contains("{") && city.contains("}")) {
                        int minProvince = getMinInt(indexOfChar(city, '{'));
                        int maxProvince = getMaxInt(indexOfChar(city, '}'));
                        cityName = city.substring(0, minProvince);
                        districtsAndCounties = city.substring(minProvince + 1, maxProvince).split("#");
                    }

                    HashMap<String, String[]> districtsAndCountiesMap = new HashMap<>();
                    for (String districtAndCounty : districtsAndCounties) {
                        String districtAndCountyName = districtAndCounty;
                        String[] roads = new String[]{"无"};
                        if (districtAndCounty.contains("{") && districtAndCounty.contains("}")) {
                            int minProvince = getMinInt(indexOfChar(districtAndCounty, '{'));
                            int maxProvince = getMaxInt(indexOfChar(districtAndCounty, '}'));
                            districtAndCountyName = districtAndCounty.substring(0, minProvince);
                            roads = districtAndCounty.substring(minProvince + 1, maxProvince).split("\\$");
                        }
                        String[] roadsMap = roads;
                        districtsAndCountiesMap.put(districtAndCountyName, roadsMap);
                    }
                    citiesMap.put(cityName, districtsAndCountiesMap);
                }
                provincesMap.put(provinceName, citiesMap);
            }
            return provincesMap;
        } catch (IOException e) {
            IOExceptionDealing(e);
        }
        return null;
    }

    public static int getMaxInt(Integer[] input) {
        if (input.length==0) {
            return 0;
        }
        int maxInt = input[0];
        for (int inputInt : input) {
            if (inputInt>maxInt) {
                maxInt = inputInt;
            }
        }
        return maxInt;
    }

    public static int getMinInt(Integer[] input) {
        if (input.length==0) {
            return 0;
        }
        int minInt = input[0];
        for (int inputInt : input) {
            if (inputInt<minInt) {
                minInt = inputInt;
            }
        }
        return minInt;
    }

    public static Integer[] indexOfChar(String input, char split) {
        ArrayList<Integer> output = new ArrayList<>();
        char[] chars = input.toCharArray();
        for (int i = 0;i<chars.length;i++) {
            if (chars[i]==split) {
                output.add(i);
            }
        }
        return output.toArray(new Integer[0]);
    }
}
