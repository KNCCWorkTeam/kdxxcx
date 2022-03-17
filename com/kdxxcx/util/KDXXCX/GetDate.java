package com.kdxxcx.util.KDXXCX;

import java.util.Calendar;

public class GetDate {
    public static long getDate() {
        Calendar calendar = Calendar.getInstance();
        return Long.parseLong(calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DATE) + "" + calendar.get(Calendar.HOUR) + "" + calendar.get(Calendar.MINUTE) + "" + calendar.get(Calendar.SECOND) + "" + calendar.get(Calendar.MILLISECOND));
    }
}
