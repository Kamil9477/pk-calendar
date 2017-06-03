package model;

import javax.swing.table.AbstractTableModel;

/**
 * Klasa odpowiedzialna za model tabeli która wyœwietla wydarzenia
 * @author Kamil
 *
 */
public class EventTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;
	private DBManager dbMan;
	
	public EventTableModel(DBManager dbMan) {
		columnNames = new String[]{"DATE", "HOUR", "PLACE", "DESCRIPTION"};
		this.dbMan = dbMan;
		setDataFromDB();
	}
	
	/**
	 * metoda wyœwietla w tabeli wydarzenia znajduj¹ce siê w bazie danych
	 */
	public void setDataFromDB() {
		data = new Object[dbMan.getEventsFromDB().size()][4];
		for(int i=0; i<dbMan.getEventsFromDB().size(); i++) {
			data[i][0] = dbMan.getEventsFromDB().get(i).getDate();
			data[i][1] = dbMan.getEventsFromDB().get(i).getHour();
			data[i][2] = dbMan.getEventsFromDB().get(i).getPlace();
			data[i][3] = dbMan.getEventsFromDB().get(i).getDesc();
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
