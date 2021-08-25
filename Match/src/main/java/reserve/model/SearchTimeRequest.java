package reserve.model;

public class SearchTimeRequest {

	private String placeName;
	private String date;
	
	public SearchTimeRequest(String placeName, String date) {
		this.placeName = placeName;
		this.date = date;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
