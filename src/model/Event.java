package model;

public class Event {
	private String date;
	private String hour;
	private String place;
	private String description;
	
	public Event(String date, String hour, String place, String description) {
		this.date = date;
		this.hour = hour;
		this.place = place;
		this.description = description;
		System.out.println("zrobione");
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
	 * zwraca godzine wydarzenia
	 * @return godzina
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
	
}
