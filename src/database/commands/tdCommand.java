package database.commands;

import database.*;
import database.util.Command;

/**Short for tell directory*/
public class tdCommand extends Command
{

	public tdCommand(String name)
	{
		super(name);
	}
	
	@Override
	public boolean run() {
		Console.say(System.getProperty("user.dir"));
		return true;
	}

}
