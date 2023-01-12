package Multiplex;

import java.io.Serializable;

/**
 * The Discount class is an abstract class that permit to apply a discount to the cost of the show
 * and to compute the discounted price of the ticket
 */
public abstract class Discount implements Serializable {
	private static final long serialVersionUID = 2818164087122726952L;
	/**
	 * Constructs discount from the percent of discount
	 * @param percent is the quantity of discount
	 */
	public Discount(double percent) {
		this.percent = percent;
		this.active = true;
	}
	
	/**
	 * 
	 * @param idClient is the client that we want to test
	 * @param show is the show that we want to test
	 * @return the result of the test if policy is applied
	 */
	public abstract boolean policy(int idClient, Show show);
	
	/**
	 * The method compute price of the ticket with the discount applied
	 * @param the show we want to apply the discount
	 */
	public double compute(Show show) {
		return show.getFullPriceTicket() * percent;
	}
	
	/**
	 * Method return the percent of discount
	 * @return the percent of discount
	 */
	public double getPercent() {
		return percent;
	}
	
	/**
	 * Set to active the discount
	 */
	protected void activate() {
		this.active = true;
	}
	
	/**
	 * Set to inactive the discount
	 */
	protected void deActivate() {
		this.active = false;
	}
	
	/**
	 * 
	 * @return the current state of a discount
	 */
	public boolean isActive() {
		return active;
	}
	
	private double percent;
	private boolean active;
}
