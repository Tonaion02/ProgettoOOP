package Multiplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JScrollPane;

/**
 * Class that rappresent the concept of Multiplex
 */
public class Multiplex {
	
	public Multiplex() {
		films = Arrays.asList("Film1", "Film2", "Film3");
		
		halls = new ArrayList<>();
		halls.add(new Hall("Sala1", 30));
		halls.add(new Hall("Sala2", 50));
		
		weekProgram = new WeekProgram();
		weekProgram.insertShowInProgram(new Show(films.get(0), 34.5f, this.getHall("Sala1"), DayOfWeek.Monday));
		weekProgram.insertShowInProgram(new Show(films.get(0), 34.5f, halls.get(0), DayOfWeek.Tuesday));
		weekProgram.insertShowInProgram(new Show(films.get(2), 34.5f, halls.get(1), DayOfWeek.Thursday));
		
		weekProgramFilters = new ArrayList<>();
		weekProgramFilters.add((s -> s.getHall().getName().equals("Sala1")));
		
		ticketOffice = new TicketOffice();
		
		Discount discount = new DiscountForFilm(0.5f, "Film1");
		discount.activate();
		getTicketOffice().addDiscount(discount);
		
		reservationHandler = new ReservationHandler();
	}
	
	public Hall getHall(final String name) {
		return halls.stream().filter(h -> h.getName().equals(name)).findAny().get();
	}
	
	public List<String> getFilms() {
		return films;
	}
	
	public TicketOffice getTicketOffice() {
		return ticketOffice;
	}
	
	public ReservationHandler getReservationHandler() {
		return reservationHandler;
	}
	
	public WeekProgram getWeekProgram() {
		return weekProgram;
	}
	
	public List<Predicate<Show>> getWeekProgramFilters() {
		return weekProgramFilters;
	}
	
	private List<String> films;
	private List<Hall> halls;
	private List<Predicate<Show>> weekProgramFilters; 
	private TicketOffice ticketOffice;
	private ReservationHandler reservationHandler;
	private WeekProgram weekProgram;
	
	//TESTING
	public static void main(String[] args) {
		
		int idClient = 0;
		
		Multiplex multiplex = new Multiplex();
		
		List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion(s -> true);
		Show show = shows.get(0);
		System.out.println(show);
		
		show.setStateSeat(1, StateReservableSeat.Reserved);
		
		List<StateReservableSeat> seats = show.getReservableSeats();
		for(StateReservableSeat state : seats) {
			System.out.println(state);
		}
		
		List<Show> showForFilm1 = multiplex.getWeekProgram().filterShowForCriterion(multiplex.getWeekProgramFilters().get(0));
		for(Show s : showForFilm1) {
			System.out.println(s);
		}
		
		try {
			multiplex.getTicketOffice().buyTicket(idClient, show, 1);
			
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}
		
		try {
			multiplex.getReservationHandler().createReservation(idClient, show, 1);
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}
	}
}
