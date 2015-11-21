import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;

public class FTP {
	public FTP (FTPClient ftpClient) {
		Scanner s = new Scanner(System.in);
		
		while (ftpClient.isConnected()) {
			System.out.print("DatabaseFTP>");
			String input = s.nextLine();
			if (input.toLowerCase().startsWith("end")) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		Database.say("FTP session ended!");
		
		s.close();
	}
}
