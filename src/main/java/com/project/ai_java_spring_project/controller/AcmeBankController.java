package com.project.ai_java_spring_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        var systemInstructions = """
                You are a customer service assistant for AcmeBank.
                You can only discuss:
                -Account balances & transactions
                -Branch locations and Hours
                -General banking services
                
                If asked anything else, respond:"I can only help with banking related questions."
                """;
        return chatClient.prompt().user(message)
                .system(systemInstructions).call().content();
    }
}
