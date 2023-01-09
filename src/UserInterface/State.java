package UserInterface;

import java.awt.BorderLayout;

import javax.swing.*;

public abstract class State extends JPanel {
	private static final long serialVersionUID = 2753947004903779946L;
	
	public State(Window window) {
		this.setLayout(new BorderLayout());
		
		this.window = window;
	}
	
	public abstract void load();
	public abstract void unLoad();
	
	protected Window window;
}
