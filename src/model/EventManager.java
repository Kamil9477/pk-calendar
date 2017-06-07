package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * klasa odpowiedzialna za zarz�dzanie wydarzeniami
 *
 */
public class EventManager {
	private List<Event> eventList;
	private DBManager dbManager;
	private XMLManager xmlManager;
	private ICalManager iCalManager;
	
	public EventManager() {
		dbManager = new DBManager();
		xmlManager = new XMLManager();
		eventList = dbManager.getEventsFromDB();
		iCalManager = new ICalManager();
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
	 * Metoda zwracaj�ca liste wydarze� kt�re odbywa si� w podanym dniu, miesi�cu oraz roku
	 * je�li w podanej dacie nie ma wydazenia to zwraca null
	 * @param day dzie�
	 * @param month miesi�c
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
	 * metoda kt�ra usuwa wydarzenia z listy wydarze� oraz przekauje polecenie
	 * do DBManagera �eby usun�� je te� z bazy danych
	 * metoda pobiera liste dat i godzin wydarze� kt�re ma usun��
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
	 * metoda tworzy liste wydarze� do exportu i przekazuje je do XMLManagera
	 * @param dates 
	 * @param hours
	 * @param file 
	 */
	public void export(List<String> dates, List<String> hours, File file, String destination) {
		List<Event> eventsToExport = new ArrayList<Event>();
		for(Event item : eventList) {
			for(int i=0; i<dates.size(); i++) {
				if(item.getDate().equals(dates.get(i)) && item.getHour().equals(hours.get(i))) {
					eventsToExport.add(item);
				}
			}
		}
		
		if(destination.equals("XML")) {
			xmlManager.exportToXML(eventsToExport, file);
		} else {
			iCalManager.export(file, eventsToExport);
		}	
	}
	
	/**
	 * metoda kt�ra dodaje do listy i bazy danych wczytane wydarzenia 
	 * z pliku pod �cie�k� podan� w parametrze
	 * @param path
	 */
	public void loadFromXML(File file) {
		List<Event> events = xmlManager.loadFromXML(file);
		eventList.addAll(events);
		for(Event item : events) {
			dbManager.addEventToDB(item);
		}
	}
}
