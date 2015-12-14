package database.commands;

import database.*;
import database.util.*;

public class createCommand extends Command
{

	public createCommand(String name)
	{
		super(name);
	}
	
	public boolean run (String[] args) {
		new DatabaseFile(args);
		return true;
	}

}
