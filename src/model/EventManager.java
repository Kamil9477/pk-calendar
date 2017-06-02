package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventManager {
	/**
	 * lista wydarze�
	 */
	private List<Event> eventList;
	
	/**
	 * klasa odpowiedzialna za komunikomanie si� z baz� danych
	 */
	private DBManager dbManager;
	
	public EventManager() {
		dbManager = new DBManager();
		eventList = dbManager.getEventsFromDB();
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
	 * Metoda zwracaj�ca list� wydarze� odbywaj�cych si� w podanym miesi�cu i roku 
	 * @param month miesi�c
	 * @param year rok
	 * @return lista wydarze�
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
	 * Metoda zwracaj�ca wydarzenie kt�re odbywa si� w podanym dniu, miesi�cu oraz roku
	 * je�li w podanej dacie nie ma wydazenia to zwraca null
	 * @param day dzie�
	 * @param month miesi�c
	 * @param year rok
	 * @return wydarzenie lub null
	 */
	public Event getEvent(int day, int month, int year) {
		
		for (Event item : eventList) {
			if(item.getYear() == year && item.getMonth() == month && item.getDay() == day) {
				return item;
			}
		}
		return null;
	}
}
