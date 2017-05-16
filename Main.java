import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class Main {

	public static int c_ = 0;

	public boolean running = true;

	static void exitAt(Object state) {
		switch(c_) {
			case 0:
				// Exit cleanly.
				running = false;
				break;
			case 1:
				// Exit cleanly, with alert.
				System.out.println("Exiting cleanly.");
				running = false;
				break;
			case 3:
				System.out.println("Something weird happened.");
				running = false;
				break;
			default:
				break;
		}
	}

	// Stopping the application, if false the application loop will break
	public boolean running = true;

	public Main() {
		// Application start point
		Scanner s = new Scanner(System.in);
		System.out.println("KConsole v0.01 by The Kanagawa Project.");

		while (running) {

			System.out.print("KConsole> ");
			String input = s.nextLine();

			// if the user entered the command "end" the application loop will
			// break
			if ( input.toLowerCase().startsWith("end") || input.toLowerCase().startsWith("exit") ) {
				running = false;
			}

			// if the user enters the command "hello" the application will
			// respond with the following statement
			else if (input.toLowerCase().startsWith("hello")) {
				hello(input);
			}


			else if (input.toLowerCase().startsWith("delete")) {
				// Delete files?
			}

			// if the user enters the command "create" the application will
			// create a file with the parameters provided
			else if (input.toLowerCase().startsWith("create")) {
				createFile(input);
			}

			// if the user enters the command "help" they will get a list of all
			// the current commands
			else if (input.toLowerCase().startsWith("help")) {
				System.out.println("----------------------------");
				System.out.println("  end to exit Console");
				System.out.println("  create to create a file");
				System.out.println("  hello for a response");
				System.out.println("  td for current directory");
				System.out.println("  runenv for executing environment variables");
				System.out.println("  clear to clear the screen.");
				System.out.println("  ftpc SERVER USERNAME PASSWORD");
				System.out.println("  cos  Current OS          ");
				System.out.println("----------------------------");

				// Scanner n = new Scanner(input.substring("help ".length(),
				// input.length()));

			}

			else if (input.toLowerCase().startsWith("ftpc")) {
				ftpc(input);
			}

			else if (input.toLowerCase().startsWith("ping")) {
				ping(input);
			}

			// if the user enters the command "td" they will be told the current
			// directory
			else if (input.toLowerCase().startsWith("td")) {
				td();
			}

			else if (input.toLowerCase().startsWith("clear")){
				int i = 0;
				for (i=0;i<32;i++)
				{
					System.out.println("\n");
				}
			}

			else if (input.toLowerCase().startsWith("cos")) {
				String currentOS = System.getProperty("os.name");

				System.out.println("Current OS: " + currentOS);
			}


		}

		// Application exit point
		Console.say("Console session has ended!");
		s.close();

	}

	private void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				Console.say("SERVER: " + aReply);
			}
		}
	}

	private void ftpc(String input) {
		if (!input.trim().equals("ftpc")) {
			String parm1, parm2, parm3 = null;
			String parm4 = "21";
			Scanner n = new Scanner(input.substring("ftpc ".length()));
			if (n.hasNext()) {
				parm1 = n.next();
				parm2 = n.next();
				parm3 = n.next();
				if (n.hasNext())
					parm4 = n.next();

				if (parm1.length() > 0 && parm2.length() > 0
						&& parm3.length() > 0) {
					parm4.trim();

					String server = parm1;
					int port = parm4.length() > 0 ? Integer.valueOf(parm4) : 21;
					String user = parm2;
					String pass = parm3;
					FTPClient ftpClient = new FTPClient();
					try {
						ftpClient.connect(server, port);
						showServerReply(ftpClient);
						int replyCode = ftpClient.getReplyCode();
						if (!FTPReply.isPositiveCompletion(replyCode)) {
							Console.say("Operation failed. Server reply code: "
									+ replyCode);
							n.close();
							return;
						}
						boolean success = ftpClient.login(user, pass);
						showServerReply(ftpClient);
						if (!success) {
							Console.say("Could not login to the server");
							n.close();
							return;
						} else {
							Console.say("LOGGED INTO SERVER");
							new FTP(ftpClient);
						}
					} catch (IOException ex) {
						System.out.println("Oops! Something wrong happened");
						ex.printStackTrace();
					}

				}
			}
			n.close();

		} else
			Console.say("See help for use of command");

	}

	private void ping(String input) {

		if (!input.trim().equals("ping")) {
			String parm1 = null;
			Scanner n = new Scanner(input.substring("ping ".length()));
			if (n.hasNext()) {
				parm1 = n.next();
			}

			if (parm1.length() > 0) {
				try {
					URL url = new URL(parm1);
					final HttpURLConnection urlConn = (HttpURLConnection) url
							.openConnection();
					urlConn.setConnectTimeout(1000 * 10); // mTimeout is in
															// seconds
					final long startTime = System.currentTimeMillis();
					urlConn.connect();
					final long endTime = System.currentTimeMillis();
					if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						// Console.say("Time (ms) : " + (endTime - startTime));
						Console.say("Ping to " + parm1 + " was success!");
						Console.say("Response time was : "
								+ (endTime - startTime) + "ms");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			n.close();
		} else
			Console.say("Pong.");
	}

	/**
	 * Tells current directory
	 */
	private void td() {
		Console.say(System.getProperty("user.dir"));
	}

	/**
	 * Used by the create file command
	 */

	private void createFile(String input) {
		String file_name = "file1";
		String file_input = "";

		Scanner n = new Scanner(input.substring("create ".length()));
		while (n.hasNext()) {
			file_name = n.next();

			try {
				String param3 = n.next();
				if (param3.toLowerCase().startsWith("\"")) {
					file_input = param3.substring(1);
					while (n.hasNext()) {
						file_input += " " + n.next();
					}
					if (file_input.endsWith("\"")) {
						file_input = file_input.substring(0,
								file_input.length() - 1);
					} else {
						file_input = "Hello!  I was created with the name "
								+ file_name;
					}
				}
			} catch (Exception e) {

			}

			break;
		}

		try {
			PrintWriter out = new PrintWriter(file_name, "UTF-8");
			out.println(file_input);
			out.close();
			Console.say("Created file with name : "
					+ file_name
					+ (file_input.length() > 0 ? (" , with input : " + file_input)
							: ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		n.close();
	}

	public void hello (String Message)
	{
		if (!Message.trim().equals("hello")) {
			Scanner n = new Scanner(Message.substring("hello ".length()));


			System.out.println(n);
		}
	}

	public static void main(String[] args) {

		new Main();

	}

}
