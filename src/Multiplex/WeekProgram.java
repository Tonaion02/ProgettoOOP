package Multiplex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the concept of program of the week
 */
public class WeekProgram implements Serializable {
	private static final long serialVersionUID = -3767903385695428281L;

	/**
	 * Constuct a empty week program
	 */
	public WeekProgram() {
		shows = new ArrayList<>();
	}
	
//	/**
//	 * 
//	 * @param criterionOfFilter criterion to filter the shows
//	 * @return list of shows filtered by a criterion
//	 */
//	public List<Show> filterShowForCriterion(ProgramFilter criterionOfFilter) {
//		return shows.stream().filter(criterionOfFilter).toList();
//	}
	
	/**
	 * Method to insert a show in the week program
	 * @param show the show we want to insert in the program
	 */
	public void insertShowInProgram(Show show) {
		shows.add(show);
	}
	
	public List<Show> getShows() {
		return shows;
	}
	
	private List<Show> shows;
}
