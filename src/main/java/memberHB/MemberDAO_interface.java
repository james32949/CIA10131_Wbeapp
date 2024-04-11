package memberHB;

import java.util.*;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(Integer memberId);
	public MemberVO findByPrimaryKey(Integer memberId);
	public List<MemberVO> getAll();
}