package database;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class Main
{

	public boolean running = true;

	public Main()
	{

		Database sys_in = new Database(System.in);

		Console.println(Color.green + "Kanagawa-Console started!");

		while (running)
		{
			Console.print(">");
			String input = sys_in.nextLine();

			if (input.toLowerCase().startsWith(Words.English.End))
			{
				running = false;
			} else if (input.toLowerCase().startsWith(Words.English.Create))
			{
				new DatabaseFile(input);
			} else if (input.toLowerCase().startsWith(Words.English.Help))
			{
				System.out.println("-------------------------");
				System.out.println("      end, exits Console");
				System.out.println("      create, creates a file");
				System.out.println("      hello for a response");
				System.out.println("      td for current directory");
				System.out.println("      ftpc SERVER USERNAME PASSWORD");
				System.out.println("(WIP) ram gets installed ram");
				System.out.println("      clear, clears the console");
				System.out.println("-------------------------");
			} else if (input.toLowerCase().startsWith("ftpc"))
			{
				ftpc(input);
			} else if (input.toLowerCase().startsWith(Words.English.Ping))
			{
				ping(input);
			} else if (input.toLowerCase().startsWith(Words.English.Hello))
			{
				Console.say("Hello.");
			} else if (input.toLowerCase().startsWith("td"))
			{
				td();
			} else if (input.toLowerCase().startsWith(Words.English.Ram))
			{
				ram();
			} else if (input.toLowerCase().startsWith("clear"))
			{
				Console.clear();
			} else if (input.toLowerCase().startsWith("color"))
			{
				System.out.println(Console.textColor.getName());
			} else
			{
				Console.say("I don't know what " + input + " means.");
			}
		}
		Console.say("Kanagawa-Database session has ended!");
		sys_in.close();
	}

	private void ram()
	{
		long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		memorySize = memorySize / (1024 * 1024 * 1024);
		Console.say("You have about " + memorySize + "GB of RAM");
	}

	private void showServerReply(FTPClient ftpClient)
	{
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0)
		{
			for (String aReply : replies)
			{
				Console.say("Server: " + aReply);
			}
		}
	}

	private void ftpc(String input)
	{
		if (!input.trim().equals("ftpc"))
		{
			String parm1, parm2, parm3 = null;
			String parm4 = "21";
			Scanner n = new Scanner(input.substring("ftpc ".length()));
			if (n.hasNext())
			{
				parm1 = n.next();
				parm2 = n.next();
				parm3 = n.next();
				if (n.hasNext()) parm4 = n.next();

				if (parm1.length() > 0 && parm2.length() > 0 && parm3.length() > 0)
				{
					parm4.trim();
					String server = parm1;
					int port = parm4.length() > 0 ? Integer.valueOf(parm4) : 21;
					String user = parm2;
					String pass = parm3;
					FTPClient ftpClient = new FTPClient();
					try
					{
						ftpClient.connect(server, port);
						showServerReply(ftpClient);
						int replyCode = ftpClient.getReplyCode();
						if (!FTPReply.isPositiveCompletion(replyCode))
						{
							Console.say(Color.red + "Operation failed. Server reply code: " + replyCode);
							n.close();
							return;
						}
						boolean success = ftpClient.login(user, pass);
						showServerReply(ftpClient);
						if (!success)
						{
							Console.say(Color.red + "Could not login to the server");
							n.close();
							return;
						} else
						{
							Console.say(Color.green + "Logged in to server!");
							new FTP(ftpClient);
						}
					} catch (IOException ex)
					{
						Console.say(Color.red + "Oops! Something wrong happened");
						ex.printStackTrace();
					}
				}
			}
			n.close();

		} else Console.say("See help for use of command");

	}

	private static void ping(String input)
	{
		if (!input.trim().equals(Words.English.Ping))
		{
			String parm1 = null;
			Database n = new Database(Words.English.Ping, input);
			if (n.hasNext())
			{
				parm1 = n.next();
			}
			if (parm1.length() > 0)
			{
				try
				{
					URL url = new URL(parm1);
					final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
					urlConn.setConnectTimeout(1000 * 10); // mTimeout is in
															// seconds
					final long startTime = System.currentTimeMillis();
					urlConn.connect();
					final long endTime = System.currentTimeMillis();
					if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
					{
						Console.say(Color.green + "Success!");
						Console.say("Pinged to " + parm1);
						Console.say("Response time was : " + (endTime - startTime) + "ms");
					}
				} catch (IOException e)
				{
					Console.say(Color.red + "Error you entered : " + parm1);
					Console.say("Command usage : ping https://example.com");
				}
			}
			n.close();
		} else Console.say("Pong.");
	}

	/**
	 * Tells current directory
	 */
	private void td()
	{
		Console.say(System.getProperty("user.dir"));
	}

	public static void main(String[] args)
	{
		Console.clear();

		// This is for when you type java Main -color red
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].toLowerCase().trim().startsWith("-color"))
			{
				for (Color c : Color.values())
				{
					if (args[i + 1].trim().toLowerCase().equals(c.getName()))
					{
						Console.textColor = c;
					}
				}
			}
		}
		new Main();
	}

}
