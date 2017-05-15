
public class User {

	int id;
	private String fullName;
	private String email;
	private String password;
	private String type;
	private float totalFine;

	// type is librarian or customer
	public User( String fullName, String email, String type, String password) {
		super();
		
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.type = type;

	}

	public User(int id, String fullName, String email, String password, String type, float totalFine) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.totalFine = totalFine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullName;
	}

	public void setFullname(String fullname) {
		this.fullName = fullname;
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

	public float getTotalFine() {
		return totalFine;
	}

	public void setTotalFine(float totalFine) {
		this.totalFine = totalFine;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", type='" + type + '\'' +
				", totalFine=" + totalFine +
				'}';
	}
}
