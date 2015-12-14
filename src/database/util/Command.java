package database.util;

public class Command {
	protected String name;
	protected String usage;

	public Command(String name) {
		this.name = name;
	}

	public boolean run() {
		return false;
	}

	public boolean run(String arg) {
		return false;
	}

	public String getUsage() {
		return commandUsageManager.getUsage(this);
	}

	public boolean run(String[] args) {
		int numOfArgs = 0;
		for (String i : args) {
			if (i != null) numOfArgs++;
		}
		if (!(numOfArgs > 1)) {
			for (String i : args) {
				if (i != null) return run(i);
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}
}
