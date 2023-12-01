package com.example.test.dto;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {

    private T body;
    private String message;

    public ResponseDto() {
    }

    public ResponseDto(T body, String message) {
        this.body = body;
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
