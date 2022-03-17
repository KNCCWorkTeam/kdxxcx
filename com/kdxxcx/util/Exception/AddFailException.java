package com.kdxxcx.util.Exception;

import static com.kdxxcx.programme.ConstantAndVar.ADDEXCEPTIONINT;

public class AddFailException extends OperateFailException{
    public AddFailException() {
        super();
    }

    public AddFailException(String message,String name,String card,String telephone,String address) {
        super(message,ADDEXCEPTIONINT,name,card,telephone,address);
    }
}
