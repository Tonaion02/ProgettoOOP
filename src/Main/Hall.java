package Main;

import java.util.List;

/*
 * The class represent the concept of Hall of the Multiplex
 */
public class Hall {
	
	/**
	 * Constructs the hall from the name and the number of seats of the hall
	 * @param numberOfSeats the number of seats of the hall
	 * @param name the name of the hall
	 */
	public Hall(String name, int numberOfSeats) {
		this.name = name;
		
		for(int i = 0;i < numberOfSeats;i++) {
			seats.add(StateSeat.Available);
		}
	}
	
	/**
	 * 
	 * @param numberOfSeat the number of seat that we want to change the state
	 * @param state the state that we want to set for the seat
	 */
	public void setStateSeat(int numberOfSeat, StateSeat state) {
		seats.set(numberOfSeat, state);
	}
	
	/**
	 * 
	 * @param numberOfSeat the number of seat that we want to see the state
	 * @return the state of the seat
	 */
	public StateSeat getStateSeat(int numberOfSeat) {
		return seats.get(numberOfSeat);
	}
	
	/**
	 * 
	 * @return the number of the seat that is present in the hall
	 */
	public int getNumberOfSeats() {
		return seats.size();
	}
	
	/**
	 * 
	 * @return the name of the hall
	 */
	public String getName() {
		return name;
	}
	
	private List<StateSeat> seats; 
	private String name;
}
