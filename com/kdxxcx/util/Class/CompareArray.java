package com.kdxxcx.util.Class;

import static com.kdxxcx.util.Array.ArrayRead.arrayRead;
import static com.kdxxcx.util.Array.ArrayToString.arrayToString;

public class CompareArray {
    Compare[] compare;
    public CompareArray(Compare[] compares) {
        this.compare = compares;
    }

    @Override
    public String toString() {
        return arrayRead(arrayToString(compare)," and ");
    }
}
