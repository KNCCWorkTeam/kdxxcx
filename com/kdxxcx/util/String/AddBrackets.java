package com.kdxxcx.util.String;

public class AddBrackets {
    public static String addBrackets(String input,String brackets) {
        return brackets+input+brackets;
    }

    public static String addBrackets(String input,String brackets1,String brackets2) {
        return brackets1+input+brackets2;
    }

    public static String addBrackets(String input) {
        return "("+input+")";
    }
}
