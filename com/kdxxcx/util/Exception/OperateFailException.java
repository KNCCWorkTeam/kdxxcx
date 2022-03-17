package com.kdxxcx.util.Exception;

public class OperateFailException extends Exception{
    public OperateFailException() {
        exceptionOperate(0,null,null,null,null);
    }

    public OperateFailException(String message,int number,String name,String card,String telephone,String address) {
        super(message);
        exceptionOperate(number,name,card,telephone,address);
    }

    void exceptionOperate(int number,String name,String card,String telephone,String address) {
        //todo
    }
}
