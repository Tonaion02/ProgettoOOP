package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle the reservations
 */
public class ReservationHandler implements Serializable {
	private static final long serialVersionUID = 4355305844034035582L;

	/**
	 * Constructs ReservationHandler
	 */
	public ReservationHandler() {
		reservations = new ArrayList<>();
	}
	
	/**
	 * With this method we can create the reservation
	 * @param idClient id of the client that want to do the reservation
	 * @param show is the show that we want to prenotate
	 * @param numberOfSeat is the number of the seat that the client want to prenotate 
	 * @throws SeatNotAvailable 
	 * @throws SeatAlreadyTaken 
	 */
	public void createReservation(int idClient, Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		
		if(show.getHall().getStateSeat(numberOfSeat).equals(StateSeat.Unavailable))
			throw new SeatNotAvailable();
		
		if(! show.getStateSeat(numberOfSeat).equals(StateReservableSeat.Free))
			throw new SeatAlreadyTaken();
		
		Reservation reservation = new Reservation(idClient, show, numberOfSeat);
		reservations.add(reservation);

		show.setStateSeat(numberOfSeat, StateReservableSeat.Reserved);
	}
	
	/**
	 * The method delete the reservation
	 * @param reservation is the reservation we want to delete 
	 */
	public void deleteReservation(Reservation reservation) {
		for(int i=0;i<reservations.size();i++) 
			if(reservation == reservations.get(i)) {
				reservations.remove(i);	
				break;
			}
		
		reservation.getShow().setStateSeat(reservation.getNumberOfSeat(), StateReservableSeat.Free);
	}
	
	/**
	 * The method retrieve all the reservation of the system
	 * @return the list of reservations that is effectuated
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	private List<Reservation> reservations;
}
