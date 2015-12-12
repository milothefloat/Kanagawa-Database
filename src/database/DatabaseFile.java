package database;

import java.io.PrintWriter;

public class DatabaseFile
{
	/**First arg file_name;	Everything after the first arg inside of " " is put into the file*/
	public DatabaseFile(String[] args)
	{
		String file_name = args[0];
		String file_input = null;

		if (args[1] != null && args[1].startsWith("\"") && args[1].endsWith("\""))
		{
			file_input = args[1].substring(1, (args[1].length() - 1));
		}
		try
		{
			PrintWriter out = new PrintWriter(file_name, "UTF-8");
			if (args[1].startsWith("\""))
			{
				out.print(args[1].substring(1));
				file_input = args[1].substring(1);
			}
			else
			{
				out.close();
				return;

			}
			for (int i = 2; i < args.length; i++)
			{
				if (args[i] != null)
				{
					if (args[i].endsWith("\""))
					{
						out.print(args[i].substring(0, (args[i].length() - 1)));
						file_input += args[i].substring(0, (args[i].length() - 1));
						break;
					}
					out.print(args[i] + " ");
					file_input += args[i].substring(0, (args[i].length())) + " ";
				}
			}
			out.close();
			Console.say(Color.green + "Created file with name : " + file_name + (file_input != null ? (" , with input : " + file_input) : ""));
		} catch (Exception e)
		{
			e.printStackTrace();
			Console.say(Color.red + "Error " + file_input + " " + file_name);
		}
	}

	public DatabaseFile(String file_name, String file_input)
	{
		try
		{
			PrintWriter out = new PrintWriter(file_name, "UTF-8");
			out.println(file_input);
			out.close();
			Console.say(Color.green + "Created file with name : " + file_name + (file_input != null ? (" , with input : " + file_input) : ""));
		} catch (Exception e)
		{
			e.printStackTrace();
			Console.say(Color.red + "Error " + file_input + " " + file_name);
		}
	}

}
