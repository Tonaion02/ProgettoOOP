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
	 * @param 
	 * @param show of 
	 * @param numberOfSeat number that represent the seat
	 */
	public Reservation(Client client, Show show, int numberOfSeat) {
		this.client = client;
		this.show = show;
		this.numberOfSeat = numberOfSeat;
	}
	
	public Client getClient() {
		return client;
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
	private Client client;
	private Show show;
}
