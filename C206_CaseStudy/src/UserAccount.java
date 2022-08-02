
public class UserAccount {

	private String name;
	private String role;
	private String email;
	private String password;
	private String status;
	private int rating;
	
	
	
	public UserAccount(String name, String role, String email, String password, String status, int rating) {
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.status = status;
		this.rating = rating;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
		
}
