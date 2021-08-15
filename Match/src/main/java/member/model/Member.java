package member.model;

public class Member {
	private String id;
	private String name;	
	private String password;
	private String tel;
	
	
	public Member(String id, String name, String password, String tel) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getTel() {
		return tel;
	}
	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	
	public void changePassword(String pwd)
	{
		this.password=pwd;
	}
	
}
