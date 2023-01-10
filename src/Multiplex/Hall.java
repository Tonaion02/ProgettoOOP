package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * The class represent the concept of Hall of the Multiplex
 */
public class Hall implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs the hall from the name and the number of seats of the hall
	 * @param numberOfSeats the number of seats of the hall
	 * @param name the name of the hall
	 */
	public Hall(int numberOfHall, int numberOfSeats, int numberOfSeatsH) {
		this.numberOfHall = numberOfHall;
		this.numberOfSeatsH = numberOfSeatsH;
		System.out.println(this.numberOfSeatsH);
		
		this.seats = new ArrayList<>();
		for(int i = 0;i < numberOfSeats;i++) {
			seats.add(StateSeat.Available);
		}
	}
	
	/**
	 * The method set the state of the seat(Available/Unavailable) identified by the number of seat
	 * @param numberOfSeat the number of seat that we want to change the state
	 * @param state the state that we want to set for the seat
	 */
	public void setStateSeat(int numberOfSeat, StateSeat state) {
		seats.set(numberOfSeat, state);
	}
	
	/**
	 * The method return the state of the seat(Available/Unavailable) identified by the number of seat
	 * @param numberOfSeat the number of seat that we want to see the state
	 * @return the state of the seat(Available/Unavailable)
	 */
	public StateSeat getStateSeat(int numberOfSeat) {
		return seats.get(numberOfSeat);
	}
	
	/**
	 * The method return the number of seats
	 * @return the number of the seat that is present in the hall
	 */
	public int getNumberOfSeats() {
		return seats.size();
	}
	
	/**
	 * The method return the number of the hall
	 * @return the number of the hall
	 */
	public int getNumberOfHall() {
		return numberOfHall;
	}
	
	/**
	 * 
	 * @return number of seats for line of the hall
	 */
	public int getNumberOfSeatsH() {
		return numberOfSeatsH;
	}
	
	private List<StateSeat> seats; 
	private int numberOfHall;
	private int numberOfSeatsH; 
}
