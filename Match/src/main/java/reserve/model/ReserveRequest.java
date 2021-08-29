package reserve.model;

import member.service.User;

public class ReserveRequest {
	private String place;
	private String date;
	private String time;
	private User user;
	private String state;
	
	
	public ReserveRequest(User user, String place, String date, String time) {
		super();
		this.place = place;
		this.date = date;
		this.time = time;
		this.user=user;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
