package com.rapiddweller.controllers;

import com.rapiddweller.models.Answer;
import com.rapiddweller.models.Question;
import com.rapiddweller.services.Wizardable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WizardController {

    @Autowired
    private Wizardable wizard;

    @CrossOrigin("*")
    @PostMapping("/ask")
    public ResponseEntity<Answer> ask(@RequestBody Question question) {
        Answer answer = wizard.answer(question);
        return ResponseEntity.ok(answer);
    }
}
