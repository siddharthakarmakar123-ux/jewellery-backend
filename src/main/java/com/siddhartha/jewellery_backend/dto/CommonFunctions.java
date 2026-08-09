package com.siddhartha.jewellery_backend.dto;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonFunctions {

	private static final Random random = new Random();

	public static String generateId() {

		long timestamp = System.currentTimeMillis();
		int randomNumber = random.nextInt(1000);
		return String.format("%013d%03d", timestamp, randomNumber);
	}
	
	public static String generateCustomerNumber() {

        int number = ThreadLocalRandom.current().nextInt(0, 100000);
        return String.format("C%05d", number);
    }

	public static String generateEmployeeNumber() {
		int number = ThreadLocalRandom.current().nextInt(0, 100000);
        return String.format("E%05d", number);
	}
}
