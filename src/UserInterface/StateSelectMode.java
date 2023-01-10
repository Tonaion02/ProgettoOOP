package UserInterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class StateSelectMode extends State {
	private static final long serialVersionUID = -329752440135874461L;

	public StateSelectMode(Window window, State clientState, State handlerState) {
		super(window);
		
		this.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		JLabel titleState = new JLabel("SELEZIONA UNA MODALITA");
		titleState.setFont(new Font(Font.SERIF,  Font.BOLD, 40));
		northPanel.add(titleState);
		this.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();     
		GridBagLayout layout = new GridBagLayout();
		centerPanel.setLayout(layout);
		this.add(centerPanel);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;		
		JPanel whiteSpace = new JPanel();
		centerPanel.add(whiteSpace, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		ChangePanelButton loadCliente = new ChangePanelButton("Cliente", this.window, clientState);
		centerPanel.add(loadCliente, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.4;
		whiteSpace = new JPanel();
		centerPanel.add(whiteSpace, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		ChangePanelButton loadGestore = new ChangePanelButton("Gestore", this.window, handlerState);
		centerPanel.add(loadGestore, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		whiteSpace = new JPanel();
		centerPanel.add(whiteSpace, gbc);
	}
	
	@Override
	public void load() {
	}

	@Override
	public void unLoad() {
	}
}
