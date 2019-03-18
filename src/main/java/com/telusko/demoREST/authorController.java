package com.telusko.demoREST;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

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
@Api("/Authors ")
@SwaggerDefinition(tags = { @Tag(name = "/Authors", description = "REST Endpoints for Authors") })
public class authorController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(authorController.class);

	AuthorDAO dao = new AuthorDAO();

	/*
	 * @GET
	 * 
	 * @Path("/test2")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String getMessage() { return
	 * "pleasure is mine!"; }
	 */

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
