package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

/**
 * The class rappresent the show concept
 */
public class Show implements Serializable {
	private static final long serialVersionUID = 68217500247298879L;
	/**
	 * Constructs the show with the title, film, the price, the hall and the date
	 * @param title the title of the show
	 * @param film the film that is screened in this show
	 * @param fullPriceTicket the full price of the ticket
	 * @param hall the hall where is taken the projection
	 * @param date of the show
	 */
	public Show(String title, String film, double fullPriceTicket, Hall hall, LocalDateTime date) {
		this.title = title;
		this.film = film;
		this.fullPriceTicket = fullPriceTicket;
		this.date = date;
		this.hall = hall;
		
		this.reservableSeats = new ArrayList<>();
		for(int i=0;i<hall.getNumberOfSeats();i++)
			this.reservableSeats.add(StateReservableSeat.Free);
	}
	
	/**
	 * The method return the full price of the ticket
	 * @return the full price of the ticket
	 */
	public double getFullPriceTicket() {
		return fullPriceTicket;
	}
	
	/**
	 * Set the full price of the ticket
	 * @param fullPriceTicket the full price of the ticket
	 */
	protected void setFullPriceTicket(double fullPriceTicket) {
		this.fullPriceTicket = fullPriceTicket;
	}
	
	/**
	 * The method return the date of the show
	 * @return data of projection of the show
	 */
	public LocalDateTime getDate() {
		return date;
	}
	
	/**
	 * The method return the film that is projected in this show
	 * @return film that is projected in this show
	 */
	public String getFilm() {
		return film;
	}
	
	/**
	 * The method that return the title of the film
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * get the list of state of the reservable seats
	 * @return reservableSeats
	 */
	public List<StateReservableSeat> getReservableSeats() {
		return reservableSeats;
	}
	
	/**
	 * Set the state of the seat identified by a number of seat
	 * @param numberOfSeat the identifier of a seat
	 * @param state the state that we want to set for the seat
	 */
	protected void setStateSeat(int numberOfSeat, StateReservableSeat state) {
		this.reservableSeats.set(numberOfSeat, state);
	}

	/**
	 * Get the state of the seat identified by a number of a seat
	 * @param numberOfSeat the identifier of a seat
	 * @return state of the selected seat
	 */
	public StateReservableSeat getStateSeat(int numberOfSeat) {
		return this.reservableSeats.get(numberOfSeat);
	}
	
	/**
	 * Get the reference to the hall where the show is projected
	 * @return Hall of the projection
	 */
	public Hall getHall() {
		return hall;
	}

	private String film;
	private double fullPriceTicket;
	private List<StateReservableSeat> reservableSeats;
	private Hall hall;
	private LocalDateTime date;
	private String title;
}
