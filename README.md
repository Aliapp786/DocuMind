# Spring AI Demo

A minimal **Spring Boot 3 + Spring AI** project wired to **Anthropic Claude** via a REST API.

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
| Anthropic API Key | [console.anthropic.com](https://console.anthropic.com) |

## Quick Start

### 1 — Set your API key

**Option A — environment variable (recommended)**
```bash
# Windows PowerShell
$env:ANTHROPIC_API_KEY = "sk-ant-..."

# Linux / macOS
export ANTHROPIC_API_KEY="sk-ant-..."
```

**Option B — edit `application.properties` directly**
```properties
spring.ai.anthropic.api-key=sk-ant-...
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
curl "http://localhost:8080/api/chat/simple?message=Hello+Claude"
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
| `spring.ai.anthropic.chat.options.model` | `claude-3-5-sonnet-20241022` | Claude model |
| `spring.ai.anthropic.chat.options.max-tokens` | `1024` | Max response tokens |
| `spring.ai.anthropic.chat.options.temperature` | `0.7` | Creativity (0–1) |

## Extending the Project

- **RAG / Document Q&A** — add `spring-ai-pgvector-store` or `spring-ai-chroma-store` and use `VectorStore`
- **Function Calling** — annotate a bean with `@Bean` returning a `FunctionCallback`
- **Other AI providers** — swap the starter (`spring-ai-openai-spring-boot-starter`, `spring-ai-vertex-ai-gemini-spring-boot-starter`, etc.)
