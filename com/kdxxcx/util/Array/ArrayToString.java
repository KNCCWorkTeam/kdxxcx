package com.kdxxcx.util.Array;

import com.kdxxcx.util.Class.Compare;

public class ArrayToString {
    public static String[] arrayToString(Compare[] input) {
        String[] output = new String[input.length];
        for (int i = 0;i< input.length;i++) {
            output[i] = input[i].toString();
        }
        return output;
    }
}
