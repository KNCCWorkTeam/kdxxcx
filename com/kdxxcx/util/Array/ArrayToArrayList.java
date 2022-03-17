package com.kdxxcx.util.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToArrayList<T> {
    public ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public ArrayList<T> arrayAddToArrayList(ArrayList<T> arrayList,T[] array) {
        arrayList.addAll(Arrays.asList(array));
        return arrayList;
    }
}
