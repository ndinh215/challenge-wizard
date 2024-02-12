package com.rapiddweller.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    private String error;

    public ResponseError(String error) {
        this.error = error;
    }
}
