package mypage.model;

import member.service.User;

public class CancelInfo {

	private User user;
	private String place_name;
	private String reserve_date;
	private String reserve_tiem;
	private String reg_time;
	
	public CancelInfo(User user, String place_name, String reserve_date, String reserve_tiem, String reg_time) {
	
		this.user = user;
		this.place_name = place_name;
		this.reserve_date = reserve_date;
		this.reserve_tiem = reserve_tiem;
		this.reg_time = reg_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public String getReserve_tiem() {
		return reserve_tiem;
	}

	public void setReserve_tiem(String reserve_tiem) {
		this.reserve_tiem = reserve_tiem;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	@Override
	public String toString() {
		return "CancelInfo [user=" + user + ", place_name=" + place_name + ", reserve_date=" + reserve_date
				+ ", reserve_tiem=" + reserve_tiem + ", reg_time=" + reg_time + "]";
	}
	
	 
}
