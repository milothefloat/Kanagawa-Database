import java.util.Scanner;

public class Database {

	Scanner input = null;
	String command = null;

	public static void say(Object message) {
		System.out.println("  Database: " + message + Color.White);
	}

	/**
	 * the command will be removed from input 
	 * */
	public Database(String command, String input) {
		command.trim();
		this.command = command;
		this.input = new Scanner(input.substring((command + " ").length()));

	}

	public void setCommand(String command) {

		this.command = command;

	}

	public boolean hasParam() {

		if (input.hasNext()) return true;

		//You don't need an else statement here so i just put this comment.
		return false;
	}

	public void setInput(String input) {

		this.input = new Scanner(input.substring((command + " ").length()));

	}

	public String nextParam() {
		if (input != null) {
			if (input.hasNext()) return input.next();
		}
		return null;
	}
	
	public void close () {
		
		input.close();
		
	}

}
