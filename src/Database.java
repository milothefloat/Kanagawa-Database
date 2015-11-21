import java.io.InputStream;
import java.util.Scanner;

public class Database {

	Scanner input = null;
	String command = null;

	public static void say(Object message) {
		System.out.println("  Database: " + message + Color.White);
	}

	public static void print(Object message) {
		System.out.print(message + Color.White.getColor());
	}

	public static void println(Object message, Color endColor) {

		System.out.println(message + endColor.getColor());

	}

	public static void println(Object message) {

		System.out.println(message + Color.White.getColor());

	}

	/**
	 * the command will be removed from input 
	 * */
	public Database(String command, String input) {
		command.trim();
		this.command = command;
		this.input = new Scanner(input.substring((command + " ").length()));

	}

	public Database(InputStream in) {
		input = new Scanner(in);
	}

	/**
	 * This string will be removed from the input
	 * */
	public void setCommand(String command) {
		this.command = command;
		Scanner a = new Scanner("");
	}

	public boolean hasNext() {
		if (input.hasNext()) return true;
		//You don't need an else statement here so i just put this comment.
		return false;
	}

	public boolean hasNextLine() {
		if (input.hasNextLine()) return true;
		//You don't need an else statement here so i just put this comment.
		return false;
	}

	public void setInput(String input) {

		this.input = new Scanner(input.substring((command + " ").length()));

	}

	public String next() {
		if (input != null) {
			if (input.hasNext()) return input.next();
		}
		return null;
	}

	public String nextLine() {
		if (input != null) {
			if (input.hasNext()) return input.nextLine();
		}
		return null;
	}

	public void close() {
		input.close();
	}

}
