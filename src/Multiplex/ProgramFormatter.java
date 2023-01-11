package Multiplex;

import java.io.Serializable;
import java.util.List;

public interface ProgramFormatter extends Serializable {
	List<Show> filter(List<Show> shows);
	public default List<String> format(List<Show> shows) {
		return shows.stream().map((s) -> s.getTitle()).toList();
	}
}
