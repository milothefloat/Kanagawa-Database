import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;

public class FTP {
	public FTP(FTPClient ftpClient) {
		Scanner s = new Scanner(System.in);

		while (ftpClient.isConnected()) {
			System.out.print("FTP>");
			String input = s.nextLine();
			if (input.toLowerCase().startsWith("end")) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		Console.say("FTP session ended!");

		s.close();
	}
}
