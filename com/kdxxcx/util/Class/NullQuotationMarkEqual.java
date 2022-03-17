package com.kdxxcx.util.Class;

public class NullQuotationMarkEqual extends Equal{

    public NullQuotationMarkEqual(String input1, String input2) {
        super(input1, input2);
    }

    @Override
    public String toString() {
        return col + " " + symbols + " " + info;
    }
}
