package database.commands;

import database.*;
import database.util.*;

public class helpCommand extends Command {

	public helpCommand(String name) {
		super(name);
	}

	public boolean run() {
		for (Commands c : Commands.values()) {
			String usageBuffer = c.get().getUsage();
			if (usageBuffer != null)
			Console.say(c.getName()+ " : " + usageBuffer);
		}
		return true;
	}

}
