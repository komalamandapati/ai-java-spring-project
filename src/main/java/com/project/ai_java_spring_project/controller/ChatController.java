package com.project.ai_java_spring_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder){
        chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(){
        return chatClient.prompt().user("Tell me an interesting fact about Java")
                .call().content();
    }

    @GetMapping("/chat-stream")
    public Flux<String> chatStream(){
        return chatClient.prompt()
                .user("I'm visiting North Carolina soon, Can you please me 10 places I must visit?")
                .stream().content();
    }

    @GetMapping("/chat-joke")
    public ChatResponse chatJoke(){
        return chatClient.prompt().user("Tell me an interesting fact about Java")
                .call().chatResponse();
    }
}
