package com.rapiddweller.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Answer {
    private String answer;
    private int type;
    private Date createdAt;
}
