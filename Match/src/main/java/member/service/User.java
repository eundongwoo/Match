package member.service;

public class User {
	private String id;
	private String name;
	private String tel;
	
	public User(String id, String name,String tel) {
		this.id = id;
		this.name = name;
		this.tel=tel;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}		
	
	public void setTel(String tel)
	{
		this.tel=tel;
	}
}
