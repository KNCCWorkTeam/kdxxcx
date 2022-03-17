package com.kdxxcx.util.Class;

public class NullQuotationMarkCompare extends Compare{
    public NullQuotationMarkCompare(String col, String info, String symbols) {
        super(col, info, symbols);
    }

    public NullQuotationMarkCompare(String col, String info) {
        super(col, info);
    }

    @Override
    public String toString() {
        return col + " " + symbols + " " + info;
    }
}
