package member;

import java.util.*;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(Integer member_id);
	public MemberVO findByPrimaryKey(Integer member_id);
	public List<MemberVO> getAll();
}