package database.commands;

import java.io.IOException;

import org.apache.commons.net.ftp.*;

import database.*;
import database.FTP;
import database.util.Command;

public class ftpcCommand extends Command
{

	public ftpcCommand(String name)
	{
		super(name);
	}

	public boolean run(String[] args)
	{
		Console.say("May not be working as expected");
		ftpc(args);
		return true;
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

	private boolean orEquals(Object o, Object... values)
	{
		for (Object i : values)
		{
			if (i == o) return true;
		}
		return false;
	}

	private void ftpc(String[] args)
	{
		String parm1 = args[0], parm2 = args[1], parm3 = args[2];
		String parm4 = "21";

		if (orEquals(null, parm1, parm2, parm3))
		{
			return;
		}
		parm4.trim();
		String server = parm1;
		int port = parm4 != null ? Integer.valueOf(parm4) : 21;
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
				return;
			}
			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			if (!success)
			{
				Console.say(Color.red + "Could not login to the server");
				return;
			}
			else
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
