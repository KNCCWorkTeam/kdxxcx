package com.kdxxcx.util.KDXXCX;

import com.kdxxcx.programme.ConstantAndVar;
import com.kdxxcx.util.Exception.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class ExceptionDealing {
    public static void IOExceptionDealing(IOException e) {
        jOptionPanePrint(e.toString());
    }

    public static void FileNotFoundExceptionDealing(FileNotFoundException e) {
        jOptionPanePrint(e.toString());
    }

    public static void ArrayLengthNotCongruentExceptionDealing(ArrayLengthNotCongruent e) {
        jOptionPanePrint(e.toString());
    }

    public static void AddFailExceptionDealing(AddFailException e) {
        jOptionPanePrint(e.toString());
    }

    public static void ChangeFailExceptionDealing(ChangeFailException e) {
        jOptionPanePrint(e.toString());
    }

    public static void NotNullExceptionDealing(NotNullException e) {
        jOptionPanePrint(e.toString(),"输入不能为空");
    }

    public static void HadInfoExceptionDealing(HadInfoException e) {
        jOptionPanePrint(e.toString(),"已有该信息");
    }

    public static void TooMuchQueryResultExceptionDealing(TooMuchQueryResultException e) {
        jOptionPanePrint(e.toString(), "该结果大于等于两个，无法直接更改");
    }

    public static void NoXiaDianExceptionDealing(NoXiaDianException e) {
    }

    public static void SQLExceptionDealing(SQLException e) {
        if (ConstantAndVar.debugMode) {
            jOptionPanePrint("未连接数据库，请检查连接或是否正确配置properties文件" + "\n" + e);
        } else {
            System.out.println("未连接数据库，请检查连接或是否正确配置properties文件");
        }
    }

    public static void AllExceptionDealing(Exception e) {
        jOptionPanePrint(e.toString());
    }

    public static void jOptionPanePrint(String info,String title) {
        JOptionPane.showConfirmDialog(null, info, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public static void jOptionPanePrint(String info) {
        JOptionPane.showConfirmDialog(null, info, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
