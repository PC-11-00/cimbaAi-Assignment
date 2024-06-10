package org.example.Controller;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RequestControllerTest {
    private RequestController requestController = new RequestController();
    @Test
    void summarize() {
        Map<String,String> text = new HashMap<String, String>();
        text.put("url",new String("https://www.google.com"));
        Object summary = requestController.summarize(text);
        System.out.println("Summary:" + summary);
    }

    @Test
    void getHistory() {
        Object res = requestController.getHistory();
        System.out.println("Responses \n");
        System.out.println(res);
    }
}