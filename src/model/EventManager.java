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
	 * klasa odpowiedzialna za komunikomanie siê z baz¹ danych
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
	 * Metoda zwracaj¹ca wydarzenie które odbywa siê w podanym dniu, miesi¹cu oraz roku
	 * jeœli w podanej dacie nie ma wydazenia to zwraca null
	 * @param day dzieñ
	 * @param month miesi¹c
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
