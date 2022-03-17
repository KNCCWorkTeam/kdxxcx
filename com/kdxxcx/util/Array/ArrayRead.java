package com.kdxxcx.util.Array;

public class ArrayRead {
    public static String arrayRead (String[] input,String split) {
        StringBuilder output = new StringBuilder();
        for (int i=0;i<input.length;i++) {
            output.append(input[i]);
            if (!(i==input.length-1)) {
                output.append(split);
            }
        }
        return output.toString();
    }

    public static String arrayRead (String[][] input,String split,String splitBig) {
        StringBuilder output = new StringBuilder();
        for (int i=0;i<input[0].length;i++) {
            output.append(input[0][i]).append(split).append(input[1][i]);
            if (!(i==input[0].length-1)) {
                output.append(splitBig);
            }
        }
        return output.toString();
    }

    public static String arrayRead (String[] input) {
        return arrayRead(input, ",");
    }

    public static String arrayRead (String[][] input) {
        return arrayRead(input," ",",");
    }
}
