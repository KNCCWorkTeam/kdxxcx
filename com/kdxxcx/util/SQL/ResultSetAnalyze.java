package com.kdxxcx.util.SQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.kdxxcx.util.Array.ArrayListToArray.arrayListToArray;

public class ResultSetAnalyze {
    public static String[] resultSetAnalyze(ResultSet resultSet,String colName) throws Exception{
        if (resultSet==null) {
            return new String[0];
        }
        ArrayList<String> resultReturn = new ArrayList<>();
        while (resultSet.next()) {
            resultReturn.add(resultSet.getString(colName).trim());
        }
        return arrayListToArray(resultReturn);
    }
}
