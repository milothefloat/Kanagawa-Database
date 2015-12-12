package database.commands;

import database.*;

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
