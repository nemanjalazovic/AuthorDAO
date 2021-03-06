package com.lazovic.demorest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lazovic.demorest.dao.BookDAO;
import com.lazovic.demorest.model.Author;
import com.lazovic.demorest.model.Book;

@Path("/books")
@Api("/Books ")
@SwaggerDefinition(tags = { @Tag(name = "/Books", description = "REST Endpoints for Books") })
public class BookController {

	BookDAO dao = new BookDAO();

	/*
	 * @GET
	 * 
	 * @Path("/test")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String getMessage() { return
	 * "pleasure is mine!"; }
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Book> getAllBooks() throws SQLException {
		return dao.getAllBooks();
	}

	@GET
	@Path("/{bookID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("bookID") int id) throws SQLException {
		return dao.getBook(id);
	}

	/*
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public boolean addBook(Author
	 * author, Book book) { return dao.insertBook(author, book); }
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{bookID}")
	public boolean updateBook(@PathParam("bookID") int id, Book book)
			throws SQLException {
		book.setId(id);
		return dao.updateBook(book);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{bookID}")
	public boolean deleteBook(@PathParam("bookID") int id) throws SQLException {
		return dao.deleteBook(id);
	}

}
