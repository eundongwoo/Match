package member.service;

public class User {
	private String id;
	private String name;
	private String tel;
	private String author;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id, String name,String tel) {
		this.id = id;
		this.name = name;
		this.tel=tel;
	}
	
	
	public User(String id, String name, String tel, String author) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.author = author;
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

	public String getTel() {
		return tel;
	}		
	
	public void setTel(String tel)
	{
		this.tel=tel;
	}
}
