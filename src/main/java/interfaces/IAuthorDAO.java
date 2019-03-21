package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import pkg.Author;

public interface IAuthorDAO {
	public String GetVersion() throws SQLException;

	public Author getAuthor(int id) throws SQLException;

	public ArrayList<Author> getAllAuthors() throws SQLException;

	public boolean insertAuthor(Author author) throws SQLException;

	public boolean updateAuthor(Author author) throws SQLException;

	public boolean deleteAuthor(int id) throws SQLException;

}