package com.kdxxcx.util.String;

public class FullVar {
    public static String fullVarFront(String intInput, int intLength) {
        StringBuilder stringBuilder = new StringBuilder();
        if (intInput.length() < intLength) {
            for(int i = 0; i < intLength - intInput.length(); ++i) {
                stringBuilder.insert(0, "0");
            }
        }
        return stringBuilder + intInput;
    }

    public static String fullVarFront(String intInput, int intLength, String fullVarWith) {
        StringBuilder stringBuilder = new StringBuilder();
        if (intInput.length() < intLength) {
            for(int i = 0; i < intLength - intInput.length(); ++i) {
                stringBuilder.insert(0, fullVarWith);
            }
        }
        return stringBuilder + intInput;
    }
}
