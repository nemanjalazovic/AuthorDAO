package interfaces;

import java.util.ArrayList;

import pkg.Author;
import pkg.Book;

public interface IBookDAO {
	public Book getBook(int id);

	public ArrayList<Book> getAllBooks();

	public boolean insertBook(Author author, Book book);

	public boolean updateBook(Book book);

	public boolean deleteBook(int id);

}
