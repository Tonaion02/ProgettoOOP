package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The class rappresent the show concept
 */
public class Show implements Serializable {
	private static final long serialVersionUID = 68217500247298879L;
	/**
	 * Constructs the show with the film, the price, the hall and the day
	 * @param film the film that is screened in this show
	 * @param fullPriceTicket the full price of the ticket
	 * @param hall the hall where is taken the projection
	 * @param day the day of the projection
	 */
	public Show(String film, double fullPriceTicket, Hall hall, DayOfWeek day) {
		this.film = film;
		this.fullPriceTicket = fullPriceTicket;
		this.day = day;
		this.hall = hall;
		
		this.reservableSeats = new ArrayList<>();
		for(int i=0;i<hall.getNumberOfSeats();i++)
			this.reservableSeats.add(StateReservableSeat.Free);
	}
	
	/**
	 * 
	 * @return the full price of the ticket
	 */
	public double getFullPriceTicket() {
		return fullPriceTicket;
	}
	
	/**
	 * Set the full price of the ticket
	 * @param fullPriceTicket the full price of the ticket
	 */
	public void setFullPriceTicket(double fullPriceTicket) {
		this.fullPriceTicket = fullPriceTicket;
	}
	
	/**
	 * 
	 * @return day of the week when the show is projected
	 */
	public DayOfWeek getDay() {
		return day;
	}
	
	/**
	 * Set the day of the week
	 * @param day
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
	
	/**
	 * 
	 * @return film that is projected in this show
	 */
	public String getFilm() {
		return film;
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
	public void setStateSeat(int numberOfSeat, StateReservableSeat state) {
		this.reservableSeats.set(numberOfSeat, state);
	}

	/**
	 * 
	 * @param numberOfSeat the identifier of a seat
	 * @return state of the selected seat
	 */
	public StateReservableSeat getStateSeat(int numberOfSeat) {
		return this.reservableSeats.get(numberOfSeat);
	}
	
	/**
	 * @return Hall of the projection
	 */
	public Hall getHall() {
		return hall;
	}

	private String film;
	private double fullPriceTicket;
	private List<StateReservableSeat> reservableSeats;
	private DayOfWeek day;
	private Hall hall;
}
