package vn.devpro.javaweb30.dto;

public class UserSignup {
	private String username; 
	private String password; 
	private String retypepassword; 
	private String email; 
	private String mobile;
	
	public UserSignup() {
		super();
	}
	
	
	public UserSignup(String username, String password, String retypepassword, String email, String mobile) {
		super();
		this.username = username;
		this.password = password;
		this.retypepassword = retypepassword;
		this.email = email;
		this.mobile = mobile;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRetypepassword() {
		return retypepassword;
	}
	
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	} 
	
	
	
}
