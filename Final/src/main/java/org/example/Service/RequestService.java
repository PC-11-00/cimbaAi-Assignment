package org.example.Service;

import org.example.ScalaLibrary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.concurrent.CompletionStage;
@Service
public class RequestService {

    private ScalaLibrary scalaLibrary = new ScalaLibrary();
    public Object summarize(String text){
        System.out.println(text);
        try {
            Object res = this.scalaLibrary.summarize(text);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in summarizing text";
        }
    }

    public Object getHistory() {
        try {
            Object res = this.scalaLibrary.getHistory();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in fetching history";
        }
    }
}
