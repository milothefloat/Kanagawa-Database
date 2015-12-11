package database.commands;
import java.util.List;

import database.Words;

public class Command
{
	private String command = null;
	public static List<Command> commands;

	public Command(String command)
	{
		this.command = command;
	}

	public String getValue()
	{
		return command;
	}

	public void run()
	{
		System.err.println("No run method for : " + command);
	}

	static
	{
		commands.add(new Command(Words.English.Create));
		commands.add(new Command(Words.English.End));
		commands.add(new Command(Words.English.Hello));
		commands.add(new Command(Words.English.Help));
		commands.add(new Command(Words.English.Ping));
		commands.add(new Command(Words.English.Ram));
	}

}
