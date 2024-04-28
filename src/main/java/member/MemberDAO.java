package member;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public class MemberDAO implements MemberDAO_interface {

	@Override
	public void insert(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();
			session.save(memberVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(memberVO.getMemberName());
		try {

			session.beginTransaction();

			MemberVO mem = session.get(MemberVO.class, memberVO.getMemberId());

			mem.setMemberName(memberVO.getMemberName());
//			mem.setMemberAccount(memberVO.getMemberAccount()); //帳號不能修改
			mem.setMemberPassword(memberVO.getMemberPassword());
			mem.setMemberEmail(memberVO.getMemberEmail());
			mem.setMemberPhone(memberVO.getMemberPhone());
			mem.setMemberAddress(memberVO.getMemberAddress());
			mem.setMemberState(memberVO.getMemberState());
			mem.setMemberGender(memberVO.getMemberGender());
			mem.setMemberBirthday(memberVO.getMemberBirthday());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void delete(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();
			MemberVO memberVO = session.get(MemberVO.class, memberId);
			if (memberVO != null) {
				session.delete(memberVO);
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void upimage(MemberVO memberVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();
			MemberVO img = session.get(MemberVO.class, memberVO.getMemberId());
			if (img != null) {
				img.setMemberImg(memberVO.getMemberImg());
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			MemberVO memberVO = session.get(MemberVO.class, memberId);
			session.getTransaction().commit();
			return memberVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<MemberVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<MemberVO> list = session.createQuery("from MemberVO", MemberVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public List<MemberVO> query(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		String memName = memberVO.getMemberName();
		String memPhone = memberVO.getMemberPhone();
		String memEmail = memberVO.getMemberEmail();

		if (memName == null && memPhone == null && memEmail == null) { // 使用者位輸入任何資訊 回傳所有會員資料
			session.beginTransaction();
			List<MemberVO> list = session.createQuery("from MemberVO", MemberVO.class).list();
			session.getTransaction().commit();
			return list;
		}
		if (memName != null) { // 姓名查詢
			String hql = "from MemberVO where memberName like ";
			String input = "'%" + memName + "%'"; // 模糊比對

			session.beginTransaction();

			List<MemberVO> list = session.createQuery(hql + input, MemberVO.class).list();
			session.getTransaction().commit();

			return list;
		}
		if (memPhone != null) { // 電話查詢
			String hql = "from MemberVO where memberPhone like ";
			String input = "'%" + memPhone + "%'"; // 模糊比對

			session.beginTransaction();

			List<MemberVO> list = session.createQuery(hql + input, MemberVO.class).list();
			session.getTransaction().commit();

			return list;
		}
		if (memEmail != null) { // 信箱查詢
			String hql = "from MemberVO where memberEmail like ";
			String input = "'%" + memEmail + "%'"; // 模糊比對

			session.beginTransaction();

			List<MemberVO> list = session.createQuery(hql + input, MemberVO.class).list();
			session.getTransaction().commit();

			return list;
		}
		return null;
	}

	@Override
	public void upState(Integer memberState, Integer memberId) {// 權限修改

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//		System.out.println("DAO:State="+memberState);
//		System.out.println("DAO:ID="+memberId);

		try {

			session.beginTransaction();
			MemberVO State = session.get(MemberVO.class, memberId);
			if (State != null) {
				State.setMemberState(memberState);
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public List<Object[]> findByAccount(String account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		String hql = "select memberPassword, memberId from MemberVO where memberAccount = :account";

		try {
			session.beginTransaction();

			Query<Object[]> query = session.createQuery(hql, Object[].class);

			List<Object[]> list = query.setParameter("account", account).list();
			
//			for(Object[] objs : list) {
//				System.out.println("memberPassword:"+objs[0]+"\nmemberId:"+objs[1]);
//			}

			session.getTransaction().commit();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}

	public static void main(String[] args) {
		MemberDAO mem = new MemberDAO();
		System.out.println(mem.findByAccount("cia10101"));
	}
}
