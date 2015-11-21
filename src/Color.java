
public class Color {

	public static Color Black = new Color("\033[0m");
	public static Color Red = new Color("\033[31m");
	public static Color Green = new Color("\033[32m");
	public static Color Yellow = new Color("\033[33m");
	public static Color Blue = new Color("\033[34m");
	public static Color Magenta = new Color("\033[35m");
	public static Color Cyan = new Color("\033[36m");
	public static Color White = new Color("\033[37m");

	private String color = null;

	public Color(String color) {

		this.color = color;
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
