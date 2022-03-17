package com.kdxxcx.util.Exception;

import static com.kdxxcx.util.Array.ArrayRead.arrayRead;

public class ArrayLengthNotCongruent extends Exception{
    public ArrayLengthNotCongruent(String[][] ArrayInput, String message) {
        super(message + arrayRead(ArrayInput));
    }

    public ArrayLengthNotCongruent() {
    }

    public ArrayLengthNotCongruent(String message) {
        super(message);
    }

    public ArrayLengthNotCongruent(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayLengthNotCongruent(Throwable cause) {
        super(cause);
    }

    public ArrayLengthNotCongruent(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
