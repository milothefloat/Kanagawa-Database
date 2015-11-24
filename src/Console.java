
public class Console {

	public static void say(Object message) {
		System.out.println("  Database: " + message + Color.Red);
	}

	public static void print(Object message) {
		System.out.print(message + Color.Red.getColor());
	}

	public static void println(Object message, Color endColor) {

		System.out.println(message + endColor.getColor());

	}

	public static void println(Object message) {

		System.out.println(message + Color.Red.getColor());
	}
	
}
