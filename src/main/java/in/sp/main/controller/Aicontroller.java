package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.Entity.ChatRequest;
import in.sp.main.Entity.ChatResponse;
import in.sp.main.services.Ai;

@RestController
@RequestMapping("/api/ai")

public class Aicontroller {

	@Autowired
    private Ai ai;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return ai.getAIResponse(request);
    }
}
