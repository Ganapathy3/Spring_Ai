# Day 1 — Spring AI Basics 🤖

**Part of my Java Trends 2026 series** — one hands-on topic per day.

## What is this?

A minimal Spring Boot app that talks to an AI (Groq's free LLM API) using **Spring AI 1.0**.

No paid API key needed. No complex setup. Just Java + Spring AI + a free Groq key.

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 21 | Language |
| Spring Boot | 3.4.1 | Framework |
| Spring AI | 1.0.0 | AI integration |
| Groq | free tier | LLM provider (llama-3.3-70b) |

## How it works

```
You → POST /ask → ChatController → Spring AI ChatClient → Groq (free AI) → Answer
```

Spring AI's `ChatClient` is a clean, fluent API to talk to any LLM.
Groq is OpenAI-API compatible, so the OpenAI starter works with Groq out of the box.

## Quick Start

### 1. Get a free Groq API key
Sign up at https://console.groq.com/keys — no credit card needed.

### 2. Set your API key as environment variable
```bash
# Mac/Linux
export GROQ_API_KEY=gsk_your_key_here

# Windows
set GROQ_API_KEY=gsk_your_key_here
```

### 3. Run the app
```bash
mvn spring-boot:run
```

## API Endpoints

### Simple Q&A
```bash
curl "http://localhost:8080/ask?q=What is Spring Boot?"
```

### Ask a Java expert
```bash
curl "http://localhost:8080/ask/java?q=How do I use records in Java 21?"
```

### POST with request body
```bash
curl -X POST http://localhost:8080/ask/chat \
  -H "Content-Type: application/json" \
  -d '{"question": "Explain virtual threads in Java 21"}'
```

## Key Concepts Learned

- **ChatClient** — Spring AI's fluent API to send prompts to any LLM
- **System prompt** — Instructions that give the AI a persona/role
- **User prompt** — The actual question
- **Groq** — Free, fast LLM cloud API (OpenAI-compatible)
- **Java Records** — Clean, zero-boilerplate DTOs (Java 16+)

## Project Structure

```
src/main/java/com/demo/springai/
├── SpringAiDay1Application.java   # Entry point
└── ChatController.java            # REST endpoints + Spring AI logic

src/main/resources/
└── application.properties         # Config (model, API key reference)
```

---
> Part of my **Java Trends 2026** daily commit series.
