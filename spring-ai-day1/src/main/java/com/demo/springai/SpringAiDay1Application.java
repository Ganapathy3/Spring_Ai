package com.demo.springai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Day 1 - Spring AI Basics
 *
 * What this app does:
 *   - Exposes a simple REST endpoint: POST /ask
 *   - Takes a question from the user
 *   - Sends it to Groq AI (free LLM) via Spring AI
 *   - Returns the AI's answer as plain text
 *
 * Think of it as: your Java app can now "talk" to an AI brain!
 */
@SpringBootApplication
public class SpringAiDay1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDay1Application.class, args);
    }
}
