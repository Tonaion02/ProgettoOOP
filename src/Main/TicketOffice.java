package Main;

import java.util.ArrayList;
import java.util.List;

/**
 Class to generate and handle tickets of the Multiplex
 */
public class TicketOffice {
	
	/**
	  Construct the TicketOffice
	 */
	public TicketOffice() {
		tickets = new ArrayList<>();
		
		discounts = new ArrayList<>();
		activeDiscounts = new ArrayList<>();
		
	}
	
	/**
	  Activate one of the discount, add the discount to the collection of active discounts
	  @param discount is the discount that we want to add to the list
	 */
	public void activeDiscount(Discount discount) {
		activeDiscounts.add(discount);
	}
	
	/**
	  Deactivate one of the discount, remove the discount to the collection of active discounts 
	  @param discount is the discount that we want to remove from list
	 */
	public void deactivateDiscount(Discount discount) {
		for(int i=0;i<activeDiscounts.size();i++) {
			activeDiscounts.remove(i);
		}
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
	
	/**
	 * Buy a ticket without reservation for a client
	 * @param idClient is the id of the client that want to buy the ticket
	 * @param show is the show for we want to buy the ticket
	 */
	public void buyTicket(int idClient, Show show) {
		
	}
	
	/**
	 * Buy a ticket with a reservation
	 * @param idClient is the id of the client that want to buy the ticket
	 * @param reservation is the reservation effetuated from the client
	 */
	public void buyTicketWithReservation(int idClient, Reservation reservation) {
		
	}
	
	/**
	 * @return the revenue of the week
	 */
	public double computeRevenueOfWeek() {
		return 0.0f;
	}
	
	/**
	 * @param film is the name of the film
	 * @return the revenue of this week for the show that projected a film
	 */
	public double computeRevenueOfFilm(String film) {
		return 0.0f;
	}
	
	List<Ticket> tickets;
	List<Discount> discounts;
	List<Discount> activeDiscounts;
}
