package map.model;

public class Place {

	private int f_id;
	private String f_name;
	private String f_addr;
	private String f_tel;
	
	public Place(int f_id, String f_name, String f_addr, String f_tel) {
		this.f_id = f_id;
		this.f_name = f_name;
		this.f_addr = f_addr;
		this.f_tel = f_tel;
	}
	public Place(String f_name, String f_addr, String f_tel) {
		this.f_name = f_name;
		this.f_addr = f_addr;
		this.f_tel = f_tel;
	}
	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_addr() {
		return f_addr;
	}

	public void setF_addr(String f_addr) {
		this.f_addr = f_addr;
	}

	public String getF_tel() {
		return f_tel;
	}

	public void setF_tel(String f_tel) {
		this.f_tel = f_tel;
	}
}
