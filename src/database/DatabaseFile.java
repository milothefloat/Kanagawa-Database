package database;

import java.io.PrintWriter;

public class DatabaseFile
{

	public DatabaseFile(String input)
	{
		String file_name = "file1";
		String file_input = "";
		Database db_in = new Database(Words.English.Create, input);
		while (db_in.hasNext())
		{
			file_name = db_in.next();
			try
			{
				String param3 = db_in.next();
				if (param3.toLowerCase().startsWith("\""))
				{
					file_input = param3.substring(1);
					while (db_in.hasNext())
					{
						file_input += " " + db_in.next();
					}
					if (file_input.endsWith("\""))
					{
						file_input = file_input.substring(0, file_input.length() - 1);
					}
					else
					{
						file_input = "Hello!  I was created with the name " + file_name;
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			break;
		}

		try
		{
			PrintWriter out = new PrintWriter(file_name, "UTF-8");
			out.println(file_input);
			out.close();
			Console.say(Color.green + "Created file with name : " + file_name + (file_input.length() > 0 ? (" , with input : " + file_input) : ""));
		} catch (Exception e)
		{
			Console.say(Color.red + "Error " + file_input + " " + file_name);
		}
		db_in.close();
	}

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
			else return;
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

}
