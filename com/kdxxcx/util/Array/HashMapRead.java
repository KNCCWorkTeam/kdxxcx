package com.kdxxcx.util.Array;

import java.util.HashMap;

import static com.kdxxcx.util.String.RemoveBrackets.removeBrackets;

public class HashMapRead {
    public static String[][] hashMapRead(HashMap hashMap) {
        String[][] output = new String[2][hashMap.size()];
        int arrayTime = 0;
        for (String firstSplit : removeBrackets(hashMap.toString(), "{", "}").split(",")) {
            output[0][arrayTime] = firstSplit.split("=")[0];
            output[1][arrayTime] = firstSplit.split("=")[1];
            arrayTime++;
        }
        return output;
    }
}
