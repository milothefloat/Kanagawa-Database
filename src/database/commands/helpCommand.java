package database.commands;

import database.Command;

public class helpCommand extends Command
{

	public helpCommand(String name)
	{
		super(name);
	}
	
	public boolean run() {
		System.out.println("-------------------------");
		System.out.println("      end, exits Console");
		System.out.println("      create, creates a file");
		System.out.println("      hello for a response");
		System.out.println("      td for current directory");
		System.out.println("      ftpc SERVER USERNAME PASSWORD");
		System.out.println("(WIP) ram gets installed ram");
		System.out.println("      clear, clears the console");
		System.out.println("-------------------------");
		return true;
	}

}
