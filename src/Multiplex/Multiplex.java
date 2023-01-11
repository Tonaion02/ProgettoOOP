package Multiplex;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import Utility.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Class that rappresent the concept of Multiplex
 */
public class Multiplex implements Serializable{
	private static final long serialVersionUID = 3938796109326536459L;

	public Multiplex() {
		films = Arrays.asList("Film1", "Film2", "Film3");
		
		halls = new ArrayList<>();
		halls.add(new Hall(0, 30, 5));
		halls.add(new Hall(1, 50, 7));
		
		shows = new ArrayList<>();
		shows.add(new Show("Show1", "Film1", 24.0f, this.getHall(0), Time.getCurrentTime().plusDays(5)));
		shows.add(new Show("Show2", "Film3", 24.0f, this.getHall(1), Time.getCurrentTime()));
		shows.add(new Show("Show3", "Film1", 24.0f, this.getHall(0), Time.getCurrentTime()));
		
//		programFormatters = new ArrayList<>();
//		programFormatters.add(new ProgramFormatter() {
//			private static final long serialVersionUID = 2706478179287181612L;
//
//			@Override
//			public List<Show> filter(List<Show> shows) {
//				Function<? super Show, ? extends Integer> f = (s) -> {return s.getHall().getNumberOfHall();};
//				Map<Integer, List<Show>> grouping = shows.stream().collect(Collectors.groupingBy(f));
//				List<Show> l = grouping.entrySet().stream().map((e) -> e.getValue()).flatMap((list) -> list.stream()).toList();
//				return l;
//			}
//		});

		programFormatters = new HashMap<>();
		programFormatters.put("ForHall", new ProgramFormatter() {
			private static final long serialVersionUID = 2706478179287181612L;

			@Override
			public List<Show> filter(List<Show> shows) {
				Function<? super Show, ? extends Integer> f = (s) -> {return s.getHall().getNumberOfHall();};
				Map<Integer, List<Show>> grouping = shows.stream().collect(Collectors.groupingBy(f));
				List<Show> l = grouping.entrySet().stream().map((e) -> e.getValue()).flatMap((list) -> list.stream()).toList();
				return l;
			}
		});
		
		ticketOffice = new TicketOffice();
		
		Discount discount = new DiscountForFilm(0.5f, "Film1");
		discount.activate();
		getTicketOffice().addDiscount(discount);
		
		reservationHandler = new ReservationHandler();
	}
	
	public void setStateSeat(final int numberOfHall, int numberOfSeat, StateSeat state) {
		Hall hall = getHall(numberOfHall);
		hall.setStateSeat(numberOfSeat, state);
	}
	
	public StateSeat getStateSeat(final int numberOfHall, int numberOfSeat) {
		Hall hall = getHall(numberOfHall);
		return hall.getStateSeat(numberOfSeat);
	}
	
	public Hall getHall(final int numberOfHall) {
		return halls.stream().filter(h -> h.getNumberOfHall() == numberOfHall).findAny().get();
	}
	
	public List<String> getFilms() {
		return films;
	}

	public List<Show> getShows() {
		return shows;
	}
	
	public void insertIntoProgram(Show s) {
		shows.add(s);
	}
	
	public Map<String, ProgramFormatter> getProgramFormatters() {
		return programFormatters;
	}
	
	public ProgramFormatter getProgramFormatter(String identifier) {
		return programFormatters.get(identifier);
	}
	
	public TicketOffice getTicketOffice() {
		return ticketOffice;
	}
	
	public ReservationHandler getReservationHandler() {
		return reservationHandler;
	}
	
	private List<Show> shows;
	private List<String> films;
	private List<Hall> halls;
	private Map<String, ProgramFormatter> programFormatters;
	private TicketOffice ticketOffice;
	private ReservationHandler reservationHandler;
}
