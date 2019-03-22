package Services;

import java.sql.SQLException;
import java.util.Date;

import pkg.Client;
import pkg.LoginUser;
import DAO.ClientDAO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginService {
	ClientDAO dao = new ClientDAO();

	public String Login(LoginUser log) throws ClassNotFoundException,
			SQLException {
		Client client = dao.Login(log);
		String jwt = "";
		if (client.getUsername() != "") {
			Long time = System.currentTimeMillis();
			jwt = Jwts.builder()
					.claim("id", client.getId_client())
					.setSubject(String.valueOf(client.getId_client()))
					.claim("name", client.getName())
					.claim("surname", client.getSurname())
					// .claim("role", klijent.getRole())
					.setExpiration(new Date(time + 90000))
					.signWith(SignatureAlgorithm.HS256, "password".getBytes())
					.compact();
			// json=Json.createObjectBuilder().add(jwt).build();
			return jwt;
		}
		return jwt;
	}

}
