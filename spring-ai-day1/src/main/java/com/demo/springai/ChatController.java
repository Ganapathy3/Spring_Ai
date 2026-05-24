package com.demo.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Day 1 - Spring AI ChatController
 *
 * explanation:
 *   - ChatClient is your TELEPHONE to the AI robot.
 *   - You pick it up (.prompt), say something (.user),
 *     call the robot (.call), and listen (.content).
 *   - That's all Spring AI is!
 *
 * We expose 3 endpoints to show different features:
 *   1. GET  /ask          - Simple question → answer
 *   2. GET  /ask/java     - Question with a Java-expert system prompt
 *   3. POST /ask/chat     - Send question in request body (more RESTful)
 */
@RestController
@RequestMapping("/ask")
public class ChatController {

    private final ChatClient chatClient;

    // Spring AI auto-creates ChatClient.Builder for us — we just inject it
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // -------------------------------------------------------
    // Endpoint 1: Simple Q&A
    // Try: GET http://localhost:8080/ask?q=What is Spring Boot?
    // -------------------------------------------------------
    @GetMapping
    public String ask(@RequestParam(defaultValue = "Tell me a fun Java fact") String q) {

        return chatClient
                .prompt()
                .user(q)        // your question goes here
                .call()
                .content();     // returns the AI's reply as a String
    }

    // -------------------------------------------------------
    // Endpoint 2: Q&A with a System Prompt
    //
    // A "system prompt" is like giving the AI a JOB DESCRIPTION
    // before it answers. Here we tell it: "You are a Java expert,
    // always answer with code examples."
    //
    // Try: GET http://localhost:8080/ask/java?q=How do I sort a list?
    // -------------------------------------------------------
    @GetMapping("/java")
    public String askJavaExpert(@RequestParam(defaultValue = "What is a record in Java 21?") String q) {

        String systemPrompt = """
                You are a senior Java developer with 10 years of experience.
                Always answer with:
                1. A simple explanation (2-3 sentences)
                2. A short Java code example
                Keep it concise and beginner-friendly.
                """;

        return chatClient
                .prompt()
                .system(systemPrompt)   // give the AI a persona/instructions
                .user(q)
                .call()
                .content();
    }

    // -------------------------------------------------------
    // Endpoint 3: POST with request body (real-world style)
    //
    // Try:
    //   POST http://localhost:8080/ask/chat
    //   Body: { "question": "Explain virtual threads in Java 21" }
    // -------------------------------------------------------
    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String answer = chatClient
                .prompt()
                .user(request.question())
                .call()
                .content();

        return new ChatResponse(request.question(), answer);
    }

    // -------------------------------------------------------
    // Simple request/response records (Java 17+ feature!)
    // Records are like DTOs but with zero boilerplate.
    // -------------------------------------------------------
    record ChatRequest(String question) {}

    record ChatResponse(String question, String answer) {}
}
