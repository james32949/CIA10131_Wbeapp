package memberJDBC;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class img {

	public static void main(String[] args) {
		String sqlImg = "UPDATE  member SET member_img=? WHERE member_id=?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String columns[] = { "member_img" };
			int employee_id = -1;
			try (PreparedStatement ps = connection.prepareStatement(sqlImg, columns);
					InputStream in = Files.newInputStream(Path.of("img/image.png"))) {;
				// 讀入圖檔後插入
				ps.setBinaryStream(1, in, in.available());
				ps.setInt(2, 1);
				
				System.out.println("上傳成功");
				
				int rowCount = ps.executeUpdate();
				/*
				 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
				 * resources小括號內，參看ResultSet說明
				 */
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					employee_id = rs.getInt(1);
					System.out.println(rowCount + " row inserted; employee ID: " + employee_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
