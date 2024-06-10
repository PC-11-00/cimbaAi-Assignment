package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.sql.DriverManager.println;
import static org.junit.jupiter.api.Assertions.*;

class ScalaLibraryTest {
    private ScalaLibrary scalaLibrary;
    @org.junit.jupiter.api.Test
    @BeforeEach
    void set() {
        scalaLibrary = new ScalaLibrary();
    }

    @org.junit.jupiter.api.Test
    void objectMapper() {
    }

    @org.junit.jupiter.api.Test
    void getHistory() {
        scalaLibrary.getHistory();
    }

    @org.junit.jupiter.api.Test
    void saveSummary() {
        String url = "http://example.com";
        String summary = "This is a summary.";
        assertDoesNotThrow(() -> scalaLibrary.saveSummary(url, summary));
    }

    @org.junit.jupiter.api.Test
    void summarize() {
        String text = "https://www.codechef.com";
        String summary = scalaLibrary.summarize(text);
        assertNotNull(summary);
        assertTrue(summary.length() > 0);
        println(summary);
    }
}