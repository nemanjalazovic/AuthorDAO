package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import pkg.Client;

public interface IClientDAO {
	public Client getClient(int id);

	public ArrayList<Client> getAllClients();

	public boolean insertClient(Client client) throws ClassNotFoundException;

	public boolean updateClient(Client client) throws ClassNotFoundException;

	public boolean deleteClient(int id) throws ClassNotFoundException;

}
