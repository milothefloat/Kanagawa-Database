package database.util;

import database.*;
import database.commands.*;

public enum Commands
{

	ping(new PingCommand("ping")),
	td(new tdCommand("td")),
	ram(new ramCommand("ram")),
	// @formatter:off
	clear(new Command("clear"){public boolean run(){ Console.clear(); return true; }}),
	end(new Command("end"){public boolean run(){ Main.running = false;return true; }}),
	// @formatter:on
	create(new createCommand("create")),
	help(new helpCommand("help")),
	ftpc(new ftpcCommand("ftpc"));

	private final Command command;
	private final String name;

	Commands(Command command)
	{
		this.name = command.getName();
		this.command = command;
	}

	public String getName()
	{
		return name;
	}

	public Command getCommand()
	{
		return command;
	}

	public boolean run(String[] args)
	{
		if (args == null || args[0] == null) return command.run();
		else return command.run(args);
	}

}
