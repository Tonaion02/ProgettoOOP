package Multiplex;

public class ModHandler {
	
	public ModHandler(Multiplex multiplex) {
		this.multiplex = multiplex;
	}
	
	public String visualizeRevenue() {
		StringBuilder result = new StringBuilder("");
		
		result.append("Incasso settimanale: " + multiplex.getTicketOffice().computeRevenueOfWeek() + "\n");
		result.append("Incasso per film: \n");
		for(String film : multiplex.getFilms()) {
			result.append(film + ": " + multiplex.getTicketOffice().computeRevenueOfFilm(film) + "\n");
		}
		
		return result.toString();
	}
	
	public void setPriceShow(Show show, double newPrice) {
		show.setFullPriceTicket(newPrice);
	}
	
	public String reportShow() {
		StringBuilder result = new StringBuilder("");
		
		result.append("Report spettacoli: \n");
		for(Show s : multiplex.getShows())
			result.append(s.getTitle() + ": " + s.freeSeat() + "\n");
		
		return result.toString();
	}
	
	private Multiplex multiplex;
}
