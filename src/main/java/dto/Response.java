package dto;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int code;

    private String message;

    private T data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(int code) {
        this.code = code;
    }

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
