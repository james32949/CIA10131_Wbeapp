package member;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private Integer member_id;
	private String member_name;
	private String member_account;
	private String member_password;
	private String member_email;
	private String member_phone;
	private String member_address;
	private Integer member_state;
	private Integer member_gender;
	private Date member_birthday;
	private byte[] member_img;
	
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public Integer getMember_state() {
		return member_state;
	}
	public void setMember_state(Integer member_state) {
		this.member_state = member_state;
	}
	public Integer getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(Integer memder_gender) {
		this.member_gender = memder_gender;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_barthday) {
		this.member_birthday = member_barthday;
	}
	public byte[] getMember_img() {
		return member_img;
	}
	public void setMember_img(byte[] member_img) {
		this.member_img = member_img;
	}
	
}