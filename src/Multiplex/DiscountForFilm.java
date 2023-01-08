package Multiplex;

public class DiscountForFilm extends Discount {

	public DiscountForFilm(double percent, String film) {
		super(percent);
		this.film = film;
	}

	@Override
	public boolean policy(int idClient, Show show) {
		return show.getFilm().equals(film);
	}
	
	private String film;
}
