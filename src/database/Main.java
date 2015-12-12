package database;

public class Main {

	public static boolean running = true;

	public Main() {
		Database sys_in = new Database(System.in);
		Console.println(Color.green + "Kanagawa-Console started!");
		while (running) {
			Console.print(">");
			String input = sys_in.nextLine();
			CommandManager.run(input);
		}
		Console.say("Kanagawa-Database session has ended!");
		sys_in.close();
	}

	public static void main(String[] args) {
		Console.clear();
		if (args.length > 0 && args[0].toLowerCase().startsWith("-color")) {
			for (Color c : Color.values()) {
				if (args[1].toLowerCase().equals(c.getName())) {
					Console.textColor = c;
				}
			}
		}
		new Main();
	}

}
