package com.heihei.daily.domains;

import lombok.Data;

@Data
public class MyResponseContent<T> {

    private boolean success;

    private T data;

    private String message;

    private int statusCode;

    public MyResponseContent(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public MyResponseContent(boolean success, T data, String message, int statusCode) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public MyResponseContent(boolean success, T data, HttpStatusCode httpStatusCode) {
        this.success = success;
        this.data = data;
        this.message = httpStatusCode.getMessage();
        this.statusCode = httpStatusCode.getStatusCode();
    }
}
