package database.commands;

import database.util.*;

public class createCommand extends Command
{

	public createCommand(String name)
	{
		super(name);
	}
	
	@Override
	public boolean run (String[] args) {
		new DatabaseFile(args);
		return true;
	}

}
