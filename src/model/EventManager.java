package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventManager {
	/**
	 * lista wydarzeñ
	 */
	private List<Event> eventList;
	
	/**
	 * obiekt odpowiedzialny za komunikomanie siê z baz¹ danych
	 */
	private DBManager dbManager;
	
	/**
	 * obiekt odpowiedzialny z zapis i odczyt z XML
	 */
	private XMLManager xmlManager;
	
	/**
	 * w konstruktorze ³¹dujemy do listy wszystkie wydarzenia z bazy danych
	 */
	public EventManager() {
		dbManager = new DBManager();
		xmlManager = new XMLManager();
		eventList = dbManager.getEventsFromDB();
	}
	
	/**
	 * zwraca liste wszystkich wydarzeñ
	 * @return
	 */
	public List<Event> getAllEvents() {
		return eventList;
	}
	
	/**
	 * zwraca obiekt DBManager, klasa EventTableModel tego potrzebuje
	 * @return
	 */
	public DBManager getDBManager() {
		return dbManager;
	}
	
	/**
	 * Dodaje wydarzenie do listy
	 * @param event nowe wydarzenie
	 */
	public void addEvent(Event ev) {
		eventList.add(ev);
		dbManager.addEventToDB(ev);
	}
	
	/**
	 * Metoda zwracaj¹ca listê wydarzeñ odbywaj¹cych siê w podanym miesi¹cu i roku 
	 * @param month miesi¹c
	 * @param year rok
	 * @return lista wydarzeñ
	 */
	public List<Event> getEvents(int month, int year) {
		List<Event> events = new ArrayList<Event>();
		for(Event item : eventList) {
			if(item.getMonth() == month && item.getYear() == year) {
				events.add(item);
			}
		}
		return events;
	}
	
	/**
	 * Metoda zwracaj¹ca liste wydarzeñ które odbywa siê w podanym dniu, miesi¹cu oraz roku
	 * jeœli w podanej dacie nie ma wydazenia to zwraca null
	 * @param day dzieñ
	 * @param month miesi¹c
	 * @param year rok
	 * @return wydarzenie lub null
	 */
	public List<Event> getEvent(int day, int month, int year) {
		List<Event> events = new ArrayList<Event>();
		for (Event item : eventList) {
			if(item.getYear() == year && item.getMonth() == month && item.getDay() == day) {
				events.add(item);
			}
		}
		return events;
	}
	
	/**
	 * metoda która usuwa wydarzenia z listy wydarzeñ oraz przekauje polecenie
	 * do DBManagera ¿eby usun¹³ je te¿ z bazy danych
	 * metoda pobiera liste dat i godzin wydarzeñ które ma usun¹æ
	 * @param dates
	 * @param hours
	 */
	public void removeEvents(List<String> dates, List<String> hours) {
		List<Event> toRemove = new ArrayList<Event>();
		for(Event item : eventList) {
			for(int i=0; i<dates.size(); i++) {
				if(item.getDate().equals(dates.get(i)) && item.getHour().equals(hours.get(i))) {
					dbManager.removeEventFromDB(item);
					toRemove.add(item);
				}
			}
		}
		
		eventList.removeAll(toRemove);
	}
	
	/**
	 * metoda tworzy liste wydarzeñ do exportu i przekazuje je do XMLManagera
	 * @param dates 
	 * @param hours
	 * @param path
	 */
	public void exportToXML(List<String> dates, List<String> hours, String path) {
		List<Event> eventsToExport = new ArrayList<Event>();
		for(Event item : eventList) {
			for(int i=0; i<dates.size(); i++) {
				if(item.getDate().equals(dates.get(i)) && item.getHour().equals(hours.get(i))) {
					eventsToExport.add(item);
				}
			}
		}
		xmlManager.exportToXML(eventsToExport, path);
	}
}
