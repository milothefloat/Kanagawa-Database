package database;

public class Console
{

	public static Color textColor = Color.white;

	public static void say(Object message)
	{
		System.out.println(textColor + "  Database: " + message + textColor);
	}

	public static void print(Object message)
	{
		System.out.print(textColor.getColor() + message + textColor.getColor());
	}

	public static void println(Object message, Color endColor)
	{
		System.out.println(textColor.getColor() + message + textColor.getColor());
	}

	public static void println(Object message)
	{
		System.out.println(textColor.getColor() + message + textColor.getColor());
	}

	public static void clear()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}
