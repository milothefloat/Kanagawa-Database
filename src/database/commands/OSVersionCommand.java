package database.commands;

import database.*;
import database.util.Command;


public class OSVersion extends Command
{
	public tdCommand(String name)
	{
		super(name);
	}
	
	@Override
	public boolean run(int verbosity) {
		if ( verbosity == 2 ) {
			Console.say(System.getProperty("os.version"));
			Console.say(System.getProperty("os.name"));
			Console.say(System.getProperty("os.arch"));
		} else if ( verbosity == 1 ) {
			Console.say(System.getProperty("os.version"));
			Console.say(System.getProperty("os.name"));
		} else {
			Console.say(System.getProperty("os.name"));
		}

		verbosity = 0;
		return true;
	}

}
