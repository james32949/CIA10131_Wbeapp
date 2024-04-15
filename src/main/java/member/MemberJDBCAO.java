package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test_member?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
	String user = "root";
	String password = "0000";

	private static final String INSER_STMT = "INSERT INTO member (member_name, member_account, member_password, member_email, member_phone, member_address, member_state, member_gender, member_birthday ,member_img)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_id,member_name,member_account,member_password,member_email,member_phone,member_address,member_state,member_gender,member_birthday,member_img FROM member";
	private static final String GET_ONE_STMT = "SELECT member_id,member_name,member_account,member_password,member_email,member_phone,member_address,member_state,member_gender,member_birthday,member_img FROM member WHERE member_id =?";
	private static final String DELETE = "DELETE FROM member WHERE member_id =?";
	private static final String UPDATE = "UPDATE member set member_name=?,member_password=?,member_email=?,member_phone=?,member_address=?,member_gender=?,member_birthday=? WHERE member_id=?";

	@Override
	public void insert(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSER_STMT);
			
			pstmt.setString(1, memberVO.getMember_name());
			pstmt.setString(2, memberVO.getMember_account());
			pstmt.setString(3, memberVO.getMember_password());
			pstmt.setString(4, memberVO.getMember_email());
			pstmt.setString(5, memberVO.getMember_phone());
			pstmt.setString(6, memberVO.getMember_address());
			pstmt.setInt(7, memberVO.getMember_state());
			pstmt.setInt(8, memberVO.getMember_gender());
			pstmt.setDate(9, memberVO.getMember_birthday());
			pstmt.setBytes(10, memberVO.getMember_img());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);
						
			pstmt.setString(1,memberVO.getMember_name());			
			pstmt.setString(2,memberVO.getMember_password());
			pstmt.setString(3,memberVO.getMember_email());
			pstmt.setString(4,memberVO.getMember_phone());
			pstmt.setString(5,memberVO.getMember_address());
			pstmt.setInt(6,memberVO.getMember_gender());
			pstmt.setDate(7,memberVO.getMember_birthday());
			pstmt.setInt(8,memberVO.getMember_id());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, member_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MemberVO findByPrimaryKey(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("member_id"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_account(rs.getString("member_account"));
				memberVO.setMember_password(rs.getString("member_password"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_phone(rs.getString("member_phone"));
				memberVO.setMember_address(rs.getString("member_address"));
				memberVO.setMember_state(rs.getInt("member_state"));
				memberVO.setMember_gender(rs.getInt("member_gender"));
				memberVO.setMember_birthday(rs.getDate("member_birthday"));
				memberVO.setMember_img(rs.getBytes("member_img"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("資料庫註冊失敗" + e.getMessage()); // e.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("資料庫錯誤" + se.getMessage()); //
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.cancel();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("member_id"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_account(rs.getString("member_account"));
				memberVO.setMember_password(rs.getString("member_password"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_phone(rs.getString("member_phone"));
				memberVO.setMember_address(rs.getString("member_address"));
				memberVO.setMember_state(rs.getInt("member_state"));
				memberVO.setMember_gender(rs.getInt("member_gender"));
				memberVO.setMember_birthday(rs.getDate("member_birthday"));
				memberVO.setMember_img(rs.getBytes("member_img"));
				list.add(memberVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("資料庫註冊失敗" + e.getMessage()); // e.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("資料庫錯誤" + se.getMessage()); //
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.cancel();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String args[]) {
		MemberJDBCAO dao = new MemberJDBCAO();
		//增新
//		MemberVO mem1 = new MemberVO();
//		
//		mem1.setMember_name("貪睡鼠鼠2");
//		mem1.setMember_account("sleepmoue2");
//		mem1.setMember_password("sleepmoues");
//		mem1.setMember_email("sleepmouse2@gmail.com");
//		mem1.setMember_phone("0966784321");
//		mem1.setMember_address("桃園市中壢區");
//		mem1.setMember_state(Integer.valueOf(1));
//		mem1.setMember_gender(Integer.valueOf(1));
//		
//		mem1.setMember_birthday(java.sql.Date.valueOf("1996-06-12"));
//		
//		mem1.setMember_img(null);
//		
//		dao.insert(mem1);
		
		//修改
		
//		MemberVO mem2 = new MemberVO();
//		mem2.setMember_id(5);
//		mem2.setMember_name("衝動鳩鳩");			
//		mem2.setMember_password("0958835976");
//		mem2.setMember_email("dh91009@gmail");
//		mem2.setMember_phone("0958835976");
//		mem2.setMember_address("新竹市香山區");
//		mem2.setMember_gender(0);
//		
//		java.sql.Date sqlDate1 = java.sql.Date.valueOf("1996-06-12");
//		mem2.setMember_birthday(sqlDate1);
//		
//		dao.update(mem2);
		
		// 刪除
		
//		dao.delete(1);
		
		// 查詢ONE
		
//		MemberVO mem3 = dao.findByPrimaryKey(2);
//		System.out.print(mem3.getMember_id() + ",");
//		System.out.print(mem3.getMember_name() + ",");
//		System.out.print(mem3.getMember_account() + ",");
//		System.out.print(mem3.getMember_password() + ",");
//		System.out.print(mem3.getMember_email() + ",");
//		System.out.print(mem3.getMember_phone() + ",");
//		System.out.print(mem3.getMember_address() + ",");
//		System.out.print(mem3.getMember_state() + ",");
//		System.out.print(mem3.getMember_gender() + ",");
//		System.out.print(mem3.getMember_birthday() + ",");
//		System.out.print(mem3.getMember_img() + ",");
//		System.out.println("\n=================================");

		// 查詢ALL
		
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMember_id() + ",");
			System.out.print(aMember.getMember_name() + ",");
			System.out.print(aMember.getMember_account() + ",");
			System.out.print(aMember.getMember_password() + ",");
			System.out.print(aMember.getMember_email() + ",");
			System.out.print(aMember.getMember_phone() + ",");
			System.out.print(aMember.getMember_address() + ",");
			System.out.print(aMember.getMember_state() + ",");
			System.out.print(aMember.getMember_gender() + ",");
			System.out.print(aMember.getMember_birthday() + ",");
			System.out.print(aMember.getMember_img() + ",");
			System.out.println();
		}

	}

	@Override
	public void upimage(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

}