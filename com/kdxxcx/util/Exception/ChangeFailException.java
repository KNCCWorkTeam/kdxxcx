package com.kdxxcx.util.Exception;

import static com.kdxxcx.programme.ConstantAndVar.CHANGEEXCEPTIONINT;

public class ChangeFailException extends OperateFailException{
    public ChangeFailException() {
        super();
    }

    public ChangeFailException(String message,String name,String card,String telephone,String address) {
        super(message,CHANGEEXCEPTIONINT,name,card,telephone,address);
    }
}
