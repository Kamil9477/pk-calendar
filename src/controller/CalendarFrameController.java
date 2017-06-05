package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.CalendarFrame;
import view.EventInfo;
import view.EventsTableFrame;
import view.NewEventFrame;
import model.DateModel;
import model.Event;
import model.EventManager;
import model.EventTableModel;

/**
 * kontroler g³ównego okna aplikacji, on "spina" wszystkie elementy programu
 *
 */
public class CalendarFrameController {
	private DateModel dateModel;
	private CalendarFrame calFrame;
	private EventManager eventManager;
	private NewEventFrame newEventFrame;
	private NewEventController newEventController;
	private EventsTableFrame eventsTableFrame;
	private EventTableModel eventTableModel;
	private EventTableController evTabController;
	
	public CalendarFrameController() {
		//g³ówne okno
		dateModel = new DateModel();
		eventManager = new EventManager();
		calFrame = new CalendarFrame(dateModel, eventManager);
		calFrame.setVisible(true);
		
		eventTableModel = new EventTableModel(eventManager.getDBManager());
		
		//okno dodawania
		newEventFrame = new NewEventFrame();
		newEventController = new NewEventController(newEventFrame, eventManager, calFrame, eventTableModel);
		
		//okno z tabela wydarzeñ
		eventsTableFrame = new EventsTableFrame(eventTableModel);
		evTabController = new EventTableController(eventManager, eventTableModel, eventsTableFrame, calFrame);
		
		//listenery do przycisków przewijaj¹cych daty
		calFrame.getPrYear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decYear();
			}
		});
		
		calFrame.getNextYear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incYear();
			}
		});
		
		calFrame.getPrMonth().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decMonth();
			}
		});
		
		calFrame.getNextMonth().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incMonth();
			}
		});
		
		calFrame.getAddEvent().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEventFrame.setVisible(true);
			}
		});
		
		//listenery dla dni tygodnia - jeœli naciœniemy to pojawi siê informacja o wydarzeniach
		//jeœli nie bêdzie tego dnia ¿adnego wydarzenia to poajawi siê odpowiedni komunikat
		for(int i=0; i<calFrame.getDays().length; i++) {
			calFrame.getDays()[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//przekazujemy do funkcji dzien do ktorego byl przypisany dany przycisk
					printEvents(Integer.parseInt(((JButton) e.getSource()).getText()));
				}
			});
		}
		
		//listener dla guzika "Przegl¹daj wyd."
		calFrame.getShowEvents().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsTableFrame.setVisible(true);
			}
		});
		
		//listener dla "Wczytaj z XML"
		calFrame.getLoadFromXML().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFromXML();
			}
		});
	}
	
	/**
	 * zmniejsza rok przechowywany w modelu
	 * aktualizuje widok
	 */
	public void decYear() {
		dateModel.setYear(dateModel.getYear() - 1);
		calFrame.updateView();
	}
	
	/**
	 * zwiêksza rok przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incYear() {
		dateModel.setYear(dateModel.getYear() + 1);
		calFrame.updateView();
	}
	
	/**
	 * zmniejsza miesi¹c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void decMonth() {
		dateModel.setMonth(dateModel.getMonth() - 1);
		calFrame.updateView();
	}
	
	/**
	 * wwiêksza miesi¹c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incMonth() {
		dateModel.setMonth(dateModel.getMonth() + 1);
		calFrame.updateView();
	}
	
	/**
	 * wypisuje informacje o wydarzeniach w danym dniu
	 * tworzy nowego JDialoga z informacjami
	 */
	public void printEvents(int day) {
		List<Event> events = eventManager.getEvent(day, dateModel.getMonth(), dateModel.getYear());
		Collections.sort(events);
		
		if(events.size() > 0) {
			String data = "";
			for(Event item : events) {
				data += item.toString();
				data += "\n----------------------------------\n";
			}
			new EventInfo(data, day, dateModel.getMonth(), dateModel.getYear()).setVisible(true);
		} else {
			new EventInfo("W tym dniu nie ma ¿adnych wydarzeñ!", 
					day, dateModel.getMonth(), dateModel.getYear()).setVisible(true);
		}
	}
	
	/**
	 * metoda uruchamiana naciœniêciem przycisku "Wczytaj z XML"
	 * pobiera od u¿ytkownika œcie¿kê pliku i wywo³uje metodê z Event Managera
	 * odœwie¿a widok
	 */
	public void loadFromXML() {
		String path = JOptionPane.showInputDialog("Podaj œcie¿kê pliku do wczytania: ");
		eventManager.loadFromXML(path);
		calFrame.updateView();
		eventTableModel.setDataFromDB();
		eventTableModel.fireTableDataChanged();
	}
}
