package com.example.home_decoration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private String message;
    private boolean success;
    private T data;
    private String token;

    public void setResultSuccess(String msg) {
        this.message = msg;
        this.success = true;
        this.data = null;

    }

    public void setResultSuccess(String msg, T data) {
        this.message = msg;
        this.success = true;
        this.data = data;
    }

    public void setResultSuccess(String msg, T data, String token) {
        this.message = msg;
        this.success = true;
        this.data = data;
        this.token = token;
    }

    public void setResultFailed(String msg) {
        this.message = msg;
        this.success = false;
        this.data = null;
        this.token = null;
    }
}
