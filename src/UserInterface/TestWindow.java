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
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import Multiplex.Multiplex;
import Multiplex.SeatAlreadyTaken;
import Multiplex.SeatNotAvailable;
import Multiplex.Show;
import Multiplex.StateReservableSeat;



public class TestWindow extends JFrame{  
    public static void main(String[] args) {  
		int idClient = 0;
		
		Multiplex multiplex = null;
		File file = new File("data/Multiplex.dat");
		
		if(!file.exists())
		{
			multiplex = new Multiplex();
			
			List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion(s -> true);
			Show show = shows.get(0);
			System.out.println(show);
			
			show.setStateSeat(1, StateReservableSeat.Reserved);
			
			List<StateReservableSeat> seats = show.getReservableSeats();
			for(StateReservableSeat state : seats) {
				System.out.println(state);
			}
		}
		else
		{
			ObjectInputStream input;
			try {
				input = new ObjectInputStream(new FileInputStream(file));
				multiplex = (Multiplex) input.readObject();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		List<Show> showForFilm1 = multiplex.getWeekProgram().filterShowForCriterion(multiplex.getWeekProgramFilters().get(0));
		for(Show s : showForFilm1) {
			System.out.println(s);
		}
		
		try {
			List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion(s -> true);
			Show show = shows.get(0);
			multiplex.getTicketOffice().buyTicket(idClient, show, 1);
			
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}
		
		try {
			List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion(s -> true);
			Show show = shows.get(0);
			multiplex.getReservationHandler().createReservation(idClient, show, 1);
		} catch (SeatNotAvailable e) {
			e.printStackTrace();
		} catch (SeatAlreadyTaken e) {
			e.printStackTrace();
		}	
    	
    	
    	
    	Window window = new Window(multiplex);
    	
    	JPanel rightPanel = new JPanel();
    	JPanel leftPanel = new JPanel();
    	
//    	//TEST HALLVIEW
//		List<Show> shows = multiplex.getWeekProgram().filterShowForCriterion((s) -> true);
//		HallView hv = new HallView(shows.get(0), window, multiplex.getTicketOffice()::buyTicket);
//		window.add(hv);
//    	//TEST HALLVIEW
		
		//TEST LISTERSELECTABLE
    	List<String> strings = Arrays.asList("antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio",
    			"antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio","antonio",
    			"antonio","antonio","antonio","filippo");
		ListerSelectable<String> listerShow = new ListerSelectable<>(strings);
		window.add(new JScrollPane(listerShow));
		//TEST LISTERSELECTABLE
		
		try {
			window.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}  