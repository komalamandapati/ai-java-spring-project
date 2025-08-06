package com.project.ai_java_spring_project.controller;

import com.project.ai_java_spring_project.vacation.Itinerary;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class VacationPlanController {

    private final ChatClient chatClient;

    public VacationPlanController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/unstructured")
    public String unstructuredResponse() {
        return chatClient.prompt()
                .user("I want to plan a trip to Hawaii.Give me list of things to do")
                .call().content();
    }

    /*If we need a structured output */
    /*When we send the prompt we are sending the expected response format,
    under the hood the spring takes care and ask the LLM for structured
    output, passing the JSON Schema which is defined as entity*/
    @GetMapping("/structured")
    public Itinerary structuredResponse() {
        return chatClient.prompt()
                .user("I want to plan a trip to Hawaii.Give me list of things to do")
                .call().entity(Itinerary.class);
    }
}
