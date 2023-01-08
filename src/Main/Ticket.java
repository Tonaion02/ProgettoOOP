package Main;

public class Ticket {
	
	/**
	 * Constructs Ticket from effectivePrice, idClient and the show
	 * @param effectivePrice the effective price calculated for the ticket
	 * @param show the show that regards the ticket
	 * @param idClient the client that bought the ticket
	 */
	public Ticket(double effectivePrice, Show show, int idClient) {
		super();
		this.effectivePrice = effectivePrice;
		this.show = show;
		this.idClient = idClient;
	}
	
	/**
	 * 
	 * @return the effective price of ticket
	 */
	public double getEffectivePrice() {
		return effectivePrice;
	}
	
	/**
	 * 
	 * @return the show that regards the ticket
	 */
	public Show getShow() {
		return show;
	}
	
	/**
	 * 
	 * @return the id of the client
	 */
	public int getIdClient() {
		return idClient;
	}

	private double effectivePrice;
	private Show show;
	private int idClient;
}
