package model;

/**
 * klasa reprezentuj¹ca pojedyñcze wydarzenie
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
	 * Metoda wyci¹gaj¹ca z pola "date" dzieñ wydarzenia
	 * @return dzieñ w którym odbywa siê wydarzenie
	 */
	public int getDay() {
		String[] tab = date.split("-");
		return Integer.parseInt(tab[0]);
	}
	
	/**
	 * Metoda wyci¹gaj¹ca z pola "date" miesi¹c wydarzenia
	 * @return miêsi¹c w którym odbywa siê dane wydarzenie
	 */
	public int getMonth() {
		String[] tab = date.split("-");
		return Integer.parseInt(tab[1]);
	}
	
	/**
	 * Metoda wyci¹gaj¹ca z pola "date" rok wydarzenia
	 * @return rok w którym odbywa siê dane wydarzenie
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
	 * musia³em samemu zaimplementowaæ 
	 * bo compareTo nie wspiera typów prymitywnych
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
