package com.kdxxcx.util.Array;


public class ArrayTransform {
    public static int[] stringArrayTransformToIntArray(String[] input) {
        int[] output = new int[input.length];
        for (int i=0;i<input.length;i++) {
            try {
                output[i] = Integer.parseInt(input[i].trim());
            } catch (Exception ignored) {
            }
        }
        return output;
    }
}
