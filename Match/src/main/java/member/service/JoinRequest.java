package member.service;

import java.util.Map;

public class JoinRequest {

	private String id;
	private String name;
	private String password;
	private String confirm;
	private String tel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public boolean isPasswordEqualToConfirm() {
		return password!=null && password.equals(confirm);
	}
	
	public void validate(Map<String, Boolean> errors)
	{
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirm, "confirm");
		checkEmpty(errors, tel, "tel");
		
		if(!errors.containsKey("confirm"))
		{
			if(!isPasswordEqualToConfirm())
			{
				errors.put("notMatch",Boolean.TRUE);
			}
		}	
	}
	
	private void checkEmpty(Map<String,Boolean> errors,String value, String fieldName)
	{
		if(value==null || value.isEmpty())
		{
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
}
