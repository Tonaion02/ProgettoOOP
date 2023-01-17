package Multiplex;

public class Client {
	public Client(int idClient, String category) {
		this.idClient = idClient;
		this.category = category;
	}
	
	public boolean equals(Object object) {
		if(object instanceof Client)
		{
			Client other = (Client)object;
			if(idClient == other.getIdClient())
				return true;
		}
		
		return false;
	}
	
	public int getIdClient() {
		return idClient;
	}
	
	public String getCategory() {
		return category;
	}
	
	private int idClient;
	private String category;
}
