package com.kdxxcx.util.Exception;

import static com.kdxxcx.util.Array.ArrayRead.arrayRead;

public class TooMuchQueryResultException extends Exception {
    public TooMuchQueryResultException() {
    }

    public TooMuchQueryResultException(String message) {
        super(message);
    }

    public TooMuchQueryResultException(String message,String[][] array) {
        super(message + arrayRead(array));
    }
}
