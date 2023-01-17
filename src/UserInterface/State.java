package UserInterface;

import java.awt.BorderLayout;

import javax.swing.*;

import Multiplex.ModClient;
import Multiplex.ModHandler;

public abstract class State extends JPanel {
	private static final long serialVersionUID = 2753947004903779946L;
	
	public State(Window window) {
		this.setLayout(new BorderLayout());
		
		this.window = window;
	}
	
	public abstract void load();
	public abstract void unLoad();
	
	protected Window window;
	
	
	public static abstract class ClientState extends State {
		private static final long serialVersionUID = 2864597613495124499L;

		public ClientState(Window window, ModClient modClient) {
			super(window);

		}

		public abstract void load();
		public abstract void unLoad();
		
		protected ModClient modClient;
	}
	
	public static abstract class HandlerState extends State {
		private static final long serialVersionUID = 8427893993685345093L;

		public HandlerState(Window window, ModHandler modHandler) {
			super(window);
			
			this.modHandler = modHandler;
		}

		public abstract void load();
		public abstract void unLoad();
		
		protected ModHandler modHandler;
	}
}
