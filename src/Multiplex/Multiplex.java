package Multiplex;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
		
		weekProgram = new WeekProgram();
		weekProgram.insertShowInProgram(new Show("Show1", films.get(0), 34.5f, this.getHall(0), LocalDateTime.now().plusDays(5)));
		weekProgram.insertShowInProgram(new Show("Show2", films.get(0), 34.5f, halls.get(1), LocalDateTime.now()));
		weekProgram.insertShowInProgram(new Show("Show3", films.get(2), 34.5f, halls.get(0), LocalDateTime.now()));
		
		weekProgramFilters = new ArrayList<>();
		weekProgramFilters.add(new ProgramFilter() {
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
	
	public Hall getHall(final int numberOfHall) {
		return halls.stream().filter(h -> h.getNumberOfHall() == numberOfHall).findAny().get();
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
	
	public List<ProgramFilter> getWeekProgramFilters() {
		return weekProgramFilters;
	}
	
	public void save() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("data/Multiplex.dat")));
			output.writeObject(this);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> films;
	private List<Hall> halls;
	private List<ProgramFilter> weekProgramFilters; 
	private TicketOffice ticketOffice;
	private ReservationHandler reservationHandler;
	private WeekProgram weekProgram;
}
