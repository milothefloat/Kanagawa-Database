public class Color {

	public static Color Black = new Color("\033[0m", "black");
	public static Color Red = new Color("\033[31m", "red");
	public static Color Green = new Color("\033[32m", "green");
	public static Color Yellow = new Color("\033[33m", "yellow");
	public static Color Blue = new Color("\033[34m", "blue");
	public static Color Magenta = new Color("\033[35m", "magenta");
	public static Color Cyan = new Color("\033[36m", "cyan");
	public static Color White = new Color("\033[37m", "white");

	public static Color[] Colors = new Color[8];

	static {
		Colors[0] = Black;
		Colors[1] = Red;
		Colors[2] = Green;
		Colors[3] = Yellow;
		Colors[4] = Blue;
		Colors[5] = Magenta;
		Colors[6] = Cyan;
		Colors[7] = White;
	}

	private String color = null;
	private String name = null;

	public Color(String color, String name) {
		this.name = name;
		this.color = color;
	}

	public String getName() {

		return name;
	}

	public String getColor() {
		if (color != null) return color;

		return null;
	}

	@Override
	public String toString() {
		if (color != null) return color;

		return null;
	}

}
