package member.controller;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;

import member.*;

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp 請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 輸入錯誤處理 **********************/
			String str = req.getParameter("member_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer member_id = null;
			try {
				member_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 中斷程式
			}

			/*************************** 2.資料查詢 *****************************************/
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneMember(member_id);
			if (memVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 中斷程式
			}

			/*************************** 3.完成查詢(Send the Success view) *************/
			req.setAttribute("memVO", memVO); // 資料庫取出MemberVO物件 存入req
			String url = "/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交成功 listOneMember.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求 ****************************************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));

			/*************************** 2.查詢 ****************************************/
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneMember(member_id);

			/*************************** 3.查詢完成 準備轉移(Send the Success view) ************/
			req.setAttribute("memVO", memVO); 
			String url = "/member/update_mem_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
/*********************************************************************************************************/		

		if ("update".equals(action)) { // 來自update_mem_input.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());

			String member_name = req.getParameter("member_name");
			
			System.out.println(member_name+"!!");
			
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_name == null || member_name.trim().length() == 0) {
				errorMsgs.add("會員姓名:請勿空白");
			} else if (!member_name.trim().matches(enameReg)) { 
				errorMsgs.add("會員姓名:只能是中、英文字母、數字和_ 且長度在2~10之間");
			}
			
			String member_password = req.getParameter("member_password").trim();
			if (member_password == null || member_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			
			String member_email = req.getParameter("member_email").trim();
			if (member_email == null || member_email.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			}
			
			String member_phone = req.getParameter("member_phone").trim();
			if (member_phone == null || member_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}
			
			String member_address = req.getParameter("member_address").trim();
			
			Integer member_state = Integer.valueOf(req.getParameter("member_state").trim());
			
			Integer member_gender = Integer.valueOf(req.getParameter("member_gender").trim());
			
			java.sql.Date member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
												
			MemberVO memVO = new MemberVO();
			
			memVO.setMember_id(member_id);
			memVO.setMember_name(member_name);

			memVO.setMember_password(member_password);
			memVO.setMember_email(member_email);
			memVO.setMember_phone(member_phone);			
			memVO.setMember_address(member_address);
			memVO.setMember_state(member_state);
			memVO.setMember_gender(member_gender);
			memVO.setMember_birthday(member_birthday);

			
/*********************************************************************************************************************************/			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
					

				/*************************** 2.查詢 ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO2 = memSvc.getOneMember(member_id);

				/*************************** 3.查詢完成 準備轉移(Send the Success view) ************/
				req.setAttribute("memVO", memVO2); 
				/******************************************************************************/
				
				RequestDispatcher failureView = req.getRequestDispatcher("/member/update_mem_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料*****************************************/
			MemberService memSvc = new MemberService();
			memVO = memSvc.updateMember(member_name, member_password, member_email, member_phone, member_address, member_state, member_gender, member_birthday, null, member_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
			successView.forward(req, res);
			
			/******************************圖片上傳*************************************************************/
			String member_img= req.getParameter("member_img").trim();
			System.out.println(member_img);

			
//			String sqlImg = "UPDATE  member SET member_img=? WHERE member_id=?;";
//			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//				String columns[] = { "member_img" };
//				int employee_id = -1;
//				try (PreparedStatement ps = connection.prepareStatement(sqlImg, columns);
//						InputStream in = Files.newInputStream(Path.of("img/image.png"))) {;
//					// 讀入圖檔後插入
//					ps.setBinaryStream(1, in, in.available());
//					ps.setInt(2, 1);
//					
//					System.out.println("上傳成功");
//					
//					int rowCount = ps.executeUpdate();
//					/*
//					 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
//					 * resources小括號內，參看ResultSet說明
//					 */
//					ResultSet rs = ps.getGeneratedKeys();
//					if (rs.next()) {
//						employee_id = rs.getInt(1);
//						System.out.println(rowCount + " row inserted; employee ID: " + employee_id);
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			
		}

		/*********************************************************************************************************/	
		
		if ("insert".equals(action)) { // 來自addMember

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求 處理錯誤 *************************/
			String member_name = req.getParameter("member_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_name == null || member_name.trim().length() == 0) {
				errorMsgs.add("會員姓名:請勿空白");
			} else if (!member_name.trim().matches(enameReg)) { 
				errorMsgs.add("會員姓名:只能是中、英文字母、數字和_ 且長度在2~10之間");
			}

			String member_account = req.getParameter("member_account").trim();
			if (member_account == null || member_account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			
			String member_password = req.getParameter("member_password").trim();
			if (member_password == null || member_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			
			String member_email = req.getParameter("member_email").trim();
			if (member_email == null || member_email.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			}
			
			String member_phone = req.getParameter("member_phone").trim();
			if (member_phone == null || member_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}

//			java.sql.Date birthday = null;
//			try {
//				birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
//			} catch (IllegalArgumentException e) {
//				birthday = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("�п�J���!");
//			}


			MemberVO memVO = new MemberVO();
			
			memVO.setMember_name(member_name);
			memVO.setMember_account(member_account);
			memVO.setMember_password(member_password);
			memVO.setMember_email(member_email);
			memVO.setMember_phone(member_phone);
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始增新 ***************************************/
			MemberService memSvc = new MemberService();
			memVO = memSvc.addMember(member_name, member_account, member_password, member_email, member_phone, "測試用資料", 0, 0, null, null);

			/*************************** 3.增新完成(Send the Success view) ***********/
			String url = "/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));

			/*************************** 2.刪除資料***************************************/
			MemberService memSvc = new MemberService();
			memSvc.deleteMember(member_id);

			/*************************** 3.刪除完成(Send the Success view) ***********/
			String url = "/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
