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
import UserInterface.State.ClientState;
import Utility.Time;

public class ProgramVisualizer extends ClientState {
	private static final long serialVersionUID = 2711349097130480705L;

	public class LoadOnPress implements ActionListener {
		public LoadOnPress(String formatter) {
			this.formatter = formatter;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ProgramVisualizer.this.selectedFormatter = formatter;
			try {
				ProgramVisualizer.this.window.update();
			} catch (CurrentStateNotAvailable e1) {
				e1.printStackTrace();
			}
		}	
		
		private String formatter;
	}
	
	public ProgramVisualizer(Window window, ModClient modClient) {
		super(window, modClient);

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
		programForWeek.addActionListener(new LoadOnPress("ForWeek"));
		
		JRadioButton programForHall = new JRadioButton("Per sala");
		northPanel.add(programForHall);
		group.add(programForHall);
		programForHall.addActionListener(new LoadOnPress("ForHall"));
		
		JRadioButton programForChrono = new JRadioButton("Cronologicamente");
		northPanel.add(programForChrono);
		group.add(programForChrono);
		programForChrono.addActionListener(new LoadOnPress("SortChrono"));
		
		JRadioButton programForNHall = new JRadioButton("Per numero sala");
		northPanel.add(programForNHall);
		group.add(programForNHall);
		programForNHall.addActionListener(new LoadOnPress("SortHall"));
		
		JRadioButton programForTitle = new JRadioButton("Per titolo");
		northPanel.add(programForTitle);
		group.add(programForTitle);
		programForTitle.addActionListener(new LoadOnPress("SortTitle"));
		
		selectedFormatter = "ForWeek";
	}
	
	private void printShows(String f) {
		Multiplex multiplex = window.getMultiplex();
		
		List<String> showsToPrint = multiplex.formatProgram(f);
		for(String s : showsToPrint) {
			area.setText(area.getText() + s + "\n");
		}
	}
	
	@Override
	public void load() {
		area.setText("");
		printShows(selectedFormatter);
	}

	@Override
	public void unLoad() {
	}
	
	private JTextArea area;
	private ButtonGroup group;
	private String selectedFormatter;
}
