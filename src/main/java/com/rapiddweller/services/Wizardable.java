package com.rapiddweller.services;

import com.rapiddweller.models.Answer;
import com.rapiddweller.models.Question;

public interface Wizardable {
    Answer answer(Question question);
}
