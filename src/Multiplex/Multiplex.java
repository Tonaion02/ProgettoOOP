package Multiplex;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Utility.Time;

public class Multiplex implements Serializable{
	private static final long serialVersionUID = 3938796109326536459L;

	public Multiplex() {
		
		films = Arrays.asList("Film1", "Film2", "Film3");
		
		halls = new ArrayList<>();
		halls.add(new Hall(0, 30, 5));
		halls.add(new Hall(1, 50, 7));
		
		shows = new ArrayList<>();
		shows.add(new Show("Show1", "Film1", 24.0f, this.getHall(0), Time.getCurrentTime().plusDays(5)));
		shows.add(new Show("Show2", "Film3", 10.0f, this.getHall(1), Time.getCurrentTime()));
		shows.add(new Show("Show3", "Film1", 15.0f, this.getHall(0), Time.getCurrentTime()));

		//PROGRAM FORMATTERS
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
		
//		Predicate<Show> notStarted = (s) -> { return Time.getCurrentTime().compareTo(s.getDate()) > 0; };
		//#TO CHANGE
		Predicate<Show> notStarted = (s) -> { return true; };
		
		class ProgramForChrono implements ProgramFormatter {
			private static final long serialVersionUID = 7474872505841523848L;

			@Override
			public List<Show> filter(List<Show> shows) {
				return shows.stream().filter(notStarted).sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate())).toList();
			}
		}
		programFormatters.put("SortChrono", new ProgramForChrono());
		
		class ProgramForNumberOfHall implements ProgramFormatter {
			private static final long serialVersionUID = 4839808637396740734L;

			@Override
			public List<Show> filter(List<Show> shows) {
				return shows.stream().filter(notStarted).sorted((s1, s2) -> s1.getHall().getNumberOfHall() - s2.getHall().getNumberOfHall()).toList();
			}
		}
		programFormatters.put("SortHall", new ProgramForNumberOfHall());
		
		class ProgramForTitle implements ProgramFormatter {
			private static final long serialVersionUID = 7871426889029387565L;

			@Override
			public List<Show> filter(List<Show> shows) {
				return shows.stream().filter(notStarted).sorted((s1, s2) -> s1.getTitle().compareTo(s2.getTitle())).toList();
			}
		}
		programFormatters.put("SortTitle", new ProgramForTitle());
		
		
		
		class ProgramFilterForWeek implements ProgramFormatter {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Show> filter(List<Show> shows) {
				
				List<Show> l = new ArrayList<>();
				
				for(Show s : shows) {
					int diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY);
					LocalDateTime startOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).minusDays(diff);
					diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY);
					LocalDateTime endOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).plusDays(-diff);
					
					if(s.getDate().compareTo(startOfWeek) >= 0 && s.getDate().compareTo(endOfWeek) <= 0)
						l.add(s);
				}
				
				return l;
			}

		}
		
		programFormatters.put("ForWeek", new ProgramFilterForWeek());
		//PROGRAM FORMATTERS
		
		ticketOffice = new TicketOffice();
		
		Discount discount = new DiscountForFilm(0.5f, "Film1");
		getTicketOffice().addDiscount("Discount1", discount);
		getTicketOffice().activeDiscount("Discount1");
		
		discount = new Discount(0.4f) {
			private static final long serialVersionUID = 6038731155203342629L;

			@Override
			public boolean policy(Client client, Show show) {
				return client.getCategory() == "Category1";
			}
		};
		getTicketOffice().addDiscount("Discount2", discount);
		getTicketOffice().activeDiscount("Discount2");
		
		discount = new Discount(0.2f) {
			private static final long serialVersionUID = -3252997687199444059L;

			@Override
			public boolean policy(Client client, Show show) {
				return show.getDate().getDayOfWeek() == DayOfWeek.FRIDAY;
			}
		};
		getTicketOffice().addDiscount("Discount3", discount);
		getTicketOffice().activeDiscount("Discount3");
		
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
	
	public void insertIntoProgram(String title, String film, double price, int nHall, LocalDateTime date) throws Exception {
		Hall h = getHall(nHall);
		if(h == null)
			throw new Exception("Sala non trovata");
		Show s = new Show(title, film, price, h, date);
		shows.add(s);
	}
	
	//TO IMPROVE
//	public List<Show> getShows(String nameProgramFormatter) {
//		ProgramFormatter p = programFormatters.get(nameProgramFormatter);
//		return p.filter(shows)
//	}
	
//	public Map<String, ProgramFormatter> getProgramFormatters() {
//		return programFormatters;
//	}
//	
//	public ProgramFormatter getProgramFormatter(String identifier) {
//		return programFormatters.get(identifier);
//	}
	
	public List<String> formatProgram(String name) {
		ProgramFormatter formatter = programFormatters.get(name);
		List<Show> showsToFormat = formatter.filter(shows);
		List<String> showsFormatted = formatter.format(showsToFormat);
		return showsFormatted;
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
