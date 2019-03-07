package com.telusko.demoREST;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pkg.Author;
import DAO.AuthorDAO;


@Path("/messages")
public class HTTPMethods {
	
	AuthorDAO dao = new AuthorDAO();
	
	/*@GET
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
    @Produces(MediaType.APPLICATION_XML)
    public Author  getAuthor() {
        return dao.getAuthor(5);
    }
    
	*/
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public Author  getAuthor() {
        return dao.getAuthor(1);
    }
	

	
	

}
