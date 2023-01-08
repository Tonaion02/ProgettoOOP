package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The class represents the concept of program of the week
 */
public class WeekProgram {
	
	/**
	 * Constuct a empty week program
	 */
	public WeekProgram() {
		shows = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param criterionOfFilter criterion to filter the shows
	 * @return list of shows filtered by a criterion
	 */
	public List<Show> filterShowForCriterion(Predicate<Show> criterionOfFilter) {
		return shows.stream().filter(criterionOfFilter).toList();
	}
	
	/**
	 * Method to insert a show in the week program
	 * @param show the show we want to insert in the program
	 */
	public void insertShowInProgram(Show show) {
		shows.add(show);
	}
	
	private List<Show> shows;
}
