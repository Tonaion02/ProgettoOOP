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
import Multiplex.ProgramFilter;
import Multiplex.Show;

public class ProgramVisualizer extends State {
	private static final long serialVersionUID = 2711349097130480705L;

	public class LoadOnPress implements ActionListener {
		public LoadOnPress(ProgramFilter filter) {
			this.filter = filter;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ProgramVisualizer.this.selectedFilter = filter;
			try {
				ProgramVisualizer.this.window.update();
			} catch (CurrentStateNotAvailable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		
		private ProgramFilter filter;
	}
	
	public ProgramVisualizer(Window window) {
		super(window);

		area = new JTextArea();
		JScrollPane scroll = new JScrollPane(area);
		this.add(scroll);
		
		JPanel northPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);
		
		group = new ButtonGroup();
		
		JRadioButton programForWeek = new JRadioButton("Programma settimanale");
		northPanel.add(programForWeek);
		group.add(programForWeek);
		group.setSelected(programForWeek.getModel(), true);
		
		class ProgramFilterForWeek implements ProgramFilter {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Show> filter(List<Show> shows) {
				
				List<Show> l = new ArrayList<>();
				
				for(Show s : shows) {
					int diff = window.getCurrentDateTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY);
					System.out.println(diff);
					LocalDateTime startOfWeek = window.getCurrentDateTime().truncatedTo(ChronoUnit.DAYS).minusDays(diff);
					diff = window.getCurrentDateTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY);
					LocalDateTime endOfWeek = window.getCurrentDateTime().truncatedTo(ChronoUnit.DAYS).plusDays(-diff);
					
					if(s.getDate().compareTo(startOfWeek) >= 0 && s.getDate().compareTo(endOfWeek) <= 0)
						l.add(s);
				}
				
				return l;
			}

		}
		window.getMultiplex().getWeekProgramFilters().add(new ProgramFilterForWeek());
		selectedFilter = window.getMultiplex().getWeekProgramFilters().get(1);
		programForWeek.addActionListener(new LoadOnPress(window.getMultiplex().getWeekProgramFilters().get(1)));
		
		JRadioButton programForHall = new JRadioButton("Programma per sale");
		northPanel.add(programForHall);
		group.add(programForHall);
		programForHall.addActionListener(new LoadOnPress(window.getMultiplex().getWeekProgramFilters().get(0)));
	}
	
	private void printShows(ProgramFilter filter) {
		Multiplex multiplex = window.getMultiplex();
		
		List<Show> showsToPrint = filter.filter(multiplex.getWeekProgram().getShows());
		for(Show s : showsToPrint) {
			area.setText(area.getText() + s.getTitle() + "\n");
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
	private ProgramFilter selectedFilter;
}
