package UserInterface;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.swing.*;

import Multiplex.Archive;
import Multiplex.ModClient;
import Multiplex.ModHandler;
import Multiplex.Multiplex;
import Multiplex.SeatAlreadyTaken;
import Multiplex.SeatNotAvailable;
import Multiplex.Show;
import Multiplex.StateReservableSeat;

public class TestWindow extends JFrame{  
	private static final long serialVersionUID = 618549544185941581L;

	public static void main(String[] args) {  
		int idClient = 0;
		
		Multiplex multiplex = null;
		File file = new File("data/Multiplex.dat");
		Archive archive = new Archive(file);
		
		if(!file.exists() || true)
		{
			multiplex = new Multiplex();
			
			List<Show> shows = multiplex.getProgramFormatter("ForHall").filter(multiplex.getShows());
			Show show = shows.get(0);
			System.out.println(show);
			
			//show.setStateSeat(1, StateReservableSeat.Reserved);
			
			List<StateReservableSeat> seats = show.getReservableSeats();
			for(StateReservableSeat state : seats) {
				System.out.println(state);
			}
		}
		else
		{
			multiplex = archive.load();
		}
		
		
		
    	ModClient modClient = new ModClient(multiplex);
    	ModHandler modHandler = new ModHandler(multiplex);
		
		
    	
		List<Show> showForFilm1 = multiplex.getShows();
		for(Show s : showForFilm1) {
			System.out.println(s);
		}
		
		try {
			List<Show> shows = multiplex.getShows();
			Show show = shows.get(0);
			//multiplex.getTicketOffice().buyTicket(idClient, show, 1);
			modClient.buyTicket(show, 1);
			
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}
		
		try {
			List<Show> shows = multiplex.getShows();
			Show show = shows.get(0);
			multiplex.getReservationHandler().createReservation(idClient, show, 2);
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}
    	
    	Window window = new Window(multiplex, archive, modClient, modHandler);
    	
//////    	//TEST HALLVIEW
//		List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion((s) -> true);
//		HallView hv = new HallView(shows.get(0), window, multiplex.getTicketOffice()::buyTicket);
//		window.add(hv);
//////    	//TEST HALLVIEW
		
		//TEST LISTERSELECTABLE
//    	List<String> strings = Arrays.asList("antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio",
//    			"antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio",
//    			"antonio","antonio","antonio","filippo");
//		ListerSelectable<String> listerShow = new ListerSelectable<>(strings);
//		window.add(new JScrollPane(listerShow));
		//TEST LISTERSELECTABLE
		
		StateSelectMode stateSelectMode = new StateSelectMode(window, new ClientState(window), new HandlerState(window));
		window.loadState(stateSelectMode);
		
		try {
			window.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}  