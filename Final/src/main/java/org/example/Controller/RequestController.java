package org.example.Controller;

import org.example.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {

    @Autowired
    private RequestService requestService = new RequestService();
    @PostMapping("/api/summarize")
    public Object summarize(@RequestBody Map<String, String> text) {
        System.out.println(text);
        String url = text.get("url");
        System.out.println(url);
        return requestService.summarize(url);
    }

    @GetMapping("/history")
    public Object getHistory() {
        return requestService.getHistory();
    }
}
