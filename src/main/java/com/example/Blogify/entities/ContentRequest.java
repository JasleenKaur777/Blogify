package com.example.Blogify.entities;

import java.util.List;

public class ContentRequest {
    private List<Content> contents;

    // Default constructor for Jackson
    public ContentRequest() {}

    public ContentRequest(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public static class Content {
        private String role;  // Required by Gemini API
        private List<Part> parts;

        // Default constructor for Jackson
        public Content() {}

        public Content(String role, List<Part> parts) {
            this.role = role;
            this.parts = parts;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    public static class Part {
        private String text;

        // Default constructor for Jackson
        public Part() {}

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    // This method is used to extract the title from the content
    public String getTitle() {
        if (contents != null && !contents.isEmpty()) {
            Content content = contents.get(0);
            if (content.getParts() != null && !content.getParts().isEmpty()) {
                return content.getParts().get(0).getText();
            }
        }
        return null;
    }
}
