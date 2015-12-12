package database.util;

import database.Console;

public class CommandManager
{
	/** Returns false if input can't be handled */
	public static boolean run(String input)
	{
		for (Commands c : Commands.values())
		{
			if (input.toLowerCase().startsWith(c.getName()))
			{
				String[] args;
				if (input.length() > c.getName().length())
					args = input.substring((c.getName() + " ").length()).split(" ");
				else args = null;
				return c.run(args);
			}
		}
		Console.say("I don't know what " + input + " means.");
		return false;
	}

}