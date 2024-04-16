package memberHB;

import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String memberName, String memberAccount, String memberPassword, String memberEmail,
			String memberPhone,String memberAddress, Integer memberState,Integer memberGender, java.sql.Date memberBirthday, byte[] memberImg) {
	
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberName(memberName);
		memberVO.setMemberAccount(memberAccount);
		memberVO.setMemberPassword(memberPassword);
		memberVO.setMemberEmail(memberEmail);
		memberVO.setMemberPhone(memberPhone);
		memberVO.setMemberAddress(memberAddress);
		memberVO.setMemberState(memberState);
		memberVO.setMemberGender(memberGender);
		memberVO.setMemberBirthday(memberBirthday);
		memberVO.setMemberImg(memberImg);
		
		dao.insert(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMember(String memberName, String memberPassword, String memberEmail,
			String memberPhone,String memberAddress, Integer memberState,Integer memberGender, java.sql.Date memberBirthday, Integer memberId) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberName(memberName);
		memberVO.setMemberPassword(memberPassword);
		memberVO.setMemberEmail(memberEmail);
		memberVO.setMemberPhone(memberPhone);
		memberVO.setMemberAddress(memberAddress);
		memberVO.setMemberState(memberState);
		memberVO.setMemberGender(memberGender);
		memberVO.setMemberBirthday(memberBirthday);
		memberVO.setMemberId(memberId);
		
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public MemberVO upImage(byte[] memberImg, Integer memberId) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberImg(memberImg);
		memberVO.setMemberId(memberId);
		
		return memberVO;
	}
	
	public void deleteMember(Integer memberId) {
		dao.delete(memberId);
	}
	
	public MemberVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public List<MemberVO> grtAll(){
		return dao.getAll();
	}
}
