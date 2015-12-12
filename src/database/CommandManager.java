package database;

import java.util.Scanner;

public class CommandManager
{
	/**Returns false if input can't be handled*/
	public static boolean run(String input)
	{
		for (Commands c : Commands.values())
		{
			if (input.toLowerCase().startsWith(c.getName()))
			{
				String command = c.getName();
				Scanner argsS = null;
				String[] args = new String[256];

				if (input.length() > c.getName().length())
				{
					argsS = new Scanner(input.substring((command + " ").length()));
					for (int i = 0; i < args.length; i++)
					{
						if (argsS.hasNext()) args[i] = argsS.next();
						else break;
					}
				}
				return c.run(args);
			}
		}

		Console.say("I don't know what " + input + " means.");
		return false;
	}
}