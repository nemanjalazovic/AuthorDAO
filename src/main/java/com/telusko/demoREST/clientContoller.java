package com.telusko.demoREST;

import java.sql.SQLException;
import java.util.ArrayList;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pkg.Client;
import pkg.LoginUser;
import DAO.ClientDAO;
import Services.LoginService;

@Path("/clients")
@Api("/Clinets ")
@SwaggerDefinition(tags = { @Tag(name = "/Clinets", description = "REST Endpoints for Clinets") })
public class clientContoller {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(authorController.class);

	ClientDAO dao = new ClientDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Client> getAllClinets() throws SQLException {
		return dao.getAllClients();
	}

	@GET
	@Path("/{clientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClient(@PathParam("clientID") int id) throws SQLException {
		return dao.getClient(id);
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
	public javax.ws.rs.core.Response Login(LoginUser log) {
		LoginService srv = new LoginService();
		try {
			if (srv.Login(log) != "") {
				return javax.ws.rs.core.Response.ok(srv.Login(log))
						.entity(javax.json.Json.createValue(srv.Login(log)))
						.build();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Resource not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("eror");
			e.printStackTrace();
		} catch (java.lang.NullPointerException e) {
			System.out.println("erorrrr");
			return javax.ws.rs.core.Response
					.status(Status.CONFLICT)
					.entity(javax.json.Json
							.createValue("Wrong username or password")).build();
		}
		return javax.ws.rs.core.Response.status(Status.CONFLICT)
				.header("Access-Control-Allow-Origin", "*")
				.entity("Wrong password!!!")
				.header("Access-Control-Allow-Methods", "POST")
				.allow("OPTIONS").build();
	}

	/*
	 * @POST
	 * 
	 * @Path( "login" )
	 * 
	 * @PermitAll public Response login( final LoginRequest loginRequest ) { try
	 * { User user = userService.authenticate( loginRequest.getUsername(),
	 * loginRequest.getPassword() ); String jwt = jwtManager.createToken(
	 * user.getName(), user.getRole() ); LoginResponse response = new
	 * LoginResponse( user.getName(), user.getRole(), jwt ); return
	 * Response.ok().entity( response ).build(); } catch (
	 * AuthenticationException e ) { LOG.warning( e.getMessage() ); return
	 * Response.noContent().status( UNAUTHORIZED ).build(); } }
	 */
}
