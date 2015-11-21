import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class Main {

	// Stopping the application, if false the application loop will break
	public boolean running = true;

	public Main() {
		// Application start point
		Scanner s = new Scanner(System.in);
		Database.say(Color.Green + "Kanagawa-Database started!");

		while (running) {
			System.out.print("Kanagawa-Database>"); // Adds a custom name to the session.
			String input = s.nextLine();

			// if the user entered the command "end" the application loop will break
			if (input.toLowerCase().startsWith("end")) {
				running = false;
			}

			// if the user enters the command "create" the application will create a file with the parameters provided
			else if (input.toLowerCase().startsWith("create")) {
				createFile(input);
			}

			// if the user enters the command "help" they will get a list of all the current commands
			else if (input.toLowerCase().startsWith("help")) {
				System.out.println("-------------------------");
				System.out.println("      end to exit database");
				System.out.println("      create to create a file");
				System.out.println("      hello for a response");
				System.out.println("      td for current directory");
				System.out.println("      ftpc SERVER USERNAME PASSWORD");
				System.out.println("(WIP) ram gets installed ram");
				System.out.println("-------------------------");

				// Scanner n = new Scanner(input.substring("help ".length(), input.length()));

			}

			else if (input.toLowerCase().startsWith("ftpc")) {
				ftpc(input);
			}

			else if (input.toLowerCase().startsWith("ping")) {
				ping(input);
			}

			else if (input.toLowerCase().startsWith("hello")) {
				System.out.println("Hello.");

			}

			// if the user enters the command "td" they will be told the current directory
			else if (input.toLowerCase().startsWith("td")) {
				td();
			}

			else if (input.toLowerCase().startsWith("ram")) {

				ram();

			}

		}

		// Application exit point
		Database.say("Kanagawa-Database session has ended!");
		s.close();

	}

	private void ram() {
		long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		memorySize = memorySize / (1024 * 1024 * 1024);
		Database.say("You have about " + memorySize + "GB of RAM");
	}

	private void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				Database.say("Server: " + aReply);
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
				if (n.hasNext()) parm4 = n.next();

				if (parm1.length() > 0 && parm2.length() > 0 && parm3.length() > 0) {
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
							Database.say("Operation failed. Server reply code: " + replyCode);
							n.close();
							return;
						}
						boolean success = ftpClient.login(user, pass);
						showServerReply(ftpClient);
						if (!success) {
							Database.say("Could not login to the server");
							n.close();
							return;
						} else {
							Database.say("Logged in to server!");
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
			Database.say("See help for use of command");

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
					final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
					urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
					final long startTime = System.currentTimeMillis();
					urlConn.connect();
					final long endTime = System.currentTimeMillis();
					if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						// Database.say("Time (ms) : " + (endTime - startTime));
						Database.say(Color.Green + "Success!");
						Database.say("Pinged to " + parm1);
						Database.say("Response time was : " + (endTime - startTime) + "ms");
					}
				} catch (IOException e) {

					Database.say(Color.Red + "Error you entered : " + parm1);
					Database.say("Command usage : ping https://example.com");

				}
			}
			n.close();
		} else
			Database.say("Pong.");
	}

	/**
	 * Tells current directory
	 */
	private void td() {
		Database.say(System.getProperty("user.dir"));
	}

	/**
	 * Used by the create file command
	 */
	private void createFile(String input) {
		String file_name = "file1";
		String file_input = "";

		//Scanner n = new Scanner(input.substring("create ".length()));
		
		Database db_in = new Database("create", input);
		
		while (db_in.hasParam()) {
			file_name = db_in.nextParam();

			try {
				String param3 = db_in.nextParam();
				if (param3.toLowerCase().startsWith("\"")) {
					file_input = param3.substring(1);
					while (db_in.hasParam()) {
						file_input += " " + db_in.nextParam();
					}
					if (file_input.endsWith("\"")) {
						file_input = file_input.substring(0, file_input.length() - 1);
					} else {
						file_input = "Hello!  I was created with the name " + file_name;
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
			Database.say("Created file with name : " + file_name + (file_input.length() > 0 ? (" , with input : " + file_input) : ""));
		} catch (Exception e) {
			Database.say(Color.Red + "Error " + file_input + " " + file_name );
		}
		db_in.close();
	}

	public static void main(String[] args) {

		new Main();

	}

}
