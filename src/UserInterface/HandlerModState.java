package UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Multiplex.ModClient;
import Multiplex.ModHandler;
import Multiplex.Multiplex;
import Multiplex.Show;
import UserInterface.State.HandlerState;

public class HandlerModState extends HandlerState {
	private static final long serialVersionUID = -4660237638570072943L;
	
	public class ReportShowsState extends State {
		private static final long serialVersionUID = 7527646365249332670L;

		public ReportShowsState(Window window) {
			super(window);
			
			area = new JTextArea();
			area.setEditable(false);
			this.add(new JScrollPane(area));
		}

		@Override
		public void load() {
			area.setText(modHandler.reportShow());
		}

		@Override
		public void unLoad() {}
		
		private JTextArea area;
	}
	
	//TO WORK ON
	public class SetPriceState extends State {
		private static final long serialVersionUID = -6748129789809031989L;

		public SetPriceState(Window window) {
			super(window);
			
			lister = new ListerSelectable<Show>(null, null);
			this.add(new JScrollPane(lister));
			
			JPanel southPanel = new JPanel();
			this.add(southPanel, BorderLayout.SOUTH);
			
			JLabel newPrice = new JLabel("Nuovo prezzo: ");
			southPanel.add(newPrice);
			price = new JTextField(5);
			southPanel.add(price);
			
			JButton submit = new JButton("Cambia prezzo");
			southPanel.add(submit);
			submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Show show = lister.getSelected();
						
						Double p = Double.parseDouble(price.getText());
						modHandler.setPriceShow(show, p);
						
						price.setText("");
						lister.unSelect();
						SetPriceState.this.window.update();
					} catch (NotSelectedItemException e1) {
						JOptionPane.showMessageDialog(SetPriceState.this.window, "Nessuno spettacolo selezionato");
						e1.printStackTrace();
					} catch (CurrentStateNotAvailable e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}

		@Override
		public void load() {
			price.setText("");
			lister.setSetOfOptions(window.getMultiplex().getShows(), (s) -> s.getTitle() + "price: " + s.getFullPriceTicket());
		}

		@Override
		public void unLoad() {}
		
		private JTextField price;
		private ListerSelectable<Show> lister;
	}
	
	public class VisualizeRevenue extends State {
		private static final long serialVersionUID = -2075474396844547279L;

		public VisualizeRevenue(Window window) {
			super(window);
			
			area = new JTextArea();
			JScrollPane scroll = new JScrollPane(area);
			this.add(scroll);
		}

		@Override
		public void load() {
			area.setText(modHandler.visualizeRevenue());
		}

		@Override
		public void unLoad() {}
		
		private JTextArea area;
	}
	
	public class InsertShowState extends State {
		private static final long serialVersionUID = -4434240871673056980L;

		public InsertShowState(Window window) {
			super(window);
			
			Multiplex multiplex = window.getMultiplex();
			
			title = new JTextField();
			film = new JTextField();
			price = new JTextField();
			nHall = new JTextField();
			
			JButton submit = new JButton("Inserisci");
			submit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						multiplex.insertIntoProgram(title.getText(), film.getText(), Double.parseDouble(price.getText()),
								Integer.parseInt(nHall.getText()), null);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(window, "Errore nell'inserimento");
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(window, "Numero di sala non valido");
						e1.printStackTrace();
					}
				}
				
			});
		}

		@Override
		public void load() {
			title.setText("");
			film.setText("");
			price.setText("");
			nHall.setText("");
		}
		@Override
		public void unLoad() {}
		
		private JTextField title;
		private JTextField film;
		private JTextField price;
		private JTextField nHall;
	}

	public HandlerModState(Window window, ModHandler modHandler) {
		super(window, modHandler);
		
		State insert = new InsertShowState(window);
		State revenue = new VisualizeRevenue(window);
		State setPrice = new SetPriceState(window);
		State reportShowState = new ReportShowsState(window);
		
		JMenuBar menuBar = createMenuBar(insert, revenue, setPrice, reportShowState);
		this.add(menuBar, BorderLayout.NORTH);
		
		insert.add(createMenuBar(insert, revenue, setPrice, reportShowState), BorderLayout.NORTH);
		revenue.add(createMenuBar(insert, revenue, setPrice, reportShowState), BorderLayout.NORTH);
		setPrice.add(createMenuBar(insert, revenue, setPrice, reportShowState), BorderLayout.NORTH);
		reportShowState.add(createMenuBar(insert, revenue, setPrice, reportShowState), BorderLayout.NORTH);
	}

	@Override
	public void load() {
		
	}

	@Override
	public void unLoad() {
		
	}
	
	private JMenuBar createMenuBar(State insert, State revenue, State setPrice, State reportShows) {
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(new ChangePanelButton("Inserisci un nuovo show", window, insert));
		menuBar.add(new ChangePanelButton("Fissa prezzo spettacolo", window, setPrice));
		menuBar.add(new ChangePanelButton("Sconti", window, null));
		menuBar.add(new ChangePanelButton("Spettacoli", window, reportShows));
		menuBar.add(new ChangePanelButton("Incasso", window, revenue));
		menuBar.add(new ChangePanelButton("Posti", window, null));
		
		return menuBar;
	}
	
	private ModHandler modHandler;
}
