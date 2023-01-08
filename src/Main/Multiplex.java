package Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that rappresent the concept of Multiplex
 */
public class Multiplex {
	
	public Multiplex() {
		films = new ArrayList<>();
		halls = new ArrayList<>();
		ticketOffice = new TicketOffice();
		reservationHandler = new ReservationHandler();
		weekProgram = new WeekProgram();
		
		
	}
	
	public Hall getHall(final String name) {
		return halls.stream().filter(h -> h.equals(name)).findAny().get();
	}
	
	public List<String> getFilms() {
		return films;
	}
	
	public TicketOffice getTickeOffice() {
		return ticketOffice;
	}
	
	public ReservationHandler getReservationHandler() {
		return reservationHandler;
	}
	
	public WeekProgram getWeekProgram() {
		return weekProgram;
	}
	
	private List<String> films;
	private List<Hall> halls;
	private TicketOffice ticketOffice;
	private ReservationHandler reservationHandler;
	private WeekProgram weekProgram;
	
	
	
	public static void main(String[] args) {
		
	}
}
