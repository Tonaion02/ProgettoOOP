package UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.*;

import Multiplex.*;
import Multiplex.ProgramFormatter;
import Multiplex.Show;
import Utility.Time;

public class ProgramVisualizer extends State {
	private static final long serialVersionUID = 2711349097130480705L;

	public class LoadOnPress implements ActionListener {
		public LoadOnPress(ProgramFormatter filter) {
			this.filter = filter;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ProgramVisualizer.this.selectedFilter = filter;
			try {
				ProgramVisualizer.this.window.update();
			} catch (CurrentStateNotAvailable e1) {
				e1.printStackTrace();
			}
		}	
		
		private ProgramFormatter filter;
	}
	
	public ProgramVisualizer(Window window) {
		super(window);

		JPanel principlePanel = new JPanel();
		principlePanel.setLayout(new BorderLayout());;
		this.add(principlePanel);
		
		area = new JTextArea();
		JScrollPane scroll = new JScrollPane(area);
		principlePanel.add(scroll);
		
		JPanel northPanel = new JPanel();
		principlePanel.add(northPanel, BorderLayout.NORTH);
		
		group = new ButtonGroup();
		
		JRadioButton programForWeek = new JRadioButton("Programma settimanale");
		northPanel.add(programForWeek);
		group.add(programForWeek);
		group.setSelected(programForWeek.getModel(), true);
		
		class ProgramFilterForWeek implements ProgramFormatter {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Show> filter(List<Show> shows) {
				
				List<Show> l = new ArrayList<>();
				
				for(Show s : shows) {
					int diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY);
					System.out.println(diff);
					LocalDateTime startOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).minusDays(diff);
					diff = Time.getCurrentTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY);
					LocalDateTime endOfWeek = Time.getCurrentTime().truncatedTo(ChronoUnit.DAYS).plusDays(-diff);
					
					if(s.getDate().compareTo(startOfWeek) >= 0 && s.getDate().compareTo(endOfWeek) <= 0)
						l.add(s);
				}
				
				return l;
			}

		}
		window.getMultiplex().getProgramFormatters().put("ForWeek", new ProgramFilterForWeek());
		selectedFilter = window.getMultiplex().getProgramFormatter("ForWeek");
		programForWeek.addActionListener(new LoadOnPress(window.getMultiplex().getProgramFormatter("ForWeek")));
		
		JRadioButton programForHall = new JRadioButton("Per sala");
		northPanel.add(programForHall);
		group.add(programForHall);
		programForHall.addActionListener(new LoadOnPress(window.getMultiplex().getProgramFormatter("ForHall")));
		
		JRadioButton programForChrono = new JRadioButton("Cronologicamente");
		northPanel.add(programForChrono);
		group.add(programForChrono);
		programForChrono.addActionListener(new LoadOnPress(window.getMultiplex().getProgramFormatter("SortChrono")));
		
		JRadioButton programForNHall = new JRadioButton("Per numero sala");
		northPanel.add(programForNHall);
		group.add(programForNHall);
		programForNHall.addActionListener(new LoadOnPress(window.getMultiplex().getProgramFormatter("SortHall")));
		
		JRadioButton programForTitle = new JRadioButton("Per titolo");
		northPanel.add(programForTitle);
		group.add(programForTitle);
		programForTitle.addActionListener(new LoadOnPress(window.getMultiplex().getProgramFormatter("SortTitle")));
	}
	
	private void printShows(ProgramFormatter filter) {
		Multiplex multiplex = window.getMultiplex();
		
		List<Show> showsToPrint = filter.filter(multiplex.getShows());
		List<String> output = filter.format(showsToPrint);
		for(var s : output) {
			area.setText(area.getText() + s + "\n");
		}
	}
	
	@Override
	public void load() {
		area.setText("");
		printShows(selectedFilter);
	}

	@Override
	public void unLoad() {
	}
	
	private JTextArea area;
	private ButtonGroup group;
	private ProgramFormatter selectedFilter;
}
