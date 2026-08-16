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

	public static String generateUniqueNumber(String idType) {

		int number = ThreadLocalRandom.current().nextInt(0, 100000);
		if ("C".equals(idType))
			return String.format("C%05d", number);
		else if ("E".equals(idType))
			return String.format("E%05d", number);
		else if ("O".equals(idType))
			return String.format("O%05d", number);
		else
			return String.format("M%05d", number);
	}
}
