package UserInterface;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import Multiplex.ModClient;
import UserInterface.State.ClientState;

public class ClientModState extends ClientState {
	private static final long serialVersionUID = 3823221456257949538L;

	public ClientModState(Window window, ModClient modClient) {
		super(window, modClient);
		
		LocalDateTime local = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		System.out.println(myFormatObj.format(local));
		System.out.println(myFormatObj.format(local.minusHours(12)));
		
		State programVisualizer = new ProgramVisualizer(this.window, modClient);
		State reservationState = new ReservationState(this.window, modClient);
		State newReservation = new NewReservation(this.window, modClient);
		
		JMenuBar menuBar = createClientMenuBar(programVisualizer, reservationState, newReservation, null);
		this.add(menuBar, BorderLayout.NORTH);
		programVisualizer.add(createClientMenuBar(programVisualizer, reservationState, newReservation, null), BorderLayout.NORTH);
		reservationState.add(createClientMenuBar(programVisualizer, reservationState, newReservation, null), BorderLayout.NORTH);
		newReservation.add(createClientMenuBar(programVisualizer, reservationState, newReservation, null), BorderLayout.NORTH);
		
		//MAGARI VISUALIZZA PRENOTAZIONI E BIGLIETTI SOTTO
	}

	@Override
	public void load() {}

	@Override
	public void unLoad() {}
	
	private JMenuBar createClientMenuBar(State programVisualizer, State reservationState, State newReservationState, State buyTicketState) {
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(new ChangePanelButton("Visualizza spettacoli", this.window, programVisualizer));
		menuBar.add(new ChangePanelButton("Prenotazioni", this.window, reservationState));
		menuBar.add(new ChangePanelButton("Nuova prenotazione", this.window, newReservationState));
		menuBar.add(new ChangePanelButton("Acquista biglietto", this.window, buyTicketState));
		
		return menuBar;
	}
}
