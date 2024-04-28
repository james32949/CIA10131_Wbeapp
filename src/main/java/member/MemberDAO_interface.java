package member;

import java.util.List;


public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(Integer memberId);
	public void upimage(MemberVO memberVO);
	public MemberVO findByPrimaryKey(Integer memberId);
	
	public List<Object[]> findByAccount(String account);
	
	public List<MemberVO> getAll();
	
	public List<MemberVO> query(MemberVO memberVO);
	
	public void upState(Integer memberState, Integer memberId);
}