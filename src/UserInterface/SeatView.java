package UserInterface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SeatView extends Rectangle {
	private static final long serialVersionUID = 8990313099653977739L;
	
	public SeatView(int x, int y, int w, int h, int numberOfSeat) {
		super(x, y, w, h);
		
		this.numberOfSeat = numberOfSeat;
	}
	
	public int getNumberOfSeat() {
		return numberOfSeat;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fill(this);
		g.setColor(Color.black);
		g.drawString(Integer.toString(numberOfSeat), (int)(this.getX() + (this.getWidth() / 2)), (int)(this.getY() + (this.getHeight() / 2)));
	}
	
	public void setC(Color c) {
		this.c = c;
	}
	
	private int numberOfSeat;
	private Color c = Color.BLACK;
}
