package Main;

/**
 * The class rappresent the reservation concept
 */
public class Reservation {
	
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
		
		//SELECT THE SEAT @@@@@@@@@@@
	}
	
	/**
	 * 
	 * @return the id of the Client
	 */
	public int getIdClient() {
		return idClient;
	}
	
	/**
	 * 
	 * @return the show that the reservation regard
	 */
	public Show getShow() {
		return show;
	}

	/**
	 * 
	 * @return the number of seat of this reservation
	 */
	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	private int numberOfSeat;
	private int idClient;
	private Show show;
}
