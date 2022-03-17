package com.kdxxcx.util.String;

public class RemoveBrackets {
    public static String removeBrackets(String input,String removeLeftBracket,String removeRightBracket) {
        return input.replace(removeLeftBracket,"").replace(removeRightBracket,"");
    }
}
