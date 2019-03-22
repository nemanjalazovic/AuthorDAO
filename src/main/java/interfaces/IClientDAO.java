package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import pkg.Client;

public interface IClientDAO {
	public Client getClient(int id) throws SQLException;

	public ArrayList<Client> getAllClients() throws SQLException;

	public boolean insertClient(Client client) throws ClassNotFoundException,
			SQLException;

	public boolean updateClient(Client client) throws ClassNotFoundException,
			SQLException;

	public boolean deleteClient(int id) throws ClassNotFoundException,
			SQLException;

}
