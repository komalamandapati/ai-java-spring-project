package com.project.ai_java_spring_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ChatClient chatClient;

    public ArticleController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "topic", defaultValue = "JDK Virtual machine") String topic)
    {
        var systemInstructions = """
            Blog Post Generator Guidelines:
            1.Length & Purpose: Generate 500 word blog posts.
            2.Structure:
            -Introduction
            -Body
            -Conclusion
            3.Content Requirements:
            -Include real world examples
            -Incorporate revelant statistics
            -Explain benfits
            4.Tone & Style:
            -Write in an informative yet conversational way
            -breakup up text with headings and short paragraphs
            5.Response format: Deliver complete, ready to publish posts with a title.
            """;
        return chatClient.prompt().user( u-> {
            u.text("Write me a blog post about {topic}");
            u.param("topic", topic);
        }).system(systemInstructions).call().content();
    }
}
