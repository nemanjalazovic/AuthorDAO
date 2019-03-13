package pkg;

import io.swagger.annotations.ApiModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ApiModel("Author bean")

public class Author {
	private int id;
	private String name;

	public Author(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\n";
	}

}
