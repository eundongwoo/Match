package mypage.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReserveInfo {

	private int reserve_num;
	private String member_id;
	private String place_name;
	private String reserve_date;
	private String reserve_time;
	private String state;
	private String reg_time;
	public ReserveInfo(int reserve_num, String member_id, String place_name, String reserve_date, String reserve_time,
			String state,String reg_time) {
		this.reserve_num = reserve_num;
		this.member_id = member_id;
		this.place_name = place_name;
		this.reserve_date = reserve_date;
		this.reserve_time = reserve_time;
		this.state = state;
		this.reg_time=reg_time;
	}
	public int getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(int reserve_num) {
		this.reserve_num = reserve_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_id(String place_name) {
		this.place_name = place_name;
	}
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_date) {
		this.reg_time = reg_time;
	}
}
