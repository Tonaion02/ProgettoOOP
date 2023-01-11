package UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Multiplex.Multiplex;
import Multiplex.Reservation;

public class ReservationState extends State {
	private static final long serialVersionUID = 508171322486247738L;

	public ReservationState(Window window) {
		super(window);
		
		//#TEMPORARY
		int idClient = 0;
		//#TEMPORARY
		
		Multiplex multiplex = window.getMultiplex();
		reservations = new ListerSelectable<>(null);
		this.add(new JScrollPane(reservations));
		
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		
		//Button to buy reservation
		JButton buy = new JButton("BUY");
		southPanel.add(buy);
		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					multiplex.getTicketOffice().buyTicketWithReservation(reservations.getSelected());
					reservations.unSelect();
				} catch (NotSelectedItemException e1) {
					JOptionPane.showMessageDialog(ReservationState.this.window, "Non hai selezionato nessuna prenotazione");
				}
			}
			
		});
		//Button to buy reservation
		
		//Button to delete reservation
		JButton delete = new JButton("DELETE");
		southPanel.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					multiplex.getReservationHandler().deleteReservation(reservations.getSelected());
					reservations.unSelect();
					
					ReservationState.this.window.update();
				} catch (NotSelectedItemException e1) {
					JOptionPane.showMessageDialog(ReservationState.this.window, "Non hai selezionato nessuna prenotazione");
				} catch (CurrentStateNotAvailable e1) {
					e1.printStackTrace();
				}
			}
			
		});
		//Button to delete reservation
	}

	@Override
	public void load() {
		Multiplex multiplex = window.getMultiplex();
		multiplex.getReservationHandler().getReservations().stream().forEach(System.out::println);
		reservations.setSetOfOptions(multiplex.getReservationHandler().getReservations());
	}

	@Override
	public void unLoad() {
		reservations.unSelect();
	}
	
	private ListerSelectable<Reservation> reservations;
}
