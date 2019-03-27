package com.lazovic.demorest.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.lazovic.demorest.model.Author;
import com.lazovic.demorest.model.Book;

public interface IBookDAO {
	public Book getBook(int id) throws SQLException;

	public ArrayList<Book> getAllBooks() throws SQLException;

	public boolean insertBook(Author author, Book book) throws SQLException;

	public boolean updateBook(Book book) throws SQLException;

	public boolean deleteBook(int id) throws SQLException;

}
