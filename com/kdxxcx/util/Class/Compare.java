package com.kdxxcx.util.Class;

public class Compare {
    String col;
    String info;
    String symbols;
    public Compare(String col, String info, String symbols) {
        this.col=col;
        this.info=info;
        this.symbols=symbols;
    }

    public Compare(String col, String info) {
        this.col=col;
        this.info=info;
        this.symbols="=";
    }

    @Override
    public String toString() {
        return col + " " + symbols + " '" + info + "'";
    }
}
