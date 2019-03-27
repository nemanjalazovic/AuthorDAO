package com.lazovic.demorest.model;

public class Client {
	private int id_client;
	private String name;
	private String surname;
	private String username;
	private String password;

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int id_client, String name, String surname, String username,
			String password) {
		super();
		this.id_client = id_client;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}

	public Client(String name, String surname, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", name=" + name
				+ ", surname=" + surname + ", username=" + username
				+ ", password=" + password + "]" + "\n";
	}

}
