package UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Multiplex.Multiplex;
import Multiplex.SeatAlreadyTaken;
import Multiplex.SeatNotAvailable;
import Multiplex.Show;

public class NewReservation extends State{
	private static final long serialVersionUID = -7769269642035089448L;

	public NewReservation(Window window) {
		super(window);
		
		int idClient = 0;
		
		Multiplex multiplex = window.getMultiplex();
		
		lister = new ListerSelectable<>(multiplex.getShows(), (s) -> s.getTitle());
		this.add(lister);
		
		nuovaPrenotazione = new JButton("Prenota un posto");
		this.add(nuovaPrenotazione, BorderLayout.SOUTH);
		
		nuovaPrenotazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewReservation.this.remove(lister);
				NewReservation.this.remove(nuovaPrenotazione);
				
				Show selectedShow = null;
				try {
					selectedShow = lister.getSelected();
				} catch (NotSelectedItemException e1) {
					JOptionPane.showMessageDialog(window, "Nessuno show selezionato!");
				}
				
				Show showToPass = selectedShow;
				HallView2 hallView = new HallView2(selectedShow, window);
				NewReservation.this.add(hallView);
				JButton prenota = new JButton("Prenota");
				NewReservation.this.add( prenota, BorderLayout.SOUTH);
				prenota.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {						
						try {
							multiplex.getReservationHandler().createReservation(idClient, showToPass, hallView.getSelectedSeat());
						} catch (SeatNotAvailable e1) {
							JOptionPane.showMessageDialog(window, "Posto non disponibile");
							return;
							//e1.printStackTrace();
						} catch (SeatAlreadyTaken e1) {
							JOptionPane.showMessageDialog(window, "Posto già prenotato");
							return;
							//e1.printStackTrace();
						}
						
						NewReservation.this.remove(hallView);
						NewReservation.this.remove(prenota);
						NewReservation.this.add(lister);
						NewReservation.this.add(nuovaPrenotazione, BorderLayout.SOUTH);
						
						try {
							window.update();
						} catch (CurrentStateNotAvailable e1) {
							e1.printStackTrace();
						}
					}
					
				});
				try {
					window.update();
				} catch (CurrentStateNotAvailable e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void load() {
		lister.unSelect();
	}

	@Override
	public void unLoad() {
		
	}
	
	private ListerSelectable<Show> lister;
	private JButton nuovaPrenotazione;
}
