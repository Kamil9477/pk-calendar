package model;

/**
 * klasa reprezentuj�ca pojedy�cze wydarzenie
 *
 */
public class Event implements Comparable<Event>{
	
	private String date;
	private String hour;
	private String place;
	private String description;
	
	public Event(String date, String hour, String place, String description) {
		this.date = date;
		this.hour = hour;
		this.place = place;
		this.description = description;
	}
	
	/**
	 * Zwraca informacje o wydarzeniu
	 * @return data informacje o wydarzeniu
	 */
	public String toString() {
		String data = "Godzina: " + hour + "\n" +
				      "Miejsce: " + place + "\n" +
				      description;
		return data;
	}
	/**
	 * Metoda wyci�gaj�ca z pola "date" dzie� wydarzenia
	 * @return dzie� w kt�rym odbywa si� wydarzenie
	 */
	public int getDay() {
		String[] tab = date.split("-");
		return Integer.parseInt(tab[0]);
	}
	
	/**
	 * Metoda wyci�gaj�ca z pola "date" miesi�c wydarzenia
	 * @return mi�si�c w kt�rym odbywa si� dane wydarzenie
	 */
	public int getMonth() {
		String[] tab = date.split("-");
		return Integer.parseInt(tab[1]);
	}
	
	/**
	 * Metoda wyci�gaj�ca z pola "date" rok wydarzenia
	 * @return rok w kt�rym odbywa si� dane wydarzenie
	 */
	public int getYear() {
		String[] tab = date.split("-");
		return Integer.parseInt(tab[2]);
	}
	
	/**
	 * zwraca date wydarzenia
	 * @return data
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * zwraca czas wydarzenia
	 * @return czas
	 */
	public String getHour() {
		return hour;
	}
	
	/**
	 * zwraca miejsce wydarzenia
	 * @return miejsce
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * zwraca opis wydarzenia
	 * @return opis
	 */
	public String getDesc() {
		return description;
	}
	
	/**
	 * zwraca godzine wydarzenie w int
	 * @return godzina
	 */
	public int getHourInt() {
		String[] tab = hour.split(":");
		return Integer.parseInt(tab[0]);
	}
	
	/**
	 * metoda potrzebna do sortowania po dacie
	 * musia�em samemu zaimplementowa� 
	 * bo compareTo nie wspiera typ�w prymitywnych
	 */
	public int compareTo(Event ev) {
		if (getYear() < ev.getYear()) {
			return -1;
		} else if (getYear() > ev.getYear()) {
			return 1;
		} else {
			if(getMonth() < ev.getMonth()) {
				return -1;
			} else if(getMonth() > ev.getMonth()) {
				return 1;
			} else {
				if(getDay() < ev.getDay()) {
					return -1;
				} else if (getDay() > ev.getDay()) {
					return 1;
				} else {
					if(getHourInt() < ev.getHourInt()) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		}
	}
	
}
