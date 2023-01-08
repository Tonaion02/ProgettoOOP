package Multiplex;

public class SeatNotAvailable extends Exception {
	private static final long serialVersionUID = 4057665798745663298L;

	public SeatNotAvailable() {
		super("Posto non disponibile");
	}
}
