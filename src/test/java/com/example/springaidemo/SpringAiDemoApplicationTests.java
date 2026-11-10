package com.example.springaidemo;

import com.example.springaidemo.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ai.openai.OpenAiChatModel;

@SpringBootTest
class SpringAiDemoApplicationTests {

    @MockBean
    OpenAiChatModel chatModel;

    @Autowired
    ChatService chatService;

    @Test
    void contextLoads() {
        // Verifies the application context starts correctly
    }
}
