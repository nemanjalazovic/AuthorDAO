package com.lazovic.demorest.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lazovic.demorest.annotation.JWTSecured;
import com.lazovic.demorest.dao.ClientDAO;
import com.lazovic.demorest.model.Client;
import com.lazovic.demorest.model.LoginUser;
import com.lazovic.demorest.services.LoginService;

@Path("/clients")
@Api("/Clinets ")
@SwaggerDefinition(tags = { @Tag(name = "/Clinets", description = "REST Endpoints for Clinets") })
public class ClientContoller {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthorController.class);

	ClientDAO dao = new ClientDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Client> getAllClinets()
			throws SQLException {
		// System.out.println("Token"+request.getHeader("Authorization"));
		return dao.getAllClients();
	}

	@GET
	@JWTSecured	
	@Path("/{clientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClient(@PathParam("clientID") int id, @Context UriInfo uriInfo) throws SQLException {
		Client client = dao.getClient(id);
		URI uri=uriInfo.getAbsolutePathBuilder().path(Integer.toString(client.getId_client())).build();

		return Response.created(uri).entity(client).build();
		
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addClient(Client client) throws SQLException,
			ClassNotFoundException {
		return dao.insertClient(client);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{clientID}")
	public boolean updateClient(@PathParam("clientID") int id, Client client)
			throws SQLException, ClassNotFoundException {
		client.setId_client(id);
		return dao.updateClient(client);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{clientID}")
	public boolean deleteAuthor(@PathParam("clientID") int id)
			throws SQLException, ClassNotFoundException {
		return dao.deleteClient(id);
	}

	@POST
	@Path("/login")
	public Response Login(LoginUser log) {
		LoginService srv = new LoginService();
		try {
			if (srv.Login(log) != "") {
				return Response.ok(srv.Login(log))
						.entity(javax.json.Json.createValue(srv.Login(log)))
						.build();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Resource not found");
			LOGGER.error("Resource not found!", e);
		} catch (SQLException e) {
			System.out.println("eror");
			e.printStackTrace();
		} catch (java.lang.NullPointerException e) {
			LOGGER.error("Error!", e);

			return Response
					.status(Status.CONFLICT)
					.entity(javax.json.Json
							.createValue("Wrong username or password")).build();
		}
		return Response.status(Status.CONFLICT)
				.header("Access-Control-Allow-Origin", "*")
				.entity("Wrong password!!!")
				.header("Access-Control-Allow-Methods", "POST")
				.allow("OPTIONS").build();
	}

}
