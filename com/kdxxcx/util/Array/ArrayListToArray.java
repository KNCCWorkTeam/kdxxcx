package com.kdxxcx.util.Array;

import java.util.ArrayList;

public class ArrayListToArray {
    public static String[] arrayListToArray(ArrayList<String> arrayList) {
        if (arrayList.size()==0) {
            System.out.println("0!!!!");
            return new String[0];
        } else {
            int time = arrayList.size();
            String[] resultArray = new String[time];
            for (int i = 0; i < time; i++) {
                resultArray[i] = arrayList.get(i);
            }
            return resultArray;
        }
    }
}
