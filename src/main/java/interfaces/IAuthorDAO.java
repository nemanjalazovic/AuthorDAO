package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import pkg.Author;

public interface IAuthorDAO {
	public String GetVersion() throws SQLException;

	public Author getAuthor(int id);

	public ArrayList<Author> getAllAuthors();

	public boolean insertAuthor(Author author);

	public boolean updateAuthor(Author author);

	public boolean deleteAuthor(int id);

}