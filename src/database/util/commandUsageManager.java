package database.util;

import java.io.*;
import java.util.*;
import database.*;
import database.Console;

public class commandUsageManager {

	private final static String filename = "data/syntax.txt";

	public static String getUsage(Command c) {

		if (c == null) return null;
		try {
			Scanner s = new Scanner(new File(filename));
			while (s.hasNext()) {
				String usageBuffer = checkForMatch(s, c);
				if (usageBuffer != null) {
					s.close();
					return usageBuffer;
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			Console.say(Color.red + "Error " + e.getMessage());
		}
		return null;
	}

	private static String checkForMatch(Scanner s, Command c) {
		String command = c.getName();
		if (s.next().equals(command)) {
			if (s.hasNextLine()) return s.nextLine();
		}
		return null;
	}

}
