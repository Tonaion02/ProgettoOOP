package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.*;

public class ListerSelectable<T> extends JPanel {
	private static final long serialVersionUID = 69243043626606673L;

	public class Selection extends JButton {
		private static final long serialVersionUID = -5524967663323719350L;
		public Selection(T selectableObject, Function<T, String> format) {
			super(format.apply(selectableObject));
			
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selected = selectableObject;
				}
			});
		}
	}
	
	public ListerSelectable(List<T> options, Function<T, String> format) {
		selected = null;
		selections = new ArrayList<>();
			
		setSetOfOptions(options, format);	
	}
	
	public void setSetOfOptions(List<T> options, Function<T, String> format) {
		selections.clear();
		this.removeAll();
		
		if(options == null)
			return;
		
		int size = options.size();
		if(size < 20)
			size = 20;
		this.setLayout(new GridLayout(size, 1));
		
		for(T t : options) {
			Selection selection = new Selection(t, format);
			selections.add(selection);
			this.add(selection);
		}
	}
	
	public void unSelect() {
		selected = null;
	}
	
	public T getSelected() throws NotSelectedItemException {
		if(selected == null)
			throw new NotSelectedItemException();
			
		return selected;
	}
	
	private List<Selection> selections;
	private T selected;
}
