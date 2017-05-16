public class Console {

	public static void say(String message) {
		System.out.println("  Console: " + message);
	}

	public static void endProgram(String customMessage, boolean e)
	{
		if ( e == false )
		{
			super.say(customMessage);
		}
	}

}
