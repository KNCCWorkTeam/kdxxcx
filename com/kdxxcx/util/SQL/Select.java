package com.kdxxcx.util.SQL;

import com.kdxxcx.util.Class.Compare;
import com.kdxxcx.util.Class.CompareArray;
import com.kdxxcx.util.Class.Equal;
import com.kdxxcx.util.Class.Order;

import java.sql.ResultSet;

import static com.kdxxcx.util.SQL.General.selectOut;
import static com.kdxxcx.util.SQL.ResultSetAnalyze.resultSetAnalyze;
import static com.kdxxcx.util.String.AddBrackets.addBrackets;

public class Select {
    //normal select
    public static String[] select(String url, String user, String password, String tableName, String colName) throws Exception {
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName), colName);
    }

    public static String[] select(String url, String user, String password, String tableName, String colName, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName + " where " + where.toString()),colName);
    }

    public static String[] select(String url, String user, String password, String tableName, String colName, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName + " order by " + orderBy.toString()),colName);
    }
    
    public static String[] select(String url, String user, String password, String tableName, String colName, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),colName);
    }

    public static String[] select(String url, String user, String password, String tableName, String colName, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName + " where " + where),colName);
    }

    public static String[] select(String url, String user, String password, String tableName, String colName, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),colName);
    }

    //select join
    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal),colName);
    }

    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),colName);
    }

    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + " order by " + orderBy.toString()),colName);
    }

    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),colName);
    }

    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),colName);
    }

    public static String[] select(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),colName);
    }

    //normal distinct select
    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName), colName);
    }

    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " where " + where.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " order by " + orderBy.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " where " + where.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, String tableName, String colName, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),colName);
    }

    //join select distinct
    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + " order by " + orderBy.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),colName);
    }

    public static String[] selectDistinct(String url, String user, String password, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),colName);
    }



    //normal select
    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName) throws Exception {
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName), asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName + " where " + where.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName, CompareArray where) throws Exception{
        //System.out.println("select " + colName +  " as " + asName + " from " + tableName + " where " + where);
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName + " where " + where),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, String tableName, String colName, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName +  " as " + asName + " from " + tableName + " where " + where.toString() + " order by " + orderBy.toString()),asName);
    }

    //select join
    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),asName);
    }

    public static String[] selectAs(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select " + colName + " as " + asName + " from " + tableName1 + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),asName);
    }



    //normal distinct select
    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName), asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName + " where " + where.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName + " where " + where.toString() + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName + " where " + where.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, String tableName, String colName, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName + " as " + asName + " where " + where.toString() + " order by " + orderBy.toString()),asName);
    }

    //join select distinct
    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, Compare where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString()),asName);
    }

    public static String[] selectAsDistinct(String url, String user, String password, String asName, state state, String tableName1, String tableName2, String colName, Equal equal, CompareArray where, Order orderBy) throws Exception{
        return resultSetAnalyze(selectOut(url, user, password, "select distinct " + colName + " from " + tableName1 + " as " + asName + addBrackets(stateToString(state), " ", " ") + tableName2 + "on" + equal + "where" + where.toString() + " order by " + orderBy.toString()),asName);
    }



    public static String stateToString(state state) {
        if (state==Select.state.DISTINCT) {
            return "distinct";
        } else if (state==Select.state.INNER) {
            return "inner join";
        } else if (state==Select.state.LEFTOUTE) {
            return "left outer join";
        } else if (state==Select.state.RIGHTOUTER) {
            return "right outer join";
        } else if (state==Select.state.FULLOUTER) {
            return "full outer join";
        }
        return null;
    }

    public enum state{
        DISTINCT,INNER,LEFTOUTE,RIGHTOUTER,FULLOUTER;
    }
}