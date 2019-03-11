package com.telusko.demoREST;

import java.lang.reflect.Array;
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

import pkg.Author;
import DAO.AuthorDAO;


@Path("/authors")
public class authorController {
	
	AuthorDAO dao = new AuthorDAO();
	

	@GET
	@Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "pleasure is mine!";
    }
    
    
    	    
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Author>  getAllAuthors() {
        return dao.getAllAuthors();
    }
	
	@GET
	@Path("/{authorID}")
    @Produces(MediaType.APPLICATION_XML)
    public Author  getAuthor(@PathParam("authorID") int id) {
        return dao.getAuthor(id);
    }
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public boolean addAuthor(Author author){
		return dao.insertAuthor(author);
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{authorID}")
	public boolean updateAuthor(@PathParam("authorID") int id, Author author){
		author.setId(id);
		return dao.updateAuthor(author);
	}
    
	
	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{authorID}")
	public boolean deleteAuthor(@PathParam("authorID") int id){
		return dao.deleteAuthor(id);
	}
    
    

	
	

}
