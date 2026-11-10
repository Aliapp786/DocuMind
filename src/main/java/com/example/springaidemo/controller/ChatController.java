package com.example.springaidemo.controller;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * REST controller exposing Spring AI chat endpoints backed by Anthropic Claude.
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AnthropicChatModel chatModel;

    public ChatController(AnthropicChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // -------------------------------------------------------------------------
    // Simple blocking endpoint
    // GET /api/chat/simple?message=Hello
    // -------------------------------------------------------------------------
    @GetMapping("/simple")
    public Map<String, String> simple(@RequestParam String message) {
        String response = chatModel.call(message);
        return Map.of("response", response);
    }

    // -------------------------------------------------------------------------
    // Full prompt with a system role
    // POST /api/chat/prompt
    // Body: { "system": "You are a pirate.", "user": "What is the weather?" }
    // -------------------------------------------------------------------------
    @PostMapping("/prompt")
    public Map<String, String> prompt(@RequestBody PromptRequest request) {
        var systemMessage = new SystemPromptTemplate(request.system()).createMessage();
        var userMessage   = new UserMessage(request.user());

        ChatResponse chatResponse = chatModel.call(new Prompt(List.of(systemMessage, userMessage)));
        String content = chatResponse.getResult().getOutput().getContent();
        return Map.of("response", content);
    }

    // -------------------------------------------------------------------------
    // Streaming endpoint (Server-Sent Events)
    // GET /api/chat/stream?message=Tell+me+a+joke
    // -------------------------------------------------------------------------
    @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> stream(@RequestParam String message) {
        return chatModel.stream(message)
                .map(chunk -> chunk.getResult().getOutput().getContent());
    }

    // -------------------------------------------------------------------------
    // DTOs
    // -------------------------------------------------------------------------
    public record PromptRequest(String system, String user) {}
}
