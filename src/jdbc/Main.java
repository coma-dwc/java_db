package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) throws Exception {
		//1.JDBCドライバのロード
		Class.forName("org.sqlite.JDBC");
		
		//2.接続するDBの指定
		String url = "jdbc:sqlite:sample_dbs/users.db";
		
		//3.DBとの接続
		Connection conn = DriverManager.getConnection(url);
		try {
			System.out.println("こんにちは sqlite!");
	
			//4.SQLを実行し値を受け取る
			Statement stmt = conn.createStatement();
			try {
				ResultSet rs = stmt.executeQuery("SELECT id, name FROM users");
				try {
					while (rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2));
					}
				} finally {
					rs.close();
				}
			} finally {
				stmt.close();
			}
		} finally {
			//5.DBとの接続を閉じる
			conn.close();
		}
	}
}