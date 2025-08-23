package in.sp.main.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import in.sp.main.Entity.ChatRequest;
import in.sp.main.Entity.ChatResponse;

@Service
public class Ai {
	
	private Map<String, String> knowledgeBase;

    public Ai() {
        knowledgeBase = new HashMap<>();
        // Add some basic Q&A
        knowledgeBase.put("hello", "Hello! How can I help you?");
        knowledgeBase.put("hi", "Hi there! What can I do for you?");
        knowledgeBase.put("how are you", "I'm just a bot, but I'm doing great!");
        knowledgeBase.put("what is your name", "I'm your friendly AI assistant.");
        knowledgeBase.put("bye", "Goodbye! Have a nice day!");
    }

    public ChatResponse getAIResponse(ChatRequest request) {
        String userMessage = request.getMessage().toLowerCase();

        for (String key : knowledgeBase.keySet()) {
            if (userMessage.contains(key)) {
                return new ChatResponse(knowledgeBase.get(key));
            }
        }
        return new ChatResponse("Sorry, I don't know the answer to that.");
    }
}


