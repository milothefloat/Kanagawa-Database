
public class CommandManager {

	public CommandManager(String input) {

		for (int i = 0; i < Command.commands.size(); i++) {

			if (input.toLowerCase().startsWith(Command.commands.get(i).getName())) {

				Command.commands.get(i).run();

			}

		}

	}

}
