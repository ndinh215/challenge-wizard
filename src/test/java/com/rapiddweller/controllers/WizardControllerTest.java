package com.rapiddweller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapiddweller.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WizardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCorrectTranslatedResult() throws Exception {
        Question question = new Question();
        question.setQuestion("Good morning");
        question.setType(Question.TYPE_TRANSLATE);

        this.mockMvc.perform(post("/ask").content(new ObjectMapper().writeValueAsString(question))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guten Morgen")));
    }

    @Test
    void shouldReturnCorrectComputedResult() throws Exception {
        Question question = new Question();
        question.setQuestion("int increment(int number) { return number + 1; } int result = increment(5);");
        question.setType(Question.TYPE_INTERPRETER);

        this.mockMvc.perform(post("/ask").content(new ObjectMapper().writeValueAsString(question))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("result = 6")));
    }

    @Test
    void shouldReturnCorrectComplexComputedResult() throws Exception {
        Question question = new Question();
        question.setQuestion("List<Integer> list = Arrays.asList(1, 2, 3);int sum = list.stream().mapToInt(i -> i * 2).sum();");
        question.setType(Question.TYPE_INTERPRETER);

        this.mockMvc.perform(post("/ask").content(new ObjectMapper().writeValueAsString(question))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("sum = 12")));
    }
}
