package application.healthSoftware;

import java.util.concurrent.ThreadLocalRandom;

public class Util {
	public static String generateID() {
		int newID = ThreadLocalRandom.current().nextInt(100000, 1000000);
		return String.valueOf(newID);
	}
}
