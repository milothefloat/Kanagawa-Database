import java.util.List;

public class Command {

	private String command = null;
	public static List<Command> commands;

	public Command(String command) {

		this.command = command;

	}
	
	public String getName () {
		
		return command;
		
	}

	public void run() {

	}

	private static class Create extends Command {

		public Create(String command) {
			super(command);
		}

		public void run() {
			
		}

	}

	static {

		commands.add(new Create(Words.English.Create));
		commands.add(new Command(Words.English.End));
		commands.add(new Command(Words.English.Hello));
		commands.add(new Command(Words.English.Help));
		commands.add(new Command(Words.English.Ping));
		commands.add(new Command(Words.English.Ram));

	}

}
