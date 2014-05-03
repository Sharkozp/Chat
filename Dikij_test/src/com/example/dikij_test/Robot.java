package com.example.dikij_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot {
    private static final String NAME = "Bender";
    private List<String> phrases = new ArrayList<String>();
    private Random generator = new Random();

    public Robot() {
        phrases.add("Hello!!!");
        phrases.add("How are you?");
        phrases.add("Nice to meet you!");
        phrases.add("Good weather today!");
        phrases.add("Kill all humans...");
    }

    public String getName() {
        return NAME;
    }

    /*
     * Get random phrase
     */
    public String getPhrase() {
        return phrases.get(generator.nextInt(phrases.size()));
    }

}
