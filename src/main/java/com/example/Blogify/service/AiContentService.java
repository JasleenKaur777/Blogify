package com.example.Blogify.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiContentService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public AiContentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String generateBlog(String title, String tone) {
        String prompt = buildPrompt(title, tone);

        Map<String, Object> message = Map.of(
            "contents", List.of(
                Map.of(
                    "role", "user",
                    "parts", List.of(Map.of("text", prompt))
                )
            )
        );

        try {
            Map<String, Object> response = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.0-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                    .bodyValue(message)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                List<Map<String, String>> parts = (List<Map<String, String>>) content.get("parts");
                return parts.get(0).get("text");
            } else {
                return "No blog generated.";
            }

        } catch (Exception e) {
            return "Error while generating blog: " + e.getMessage();
        }
    }

    private String buildPrompt(String title, String tone) {
        return "Write a detailed and engaging blog post on the topic: \"" + title + "\". "
             + "Use a " + tone.toLowerCase() + " tone. "
             + "Include headings, subheadings, formatting (like bold/italic), and structure it like a professional blog article.";
    }
}
