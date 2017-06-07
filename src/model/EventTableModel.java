package model;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Model tabeli która wyœwietla wydarzenia
 *
 */
public class EventTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;
	private DBManager dbMan;
	
	/**
	 * ustawia nazwy kolumn w tabeli, wczytuje to tabeli wydarzenia z bazy danych
	 * @param dbMan obiekt do komunikowania siê z baz¹ danych
	 */
	public EventTableModel(DBManager dbMan) {
		columnNames = new String[]{"DATA", "GODZINA", "MIEJSCE", "OPIS"};
		this.dbMan = dbMan;
		setDataFromDB();
	}
	
	/**
	 * metoda wyœwietla w tabeli wydarzenia znajduj¹ce siê w bazie danych
	 */
	public void setDataFromDB() {
		List <Event> events = dbMan.getEventsFromDB();
		Collections.sort(events);
		
		data = new Object[events.size()][4];
		for(int i=0; i<events.size(); i++) {
			data[i][0] = events.get(i).getDate();
			data[i][1] = events.get(i).getHour();
			data[i][2] = events.get(i).getPlace();
			data[i][3] = events.get(i).getDesc();
		}
	}
	
	/**
	 * metoda wyœwietla w tabeli wydarzenia o podanych filtrach
	 * @param fields pola po których filtrujemy
	 * @param filters filtry
	 */
	public void setFilter(List<String> fields, List<String> filters) {
		List<Event> filtered = dbMan.getFilteredEvents(fields, filters);
		Collections.sort(filtered);
		data = new Object[filtered.size()][4];
		for(int i=0; i<filtered.size(); i++) {
			data[i][0] = filtered.get(i).getDate();
			data[i][1] = filtered.get(i).getHour();
			data[i][2] = filtered.get(i).getPlace();
			data[i][3] = filtered.get(i).getDesc();
		}
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
}
