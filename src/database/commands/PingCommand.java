package database.commands;

import java.io.IOException;
import java.net.*;

import database.*;

public class PingCommand extends Command
{

	public PingCommand(String command)
	{
		super(command);
	}
	
	@Override
	public void run () {
		
	}
	
	@SuppressWarnings("unused")
	private static void pingAddress(String address)
	{
		if (address.length() > 0)
		{
			try
			{
				URL url = new URL(address);
				final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
				final long startTime = System.currentTimeMillis();
				urlConn.connect();
				final long endTime = System.currentTimeMillis();
				if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					Console.say(Color.green + "Success!");
					Console.say("Pinged to " + address);
					Console.say("Response time was : " + (endTime - startTime) + "ms");
				}
			} catch (IOException e)
			{

				Console.say(Color.red + "Error you entered : " + address);
				Console.say("Command usage : ping https://example.com");

			}
		} else Console.say("Pong.");
	}

}
