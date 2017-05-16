
public class Book {

	public int id;
	private String title;
	private String author;
	private String publication;
	private String location;
	private String status;
	
	
	
	public Book( String title, String author, String publication, String location) {
		super();
		
		this.title = title;
		this.author = author;
		this.publication = publication;
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Book(int id, String title, String author, String publication, String location, String status) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publication = publication;
		this.location = location;
		this.status=status;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publication=" + publication
				+ ", location=" + location + "]";
	}
	
	
	
	
}
