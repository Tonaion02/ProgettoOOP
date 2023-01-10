package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChangePanelButton extends JButton {
	private static final long serialVersionUID = 481233589260964755L;
	
	public ChangePanelButton(final String name, final Window window, final State stateToLoad) {
		super(name);
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.loadState(stateToLoad);
			}
		});
	}
}