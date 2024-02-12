package com.rapiddweller.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_TRANSLATE = 1;
    public static final int TYPE_INTERPRETER = 2;

    private String question;
    private int type;
}
