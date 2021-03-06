package member.model;

public class Member {
	private String id;
	private String name;	
	private String password;
	private String tel;
	private String author;
	
	public Member(String id, String name, String password, String tel) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.tel = tel;
	}
	
	public Member(String id, String name, String password, String tel, String author) {
		this(id,name,password,tel);
		this.author=author;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
	
	public void changeTel(String tel)
	{
		this.tel=tel;
	}
	
}
