package com.kdxxcx.util.KDXXCX;

public class DealDate {
    public static long dealDate(long input) {
        long dealLong = input << 10;
        if (dealLong+"".length()<=19) {
            dealLong = Long.parseLong(dealLong + "0");
        }
        return dealLong;
    }
}
