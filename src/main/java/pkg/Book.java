package pkg;

public class Book {
	private int id;
	private String author;
	private String title;

	public Book() {
		super();
	}

	public Book(int id, String author, String title) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
	}

	public Book(int id, String title) {
		super();
		this.title = title;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return id + author + title + "\n";
	}

}
