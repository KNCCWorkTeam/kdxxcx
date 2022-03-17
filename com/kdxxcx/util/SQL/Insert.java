package com.kdxxcx.util.SQL;

import static com.kdxxcx.util.SQL.General.insertInto;
import static com.kdxxcx.util.String.AddBrackets.addBrackets;
import static com.kdxxcx.util.Array.ArrayRead.arrayRead;

public class Insert {
    public static void insert(String url,String user,String password,String tableName,String[] addRow) throws Exception{
        insertInto(url, user, password, "insert into " + tableName + " values " + addBrackets(arrayRead(addRow)));
    }

    public static void insert(String url,String user,String password,String tableName,String[] col,String[] addRow) throws Exception{
        insertInto(url, user, password, "insert into " + tableName + " " + addBrackets(arrayRead(col)) + " values " + addBrackets(arrayRead(addRow)));
    }

}
