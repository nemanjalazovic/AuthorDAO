package com.telusko.demoREST;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.AuthorDAO;
import pkg.Author;

@Path("/authors")
public class authorController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(authorController.class);

	AuthorDAO dao = new AuthorDAO();

	/*@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiOperation(produces = "application/json", value = "Fetch author details", httpMethod = "GET", notes = "<br>This service fetches author details", response = Author.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Author.class, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 422, message = "Invalid data", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	public Response getEmployee() {
		Author empDetails = dao.getAuthor(3);
		Response response;
		LOGGER.debug("Fetching employee");
		GenericEntity<Author> entity = new GenericEntity<Author>(empDetails) {
		};
		response = Response.status(200).entity(entity).build();
		return response;
	}*/
	
	

	@GET
	@Path("/test2")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage() {
		return "pleasure is mine!";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Author> getAllAuthors() {
		return dao.getAllAuthors();
	}

	@GET
	@Path("/{authorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Author getAuthor(@PathParam("authorID") int id) {
		return dao.getAuthor(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addAuthor(Author author) {
		return dao.insertAuthor(author);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{authorID}")
	public boolean updateAuthor(@PathParam("authorID") int id, Author author) {
		author.setId(id);
		return dao.updateAuthor(author);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{authorID}")
	public boolean deleteAuthor(@PathParam("authorID") int id) {
		return dao.deleteAuthor(id);
	}

}
