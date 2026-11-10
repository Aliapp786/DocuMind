package com.example.springaidemo.service;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer wrapping Spring AI interactions.
 * Keeps business logic out of the controller.
 */
@Service
public class ChatService {

    private static final String DEFAULT_SYSTEM =
            "You are a helpful, concise, and friendly AI assistant.";

    private final AnthropicChatModel chatModel;

    public ChatService(AnthropicChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String userMessage) {
        return chat(DEFAULT_SYSTEM, userMessage);
    }

    public String chat(String systemPrompt, String userMessage) {
        var system = new SystemPromptTemplate(systemPrompt).createMessage();
        var user   = new UserMessage(userMessage);
        return chatModel.call(new Prompt(List.of(system, user)))
                .getResult()
                .getOutput()
                .getContent();
    }
}
