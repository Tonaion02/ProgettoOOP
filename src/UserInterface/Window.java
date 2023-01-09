package UserInterface;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Multiplex.Multiplex;

public class Window extends JFrame {
	private static final long serialVersionUID = -897675181702202733L;
	
	public Window(Multiplex multiplex) {
		//Common part
		this.multiplex = multiplex;
		
		//Common part
		
		currentState = null;
		
		
		//Set size of the Window
		this.setSize(300, 300);

		//Set on close action
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				Window.this.onClose();
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
		});
		//Set on close action
		
		this.setVisible(true);
	}
	
	public void update() throws Exception {
		if(currentState == null)
			throw new CurrentStateNotAvailable();
		
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	private void onClose() {
		multiplex.save();
	}
	
	public void loadState(State state) {
		if(currentState != null)
			currentState.unLoad();
		
		currentState = state;
		currentState.load();
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	public Multiplex getMultiplex() {
		return multiplex;
	}
	
	private State currentState;
	private Multiplex multiplex;
}
