package database.util;

public enum ftpCommands
{

	end(Commands.end.get());

	private final Command command;
	private final String name;

	ftpCommands(Command command)
	{
		this.name = command.getName();
		this.command = command;
	}

	public String getName()
	{
		return name;
	}

	public boolean run(String[] args)
	{
		if (args[0] == null) return command.run();
		else return command.run(args);

	}

}
