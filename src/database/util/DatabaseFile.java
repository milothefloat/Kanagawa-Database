package database.util;

import java.io.PrintWriter;

import database.*;

public class DatabaseFile {
	/**First arg file_name;	Everything after the first arg inside of " " is put into the file*/
	public DatabaseFile(String[] args) {
		String file_buffer = null;
		for (int i = 1; i < args.length; i++) {
			file_buffer += args[i] + " ";
		}
		createFile(args[0], file_buffer);
	}

	public DatabaseFile(String file_name, String file_input) {
		createFile(file_name, file_input);
	}

	public void createFile(String file_name, String file_input) {
		try {
			PrintWriter out = new PrintWriter(file_name, "UTF-8");
			out.println(file_input);
			out.close();
			Console.say(Color.green + "Created file with name : " + file_name + (file_input != null ? (" , with input : " + file_input) : ""));
		} catch (Exception e) {
			e.printStackTrace();
			Console.say(Color.red + "Error " + file_input + " " + file_name);
		}
	}

}
