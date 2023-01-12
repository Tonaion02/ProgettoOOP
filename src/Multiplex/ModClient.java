package Multiplex;

public class ModClient {
	
	public ModClient(Multiplex multiplex)  {
		this.multiplex = multiplex;
		this.currentIdClient = 0;
	}
	
	public void buyTicket(Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getTicketOffice().generateTicket(currentIdClient, show, numberOfSeat);
	}
	
	public void buyTicketWithReservation(Reservation reservation) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getTicketOffice().generateTicket(reservation.getIdClient(), reservation.getShow(), reservation.getNumberOfSeat());
		multiplex.getReservationHandler().deleteReservation(reservation);
	}
	
	public void takeReservation(Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getReservationHandler().createReservation(currentIdClient, show, numberOfSeat);
	}
	
	public int getIdClient() {
		return currentIdClient;
	}
	
	private Multiplex multiplex;
	private int currentIdClient;
}
