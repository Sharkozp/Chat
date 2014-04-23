package com.example.dikij_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot {
	private String name = "Bender";
	private List<String> phrases = new ArrayList<String>();

	public Robot() {
		phrases.add("Hello!!!");
		phrases.add("How are you?");
		phrases.add("Nice to meet you!");
		phrases.add("Good weather today!");
		phrases.add("Kill all humans...");
	}

	public String getName() {
		return name;
	}

	/*
	 * Get random phrase
	 */
	public String getPhrase() {
		Random generator = new Random();
		int index = generator.nextInt(phrases.size());
		return phrases.get(index);
	}

}
