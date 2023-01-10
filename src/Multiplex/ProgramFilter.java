package Multiplex;

import java.io.Serializable;
import java.util.List;

public interface ProgramFilter extends Serializable {
	List<Show> filter(List<Show> shows);
}
