package com.kdxxcx.util.Time;

import java.util.Calendar;

import static com.kdxxcx.util.String.FullVar.fullVarFront;
import static java.lang.String.valueOf;

public class GetTime {
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return fullVarFront(valueOf(calendar.get(Calendar.YEAR)), 4) + "-" + fullVarFront(valueOf(calendar.get(Calendar.MONTH) + 1), 2) + "-" + fullVarFront(valueOf(calendar.get(Calendar.DATE)), 2);
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        return fullVarFront(valueOf(calendar.get(Calendar.YEAR)), 4) + "-" + fullVarFront(valueOf(calendar.get(Calendar.MONTH) + 1), 2) + "-" + fullVarFront(valueOf(calendar.get(Calendar.DATE)), 2) + " " + fullVarFront(valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2) + ":" + fullVarFront(valueOf(calendar.get(Calendar.MINUTE)), 2) + ":" + fullVarFront(valueOf(calendar.get(Calendar.SECOND)), 2);
    }
}
