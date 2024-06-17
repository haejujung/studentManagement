package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;

public class Login {

	private static final Logger LOGGER = Logger.getLogger(Login.class.getName());

	public static void main(String[] args) {

		try {
			Connection conn = DBConnection.getconnection();

			Scanner scanner = new Scanner(System.in);
			System.out.println("name 을 입력 하세요 : ");
			String name = scanner.nextLine();

			System.out.println("password 를 입력하세요 : ");
			String pwd = scanner.nextLine();

			if (authenticateUser(conn, name, pwd)) {
				System.out.println(" 로그인 되었습니다. ");

			} else {
				System.out.println(" 로그인 실패");

			}

		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "MySQL 연결 오류");
			e.printStackTrace();
		}

	}

	private static boolean authenticateUser(Connection conn, String username, String password) {
		String query = " select * from users where username =  ?  and password =  ?  ";
		boolean result = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			result = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
