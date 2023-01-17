package Multiplex;

public class ModClient {
	
	public ModClient(Multiplex multiplex)  {
		this.multiplex = multiplex;
	}
	
	public void buyTicket(Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getTicketOffice().generateTicket(currentClient, show, numberOfSeat);
	}
	
	public void buyTicketWithReservation(Reservation reservation) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getTicketOffice().generateTicket(reservation.getClient(), reservation.getShow(), reservation.getNumberOfSeat());
		multiplex.getReservationHandler().deleteReservation(reservation);
	}
	
	public void takeReservation(Show show, int numberOfSeat) throws SeatNotAvailable, SeatAlreadyTaken {
		multiplex.getReservationHandler().createReservation(currentClient, show, numberOfSeat);
	}
	
	public Client getClient() {
		return currentClient;
	}
	
	public void selectClient(Client client) {
		currentClient = client;
	}
	
	private Multiplex multiplex;
	private Client currentClient;
}
