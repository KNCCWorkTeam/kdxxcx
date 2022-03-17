package com.kdxxcx.util.Array;

public class ArrayMax {
    public static int arrayMax(int[] input) {
        int max = Integer.MIN_VALUE;
        for (int i : input) {
            max = Math.max(i, max);
        }
        return max;
    }
}
