package database;

/** ASCII colors */
public enum Color
{

	// color, name, id;
	black("\033[0m", "black", 0),
	red("\033[31m", "red", 1),
	green("\033[32m", "green", 2),
	yellow("\033[33m", "yellow", 3),
	blue("\033[34m", "blue", 4),
	magenta("\033[35m", "magenta", 5),
	cyan("\033[36m", "cyan", 6),
	white("\033[37m", "white", 7);

	private final String color;
	private final String name;
	private final int id;

	Color(String color, String name, int id)
	{
		this.color = color;
		this.name = name;
		this.id = id;
	}

	/** Returns string value of color */
	public String getColor()
	{
		return color;
	}

	public String getName()
	{
		return name;
	}

	public int getID()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return color;
	}

}
