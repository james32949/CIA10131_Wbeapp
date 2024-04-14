package member;

import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String member_name, String member_account, String member_password, String member_email,
			String member_phone,String member_address, Integer member_state,Integer member_gender, java.sql.Date member_birthday, byte[] member_img) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_name(member_name);
		memberVO.setMember_account(member_account);
		memberVO.setMember_password(member_password);
		memberVO.setMember_email(member_email);
		memberVO.setMember_phone(member_phone);
		memberVO.setMember_address(member_address);
		memberVO.setMember_state(member_state);
		memberVO.setMember_gender(member_gender);
		memberVO.setMember_birthday(member_birthday);
		memberVO.setMember_img(member_img);
		dao.insert(memberVO);
		
		return memberVO;
		
	}
	
	public MemberVO updateMember(String member_name, String member_password, String member_email,
			String member_phone,String member_address, Integer member_state,Integer member_gender, java.sql.Date member_birthday, byte[] member_img, Integer member_id) {
		
		MemberVO memberVO = new MemberVO();
		
		
		memberVO.setMember_name(member_name);

		memberVO.setMember_password(member_password);
		memberVO.setMember_email(member_email);
		memberVO.setMember_phone(member_phone);
		memberVO.setMember_address(member_address);
		memberVO.setMember_state(member_state);
		memberVO.setMember_gender(member_gender);
		memberVO.setMember_birthday(member_birthday);
		memberVO.setMember_img(member_img);
		memberVO.setMember_id(member_id);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void deleteMember(Integer member_id) {
		dao.delete(member_id);
	}
	
	public MemberVO getOneMember(Integer member_id) {
		return dao.findByPrimaryKey(member_id);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
}
