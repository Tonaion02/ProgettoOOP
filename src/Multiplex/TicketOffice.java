package Multiplex;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Utility.Time;

/**
 Class to generate and handle tickets of the Multiplex
 */
public class TicketOffice implements Serializable {
	private static final long serialVersionUID = 8085697820860729057L;
	/**
	  Construct the TicketOffice
	 */
	public TicketOffice() {
		tickets = new ArrayList<>();
		
		discounts = new ArrayList<>();
	}
	
	/**
	 * Add a discount to the list of the discount 
	 * @param discount is the discount that we want to add to the list of possible discounts
	 */
	public void addDiscount(Discount discount) {
		discounts.add(discount);
	}
	
	/**
	 * Return the list of possible discounts
	 * @return return list of discounts
	 */
	public List<Discount> getDiscounts() {
		return discounts;
	}
	
	public void activeDiscount(int number) {
		discounts.get(number).activate();
	}
	
	public void deactivateDiscount(int number) {
		discounts.get(number).deActivate();
	}
	
	/**
	 * Buy a ticket without reservation for a client
	 * @param idClient is the id of the client that want to buy the ticket
	 * @param show is the show for we want to buy the ticket
	 * @param number of seat thaw we want to seat
	 * @throws SeatNotAvailable 
	 * @throws SeatAlreadyTaken 
	 */
	public void generateTicket(int idClient, Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		
		if(show.getHall().getStateSeat(numberOfSeat).equals(StateSeat.Unavailable))
			throw new SeatNotAvailable();
		
		if(show.getStateSeat(numberOfSeat).equals(StateReservableSeat.Sold))
			throw new SeatAlreadyTaken();
		
		Ticket ticket = new Ticket(selectMinPrice(show), idClient, show.getFilm(), show.getDate());
		tickets.add(ticket);
		
		show.setStateSeat(numberOfSeat, StateReservableSeat.Sold);
	}
	
	/**
	 * @return the revenue of the week
	 */
	public double computeRevenueOfWeek() {
		int diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY);
		System.out.println(diff);
		LocalDateTime startOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).minusDays(diff);
		diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY);
		LocalDateTime endOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).plusDays(-diff);
		
		return tickets.stream().filter(t -> t.getDate().compareTo(startOfWeek) >= 0 && t.getDate().compareTo(endOfWeek) <= 0).mapToDouble(t -> t.getEffectivePrice()).sum();
	}
	
	/**
	 * @param film is the name of the film
	 * @return the revenue of this week for the show that projected a film
	 */
	public double computeRevenueOfFilm(final String film) {
		return tickets.stream().filter(t -> t.getFilm() == film).mapToDouble(t -> t.getEffectivePrice()).sum();
	}
	
	/**
	 * This method compute the minimum price for a show
	 * @param show that we want to calculate the minimum price
	 * @return the minimum price for the ticket
	 */
	private double selectMinPrice(Show show) {
		double minPrice = show.getFullPriceTicket();
		for(Discount d : discounts) {
			if(d.isActive()) {
				double p = d.compute(show);
				if(minPrice > p)
					minPrice = p;	
			}
		}
		
		return minPrice;
	}
	
	private List<Ticket> tickets;
	private List<Discount> discounts;
}
