package com.kdxxcx.util.SQL;

import com.kdxxcx.util.Class.Compare;
import com.kdxxcx.util.Class.CompareArray;

import static com.kdxxcx.util.SQL.General.insertInto;

public class Delete {
    public static void delete(String url,String user,String password,String tableName) throws Exception{
        insertInto(url,user,password,"delete from " + tableName);
    }

    public static void delete(String url, String user, String password, String tableName, Compare where) throws Exception{
        insertInto(url,user,password,"delete from " + tableName + " where " + where.toString());
    }

    public static void delete(String url, String user, String password, String tableName, CompareArray where) throws Exception{
        insertInto(url,user,password,"delete from " + tableName + " where " + where.toString());
    }
}
