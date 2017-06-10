package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.CalendarFrame;
import view.EventsTableFrame;
import model.DBManager;
import model.EventManager;
import model.EventTableModel;

/**
 * Kontroler okienka do przegl¹dania wszystkich wydarzeñ
 */
public class EventTableController {
	EventManager eventMan;
	EventTableModel evTabModel;
	EventsTableFrame evTabFrame;
	CalendarFrame calFrame;
	
	/**
	 * Przypisanie obiektów podanych w parametrze, ustawienie listenerów dla komponentów okna wyœwietlaj¹cego 
	 * tabelê z wszystkimi wydarzeniami (EventTabFrame)
	 * @param eventMan obiekt zarz¹dzaj¹cy wydarzeniami
	 * @param evTabModel obiekt przechowuj¹cy model tabeli
	 * @param evTabFrame okno wyœwietlaj¹ce tabele wydarzeñ
	 * @param calFrame g³ówne okno kalendarza
	 */
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
		
		evTabFrame.getExportToXML().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				export("XML");
			}
		});
		
		evTabFrame.getExportToICal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				export("iCal");
			}
		});
	}
	
	/**
	 * tworzy listê dat i godzin wybranych do usuniêcia wydarzeñ
	 * i przekazuje je do EventManagera 
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
	
	/**
	 * zczytuje z pól tekstowych filtry i przekazuje je w postaci dwóch list do EventManagera
	 */
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
	
	/**
	 * usuwa filtry, czyli ustawia pocz¹tkowy widok tabeli
	 */
	public void removeFilters() {
		evTabFrame.clearFields();
		evTabModel.setDataFromDB();
		evTabModel.fireTableDataChanged();
	}
	
	/**
	 * metoda tworzy liste dat i godzin zaznaczonych wydarzen do exportu
	 * i przekazuje to do EventManagera
	 * @param destination parametr decyduj¹cy czy bêdzie to eksport do XML czy iCal
	 */
	public void export(String destination) {
		int[] selected = evTabFrame.getTable().getSelectedRows();
		if (selected.length > 0) {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Plik XML", "xml"));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Plik iCal", "ics"));
			fc.setAcceptAllFileFilterUsed(false);
			int result = fc.showSaveDialog(evTabFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fc.getSelectedFile();
			    
			    List<String> dates = new ArrayList<String>();
				List<String> hours = new ArrayList<String>();
				for (int i = 0; i < selected.length; i++) {
					dates.add((String) evTabModel.getValueAt(selected[i], 0));
					hours.add((String) evTabModel.getValueAt(selected[i], 1));
				}
				
				eventMan.export(dates, hours, selectedFile, destination); 
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nie zaznaczono ¿adnego wydarzenia!", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
	}
}
