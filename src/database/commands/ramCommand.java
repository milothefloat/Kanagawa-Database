package database.commands;

import java.lang.management.*;

import database.*;
import database.util.*;

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
		
		long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		memorySize = memorySize / (1000 * 1000 * 1000);
		Console.say("You have about " + memorySize + "GB of RAM");
	}

}