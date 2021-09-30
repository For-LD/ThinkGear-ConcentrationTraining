package thinkgeardemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBUtil {
	// 使用SQL Server数据库存储脑电波数据
	String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ThinkGearDemo";
	String userName = "sa";
	String userPwd = "MIMA*963";

	// 获取连接
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		return DriverManager.getConnection(dbURL, userName, userPwd);
	}

	// 关闭连接，释放资源
	public void close(PreparedStatement pstmt, Connection con) throws Exception {
		if (pstmt != null) {
			pstmt.close();
			if (con != null) {
				con.close();
			}
		}
	}
}