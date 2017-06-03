package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import view.CalendarFrame;
import view.EventsTableFrame;
import model.DBManager;
import model.EventManager;
import model.EventTableModel;

public class EventTableController {
	EventManager eventMan;
	EventTableModel evTabModel;
	EventsTableFrame evTabFrame;
	CalendarFrame calFrame;
	
	public EventTableController(EventManager eventMan, EventTableModel evTabModel, 
			EventsTableFrame evTabFrame, CalendarFrame calFrame) {
		this.eventMan = eventMan;
		this.evTabModel = evTabModel;
		this.evTabFrame = evTabFrame;
		this.calFrame = calFrame;
		
		evTabFrame.getRemoveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEvent();
			}
		});
		
		evTabFrame.getFilterButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterEvents();
			}
		});
		
		evTabFrame.getDelFilterButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeFilters();
			}
		});
	}
	
	/**
	 * metoda tworzy listê dat i godzin wybranych do usuniêcia wydarzeñ
	 * i przekazuje je dalej do usuniêcia
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
			
			//odœwie¿anie danych
			evTabModel.setDataFromDB();
			evTabModel.fireTableDataChanged();
			calFrame.updateView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Nie zaznaczono ¿adnego wydarzenia!", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void filterEvents() {
		if(evTabFrame.getDateTextF().getText().equals("") && evTabFrame.getHourTextF().getText().equals("")
				&& evTabFrame.getPlaceTextF().getText().equals("") && evTabFrame.getDescTextF().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Nie wprowadzono ¿adnego filtru!", "B³¹d", JOptionPane.ERROR_MESSAGE);
		} else {
			List<String> filters = new ArrayList<String>();
			List<String> fields = new ArrayList<String>();
			
			if(!evTabFrame.getDateTextF().getText().equals("")) {
				filters.add(evTabFrame.getDateTextF().getText());
				fields.add("DATE");
			}
			
			if(!evTabFrame.getHourTextF().getText().equals("")) {
				filters.add(evTabFrame.getHourTextF().getText());
				fields.add("HOUR");
			}
			
			if(!evTabFrame.getPlaceTextF().getText().equals("")) {
				filters.add(evTabFrame.getPlaceTextF().getText());
				fields.add("PLACE");
			}
			
			if(!evTabFrame.getDescTextF().getText().equals("")) {
				filters.add(evTabFrame.getDescTextF().getText());
				fields.add("DESCRIPTION");
			}
			
			evTabModel.setFilter(fields, filters);
			evTabModel.fireTableDataChanged();
		}
	}
	
	public void removeFilters() {
		evTabFrame.clearFields();
		evTabModel.setDataFromDB();
		evTabModel.fireTableDataChanged();
	}
}
