package com.kdxxcx.util.SQL;

import com.kdxxcx.util.Class.Compare;
import com.kdxxcx.util.Class.CompareArray;
import com.kdxxcx.util.Class.Equal;

import static com.kdxxcx.util.SQL.General.insertInto;

public class Update {
    public static void update(String url, String user, String password, String tableName, Compare compare) throws Exception{
        insertInto(url, user, password, "update " + tableName + " set " + compare.toString());
    }

    public static void update(String url, String user, String password, String tableName, Equal equal, Compare where) throws Exception{
        System.out.println("update " + tableName + " set " + equal.toString() + " where " + where.toString());
        insertInto(url, user, password, "update " + tableName + " set " + equal.toString() + " where " + where.toString());
    }

    public static void update(String url, String user, String password, String tableName, Equal equal, CompareArray where) throws Exception{
        insertInto(url, user, password, "update " + tableName + " set " + equal.toString() + " where " + where.toString());
    }
}
