package member;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;
import static common.Common.DRIVER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements MemberDAO_interface {
	
	
	private static final String INSER_STMT = "INSERT INTO member (member_name, member_account, member_password, member_email, member_phone, member_address, member_state, member_gender, member_birthday ,member_img)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_id,member_name,member_account,member_password,member_email,member_phone,member_address,member_state,member_gender,member_birthday,member_img FROM member";
	private static final String GET_ONE_STMT = "SELECT member_id,member_name,member_account,member_password,member_email,member_phone,member_address,member_state,member_gender,member_birthday,member_img FROM member WHERE member_id =?";
	private static final String DELETE = "DELETE FROM member WHERE member_id =?";
	private static final String UPDATE = "UPDATE member set member_name=?,member_password=?,member_email=?,member_phone=?,member_address=?,member_state=?, member_gender=?,member_birthday=? WHERE member_id=?";

	@Override
	public void insert(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
						
			pstmt.setString(1,memberVO.getMember_name());
			pstmt.setString(2,memberVO.getMember_password());
			pstmt.setString(3,memberVO.getMember_email());
			pstmt.setString(4,memberVO.getMember_phone());
			pstmt.setString(5,memberVO.getMember_address());
			pstmt.setInt(6,memberVO.getMember_state());
			pstmt.setInt(7,memberVO.getMember_gender());
			pstmt.setDate(8,memberVO.getMember_birthday());
			pstmt.setInt(9,memberVO.getMember_id());
			
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

}
