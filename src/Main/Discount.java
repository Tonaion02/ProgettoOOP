package Main;

/**
 * The Discount class is an abstract class that permit to apply a discount to the cost of the show
 * and to compute the discounted price of the ticket
 */
public abstract class Discount {
	/**
	 * Constructs discount from the percent of discount
	 * @param percent is the quantity of discount
	 */
	public Discount(double percent) {
		this.percent = percent;
	}
	
	/**
	 * The method compute the effective price of the ticket if the politics of discount apply to it
	 * @param the show we want to apply the discount
	 */
	public abstract double compute(Show show);
	
	/**
	 * Method return the percent of discount
	 * @return the percent of discount
	 */
	public double getPercent() {
		return percent;
	}
	
	private double percent;
}
