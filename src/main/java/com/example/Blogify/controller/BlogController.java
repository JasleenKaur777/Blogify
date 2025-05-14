package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.entities.ContentRequest;
import com.example.Blogify.service.AiContentService;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private AiContentService aiContentService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateBlog(@RequestParam String title,
                                               @RequestParam(defaultValue = "professional") String tone) {
        try {
            String blogContent = aiContentService.generateBlog(title, tone);
            return ResponseEntity.ok(blogContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to generate blog: " + e.getMessage());
        }
    }


}
