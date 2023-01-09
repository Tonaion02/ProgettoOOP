package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		halls.add(new Hall("Sala1", 30, 5));
		halls.add(new Hall("Sala2", 50, 7));
		
		weekProgram = new WeekProgram();
		weekProgram.insertShowInProgram(new Show(films.get(0), 34.5f, this.getHall("Sala2"), DayOfWeek.Monday));
		weekProgram.insertShowInProgram(new Show(films.get(0), 34.5f, halls.get(0), DayOfWeek.Tuesday));
		weekProgram.insertShowInProgram(new Show(films.get(2), 34.5f, halls.get(1), DayOfWeek.Thursday));
		
		weekProgramFilters = new ArrayList<>();
		weekProgramFilters.add((s) -> s.getFilm().equals("Film1"));
		
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
	
	//TESTING
	public static void main(String[] args) {

	}
}
