package database.commands;

import database.*;

public class ramCommand extends Command
{

	public ramCommand(String name)
	{
		super(name);
	}

	public boolean run()
	{
		ram();
		return true;
	}

	private void ram()
	{
		/*
		long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		memorySize = memorySize / (1000 * 1000 * 1000);
		Console.say("You have about " + memorySize + "GB of RAM");*/
		Console.say("This command is not working right now.");
	}

}