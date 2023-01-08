package Multiplex;

public class SeatAlreadyTaken extends Exception {
	private static final long serialVersionUID = -5254226750333329661L;

	public SeatAlreadyTaken() {
		super("Seat already taken");
	}
}
