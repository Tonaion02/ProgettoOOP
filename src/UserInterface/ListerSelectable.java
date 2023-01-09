package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ListerSelectable<T> extends JPanel {
	private static final long serialVersionUID = 69243043626606673L;

	public class Selection extends JButton {
		private static final long serialVersionUID = -5524967663323719350L;
		public Selection(T selectableObject) {
			super(selectableObject.toString());
			
			this.selectableObject = selectableObject;
			
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selected = selectableObject;
				}
			});
		}
		
		@SuppressWarnings("unused")
		private T selectableObject;
	}
	
	public ListerSelectable(List<T> selects) {
		this.setLayout(new GridLayout(selects.size(), 1));
		
		selected = null;
		
		selections = new ArrayList<>();
		for(T t : selects) {
			Selection selection = new Selection(t);
			selections.add(selection);
			this.add(selection);
		}
	}
	
	public T getSelected() throws Exception {
		if(selected == null)
			throw new NotSelectedItemException();
			
		return selected;
	}
	
	private List<Selection> selections;
	private T selected;
}
