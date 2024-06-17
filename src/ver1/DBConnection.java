package ver1;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

	private static HikariDataSource dataSource;

	private static final String URL = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";

	static {

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(10);
		dataSource = new HikariDataSource(config);

	}

	public static Connection getconnection() throws SQLException {
		System.out.println("히카리시피를 사용한 데이타 소스 활용");
		return dataSource.getConnection();

	}

}