package Multiplex;

import java.io.Serializable;
import java.util.function.Predicate;

public interface ProgramFilter extends Predicate<Show>, Serializable{
}
