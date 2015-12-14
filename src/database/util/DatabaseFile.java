package database.util;

import java.io.PrintWriter;

import database.*;

/**
 * Used to create files.
 * 
 * @author Milo
 * @since 0
 * @version 1.0
 */
public class DatabaseFile
{
	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * @param args
	 *            args[0] is used for the file name, everything after args[0]
	 *            and inside of " " is put into the file
	 * 
	 * @since 1.0
	 */
	public DatabaseFile(String[] args)
	{
		// FileInputBuffer finBuffer
		String finBuffer = "";
		for (int i = 1; i < args.length; i++)
		{
			finBuffer += args[i].replace("\"", "") + " ";
		}
		createFile(args[0], finBuffer);
	}

	public DatabaseFile(String file_name, String file_input)
	{
		createFile(file_name, file_input);
	}

	public void createFile(String file_name, String file_input)
	{
		createFile(file_name, file_input, DEFAULT_CHARSET);
	}

	/**
	 * Creates a file.
	 * 
	 * @param file_name
	 *            The name of the file.
	 * @param file_input
	 *            The input for the file.
	 * @param charset
	 *            The charset for the file.
	 * 
	 */
	public void createFile(String file_name, String file_input, String charset)
	{
		try
		{
			PrintWriter out = new PrintWriter(file_name, charset);
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
