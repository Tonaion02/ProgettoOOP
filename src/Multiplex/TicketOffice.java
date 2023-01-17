package Multiplex;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		discounts = new HashMap<>();
		activeDiscounts = new ArrayList<>();
	}
	
	public void addDiscount(String name, Discount discount) {
		discounts.put(name, discount);
	}

	public void activeDiscount(String name) {
		Discount discount = discounts.get(name);
		activeDiscounts.add(discount);
	}
	
	public void deActiveDiscount(String name) {
		Discount discount = discounts.get(name);
		activeDiscounts.remove(discount);
	}
	
	public boolean isActiveDiscount(String name) {
		Discount discount = discounts.get(name);
		boolean active = false;
		for(Discount d : activeDiscounts)
			if(d == discount)
			{
				active = true;
				break;
			}
		
		return active;
	}
	
	public List<String> getDiscountsIdentifiers() {
		return discounts.entrySet().stream().map(e -> e.getKey()).toList();
	}
	
	/**
	 * Buy a ticket without reservation for a client
	 * @param idClient
	 * @param show is the show for we want to buy the ticket
	 * @param number of seat thaw we want to seat
	 * @throws SeatNotAvailable 
	 * @throws SeatAlreadyTaken 
	 */
	public void generateTicket(Client client, Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		
		if(show.getHall().getStateSeat(numberOfSeat).equals(StateSeat.Unavailable))
			throw new SeatNotAvailable();
		
		if(show.getStateSeat(numberOfSeat).equals(StateReservableSeat.Sold))
			throw new SeatAlreadyTaken();
		
		Ticket ticket = new Ticket(selectMinPrice(client, show), client.getIdClient(), show.getFilm(), show.getDate());
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
	private double selectMinPrice(Client client, Show show) {
		double minPrice = show.getFullPriceTicket();
		
		for(Discount discount : activeDiscounts) {
			if(discount.policy(client, show))
			{
				double p = discount.compute(show);
				if(minPrice > p)
					minPrice = p;	
			}
		}
		return minPrice;
	}
	
	private List<Ticket> tickets;
	private Map<String, Discount> discounts;
	private List<Discount> activeDiscounts;
}
