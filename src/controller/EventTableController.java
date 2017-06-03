package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import view.EventsTableFrame;
import model.DBManager;
import model.EventManager;
import model.EventTableModel;

public class EventTableController {
	EventManager eventMan;
	DBManager dbMan;
	EventTableModel evTabModel;
	EventsTableFrame evTabFrame;
	
	public EventTableController(EventManager eventMan, 
			EventTableModel evTabModel, EventsTableFrame evTabFrame) {
		this.eventMan = eventMan;
		this.dbMan = eventMan.getDBManager();
		this.evTabModel = evTabModel;
		this.evTabFrame = evTabFrame;
		
		evTabFrame.getRemoveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEvent();
			}
		});
	}
	
	/**
	 * metoda tworzy list� dat i godzin wybranych do usuni�cia wydarze�
	 * i przekazuje je dalej do usuni�cia
	 */
	public void removeEvent() {
		int[] selected = evTabFrame.getTable().getSelectedRows();
		
		if (selected.length > 0) {
			List<String> dates = new ArrayList<String>();
			List<String> hours = new ArrayList<String>();
			for (int i = 0; i < selected.length; i++) {
				dates.add((String) evTabModel.getValueAt(selected[i], 0));
				hours.add((String) evTabModel.getValueAt(selected[i], 1));
			}
			eventMan.removeEvents(dates, hours);
			
			//od�wie�anie danych
			evTabModel.setDataFromDB();
			evTabModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(null, "Nie zaznaczono �adnego wydarzenia!", "B��d", JOptionPane.ERROR_MESSAGE);
		}
	}
}
