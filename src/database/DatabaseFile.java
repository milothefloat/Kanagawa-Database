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
					} else
					{
						file_input = "Hello!  I was created with the name " + file_name;
					}
				}
			} catch (Exception e)
			{

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

}
