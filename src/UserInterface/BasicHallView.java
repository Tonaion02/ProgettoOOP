package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Multiplex.Hall;
import Multiplex.Show;
import Multiplex.StateReservableSeat;
import Multiplex.StateSeat;

public abstract class BasicHallView extends JComponent {
	private static final long serialVersionUID = 4840182416182552680L;
	

	public static class HallViewClient extends BasicHallView {
		private static final long serialVersionUID = -2518365984078708971L;

		public HallViewClient(Show show, Window window) {
			super(window, show.getHall().getNumberOfHall());
			
			int x = 0, y = 0;
			int w = 40, h = 40;
			int padX = 5, padY = 5;
			
			List<StateReservableSeat> rSeats = show.getReservableSeats();
			
			for(int i=0;i<rSeats.size();i++) {
				
				//Compute position of the seat
				y = (i / show.getHall().getNumberOfSeatsH()) * (h + padY);
				
				if(i % show.getHall().getNumberOfSeatsH() == 0)
					x = 0;
				
				SeatView s = new SeatView(x, y, w, h, i);
				x += w + padX;
				//Compute position of the seat
				
				seats.add(s);
			}
			
			for(SeatView s : seats) {
				
				int i = s.getNumberOfSeat();
				
				//Set the correct color from the state of the seat
				StateSeat state = show.getHall().getStateSeat(i);
				if(state.equals(StateSeat.Unavailable))
					s.setC(Color.gray);
				else
				{
					StateReservableSeat stat = rSeats.get(i);
					if(stat.equals(StateReservableSeat.Free))
						s.setC(Color.green);
					else if(stat.equals(StateReservableSeat.Sold))
						s.setC(Color.red);
					else if(stat.equals(StateReservableSeat.Reserved))
						s.setC(Color.yellow);
				}
				//Set the correct color from the state of the seat
			}
		}
	}
	
	public static class HallViewHandler extends BasicHallView {
		private static final long serialVersionUID = 3154300639948559366L;

		public HallViewHandler(Hall hall, Window window) {
			super(window, hall.getNumberOfSeatsH());
			
			int x = 0, y = 0;
			int w = 40, h = 40;
			int padX = 5, padY = 5;
			
			int nSeats = hall.getNumberOfSeats();
			
			for(int i=0;i<nSeats;i++) {
			
				//Compute position of the seat
				y = (i / numberOfSeatH) * (h + padY);
			
				if(i % numberOfSeatH == 0)
					x = 0;
			
				SeatView s = new SeatView(x, y, w, h, i);
				x += w + padX;
				//Compute position of the seat
			
				seats.add(s);
			}
			
			for(SeatView s : seats) {
			
				int i = s.getNumberOfSeat();
			
				//Set the correct color from the state of the seat
				StateSeat state = hall.getStateSeat(i);
				if(state.equals(StateSeat.Unavailable))
					s.setC(Color.gray);
				else
					s.setC(Color.white);	
				//Set the correct color from the state of the seat
			}
		}
		
	}
	
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
			g.drawString(Integer.toString(numberOfSeat), 
					(int)(this.getX() + (this.getWidth() / 2)), 
					(int)(this.getY() + (this.getHeight() / 2)));
		}
		
		public void renderBorder(Graphics2D g, Color color) {
			g.setColor(color);
			g.draw(this);
		}
		
		public void setC(Color c) {
			this.c = c;
		}
		
		private int numberOfSeat;
		private Color c = Color.BLACK;
	}
	
	public BasicHallView(Window window, int numberOfSeatH) {	
		this.window = window;
		this.numberOfSeatH = numberOfSeatH;
		
		seats = new ArrayList<>();
		
		selectedSeat = null;
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				updatePosition(e.getComponent().getSize().width, e.getComponent().getSize().height);
				try {
					window.update();
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
			
		});
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				for(SeatView s : seats) {
					if(s.contains(e.getPoint())) {
						
						selectedSeat = s.getNumberOfSeat();
						try {
							window.update();
						} catch (CurrentStateNotAvailable e1) {
							e1.printStackTrace();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	private void updatePosition(int wFrame, int hFrame) {
		int x = 0, y = 0;
		int w = 40, h = 40;
		int padX = 5, padY = 5;
		
		int numberOfSeatV = seats.size() / numberOfSeatH;
		
		int wImage = numberOfSeatH * (w + padX);
		int hImage = numberOfSeatV * (h + padY);
		
		int startX = (wFrame - wImage) / 2;
		int startY = (hFrame - hImage) / 2;
		
		if(wImage > wFrame)
		{
			double d = wFrame / (double)wImage;
			w *= d;
			padX *= d;
			startX = 0;
			System.out.println(w);
		}
		if(hImage > hFrame)
		{
			double d = hFrame / (double)hImage;
			h *= d;
			padY *= d;
			startY = 0;
		}
			
		
		for(SeatView s : seats) {
			
			int i = s.getNumberOfSeat();
			
			//Compute position of the seat
			y = startY + (i / numberOfSeatH) * (h + padY);
			
			if(i % numberOfSeatH == 0)
				x = startX;
			
			s.setRect(x, y, w, h);
			x += w + padX;
			//Compute position of the seat
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		seats.stream().forEach((r) -> {r.draw(g2);});
		
		if(selectedSeat != null)
		{
			g2.setColor(Color.black);
			seats.get(selectedSeat).renderBorder(g2, Color.black);
		}
		
		System.out.println("WEWE");
	}

	public Integer getSelectedSeat() {
		return selectedSeat;
	}
	
	protected List<SeatView> seats;
	protected int numberOfSeatH;
	@SuppressWarnings("unused")
	private Window window;
	
	private Integer selectedSeat;
}
