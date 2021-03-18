package clientServer;

import java.sql.DriverManager;
import java.sql.Connection;

public class Koneksi {
	private static Connection koneksi;

	public static Connection getKoneksi() {
		if (koneksi == null) {
			try {
				String url = "jdbc:mysql://192.168.1.2/cs";
				String username = "root";
				String password = "root";

				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				koneksi = DriverManager.getConnection(url, username, password);
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return koneksi;
	}
}
