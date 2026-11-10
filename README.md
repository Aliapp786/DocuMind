# Spring AI Demo

A minimal **Spring Boot 3 + Spring AI** project wired to **OpenAI (ChatGPT)** via a REST API.

## Project Structure

```
spring-ai-demo/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/springaidemo/
    │   │   ├── SpringAiDemoApplication.java   ← Boot entry point
    │   │   ├── controller/ChatController.java  ← REST endpoints
    │   │   └── service/ChatService.java        ← AI service layer
    │   └── resources/
    │       └── application.properties          ← Config & API key
    └── test/
        └── java/com/example/springaidemo/
            └── SpringAiDemoApplicationTests.java
```

## Prerequisites

| Tool | Version |
|------|---------|
| Java | 17 +    |
| Maven | 3.9 +  |
| OpenAI API Key | [platform.openai.com](https://platform.openai.com/api-keys) |

## Quick Start

### 1 — Set your API key

**Option A — environment variable (recommended)**
```bash
# Windows PowerShell
$env:OPENAI_API_KEY = "sk-..."

# Linux / macOS
export OPENAI_API_KEY="sk-..."
```

**Option B — edit `application.properties` directly**
```properties
spring.ai.openai.api-key=sk-...
```

### 2 — Run the app

```bash
cd spring-ai-demo
mvn spring-boot:run
```

---

## REST Endpoints

### Simple chat (GET)
```bash
curl "http://localhost:8080/api/chat/simple?message=Hello+ChatGPT"
```

### Custom system prompt (POST)
```bash
curl -X POST http://localhost:8080/api/chat/prompt \
  -H "Content-Type: application/json" \
  -d '{"system":"You are a Shakespearean poet.","user":"Tell me about Java."}'
```

### Streaming (SSE)
```bash
curl "http://localhost:8080/api/chat/stream?message=Tell+me+a+joke"
```

---

## Configuration Options

| Property | Default | Description |
|----------|---------|-------------|
| `spring.ai.openai.chat.options.model` | `gpt-4o` | OpenAI model |
| `spring.ai.openai.chat.options.max-tokens` | `1024` | Max response tokens |
| `spring.ai.openai.chat.options.temperature` | `0.7` | Creativity (0–1) |

## Extending the Project

- **RAG / Document Q&A** — add `spring-ai-pgvector-store` or `spring-ai-chroma-store` and use `VectorStore`
- **Function Calling** — annotate a bean with `@Bean` returning a `FunctionCallback`
- **Other AI providers** — swap the starter (`spring-ai-anthropic-spring-boot-starter`, `spring-ai-vertex-ai-gemini-spring-boot-starter`, etc.)
