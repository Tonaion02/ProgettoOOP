package Multiplex;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The class rappresent the reservation concept
 */
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs a reservation from 
	 * @param idClient id of the client that created the reservation
	 * @param show of 
	 * @param numberOfSeat number that represent the seat
	 */
	public Reservation(int idClient, Show show, int numberOfSeat) {
		this.idClient = idClient;
		this.show = show;
		this.numberOfSeat = numberOfSeat;
	}
	
	/**
	 * Get the id of the Client
	 * @return the id of the Client
	 */
	public int getIdClient() {
		return idClient;
	}
	
	/**
	 * Get the show that the reservation regard
	 * @return the show that the reservation regard
	 */
	public Show getShow() {
		return show;
	}

	/**
	 * Get the number of seat of this reservation
	 * @return the number of seat of this reservation
	 */
	public int getNumberOfSeat() {
		return numberOfSeat;
	}
	
	/**
	 * Controll if the reservation is expired
	 * @param currentTime the current local date time
	 * @return true if the reservation is expired false in other case
	 */
	public boolean isExpired(LocalDateTime currentTime) {
		return currentTime.plusHours(12).compareTo(show.getDate()) > 0;
	}

	private int numberOfSeat;
	private int idClient;
	private Show show;
}
