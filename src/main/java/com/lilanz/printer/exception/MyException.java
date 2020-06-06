package com.lilanz.printer.exception;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public MyException(String message) {
        this.code = "500";
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

