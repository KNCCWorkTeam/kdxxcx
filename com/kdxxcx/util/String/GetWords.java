package com.kdxxcx.util.String;

public class GetWords {
    public static String getWords(String input,int intInput) {
        return input.split(" ")[intInput-1];
    }

    public static int getWordsCount(String input) {
        return input.split(" ").length;
    }
}
