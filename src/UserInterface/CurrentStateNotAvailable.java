package UserInterface;

public class CurrentStateNotAvailable extends Exception {
	private static final long serialVersionUID = -1285132409073067433L;

	public CurrentStateNotAvailable() {
		super("Current State Not Available");
	}
}
