package Multiplex;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 572478323850917103L;
	/**
	 * Constructs Ticket from effectivePrice, idClient and the show
	 * @param effectivePrice the effective price calculated for the ticket
	 * @param idClient the client that bought the ticket
	 */
	public Ticket(double effectivePrice, int idClient, String film, LocalDateTime date) {
		this.effectivePrice = effectivePrice;
		this.idClient = idClient;
		this.film = film;
		this.date = date;
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
	 * @return the id of the client
	 */
	public int getIdClient() {
		return idClient;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public String getFilm() {
		return film;
	}

	private double effectivePrice;
	private int idClient;
	private LocalDateTime date;
	private String film;
}
