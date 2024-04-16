package memberHB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class MemberDAO implements MemberDAO_interface {

	private SessionFactory factory;

	public MemberDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(MemberVO memberVO) {

	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void upimage(MemberVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer memberId) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberVO findByPrimaryKey(Integer memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
package memberHB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;


public class MemberDAO implements MemberDAO_interface{
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
		private SessionFactory factory;
		
		public MemberDAO() {
			factory = HibernateUtil.getSessionFactory();
		}
		
		// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
		// 以避免請求執行緒共用了同個 Session
		private Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public void insert(MemberVO memberVO) {
			
			
		}

		@Override
		public void update(MemberVO memberVO) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Integer memberId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void upimage(MemberVO memberVO) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public MemberVO findByPrimaryKey(Integer memberId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<MemberVO> getAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static void main(String[] args) {
			MemberDAO test =new MemberDAO();
			test.insert(null);
		}
}
