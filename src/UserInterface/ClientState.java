package UserInterface;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class ClientState extends State {
	private static final long serialVersionUID = 3823221456257949538L;

	public ClientState(Window window) {
		super(window);
		
		LocalDateTime local = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		System.out.println(myFormatObj.format(local));
		System.out.println(myFormatObj.format(local.minusHours(12)));
		
		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar, BorderLayout.NORTH);
		
		menuBar.add(new ChangePanelButton("Visualizza lista spettacoli", this.window, new ProgramVisualizer(this.window)));
		menuBar.add(new ChangePanelButton("Effettua Prenotazione", this.window, null));
		menuBar.add(new ChangePanelButton("Cancella una prenotazione", this.window, null));
		menuBar.add(new ChangePanelButton("Acquista biglietto", this.window, null));
		
		//MAGARI VISUALIZZA PRENOTAZIONI E BIGLIETTI SOTTO
	}

	@Override
	public void load() {}

	@Override
	public void unLoad() {}
}
