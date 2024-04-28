package common;

public class Common {
	// MySQL 8之後連線URL需加上SSL與時區設定
	public final static String URL = "jdbc:mysql://localhost:3306/joyfulresort?serverTimezone=Asia/Taipei";
	// MySQL 8之前
	// public final static String URL = "jdbc:mysql://localhost:3306/bookshop_jdbc";
	
	public final static String USER = "root";
	public final static String PASSWORD = "0000";
	public final static String DRIVER = "com.mysql.cj.jdbc.Driver";
}
