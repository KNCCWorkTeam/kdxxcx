package com.kdxxcx.util.Class;

import java.util.HashMap;

import static com.kdxxcx.util.Array.ArrayRead.arrayRead;
import static com.kdxxcx.util.Array.HashMapRead.hashMapRead;

public class Order {
    private HashMap hashMap;
    public Order(HashMap hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    public String toString() {
        return arrayRead(hashMapRead(hashMap));
    }
}
