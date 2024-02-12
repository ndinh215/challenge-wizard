package com.rapiddweller.services;

import com.azure.ai.openai.models.*;
import com.rapiddweller.configuration.OpenAIConfig;
import com.rapiddweller.models.Answer;
import com.rapiddweller.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChallengeWizard implements Wizardable {

    @Autowired
    OpenAIConfig openAIConfig;

    @Override
    public Answer answer(Question question) {
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage(question.getQuestion()));

        switch (question.getType()) {
            case Question.TYPE_TRANSLATE: {
                chatMessages.add(new ChatRequestUserMessage("Translate it to German"));
                break;
            }
            case Question.TYPE_INTERPRETER: {
                chatMessages.add(new ChatRequestUserMessage("{last variable name} = {result}"));
                break;
            }
            case Question.TYPE_DEFAULT: {
                chatMessages.add(new ChatRequestUserMessage("What is it?"));
                break;
            }
        }

        ChatCompletions chatCompletions = openAIConfig.getClient().getChatCompletions(openAIConfig.getModelName(),
                new ChatCompletionsOptions(chatMessages));

        String fittestMessage = chatCompletions.getChoices().get(0).getMessage().getContent();

        Answer answer = new Answer();
        answer.setAnswer(fittestMessage);
        answer.setType(question.getType());
        answer.setCreatedAt(new Date());

        return answer;
    }
}
