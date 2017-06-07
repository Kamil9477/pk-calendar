package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import view.CalendarFrame;
import view.EventInfo;
import view.EventsTableFrame;
import view.NewEventFrame;
import view.ProgramInfo;
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
	
	/**
	 * Inicjalizuje obiekty klas: DateModel, EventManager, CalendarFrame, EventTableModel, NewEventFrame, 
	 * NewEventController, EventsTableFrame i EventTableController. W³¹cza widocznoœæ g³ównego okna aplikacji(CalendarFrame), 
	 * i ustawia listenery dla komponentów znajduj¹cych siê w tym oknie.
	 */
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
		
		//listenery dla nowego wydarzenia
		calFrame.getAddEvent().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEventFrame.setVisible(true);
			}
		});
		
		calFrame.getAddEventMenuItem().addActionListener(new ActionListener() {
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
		
		//listener dla "Przegl¹daj wydarzenia"
		calFrame.getShowEventsMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsTableFrame.setVisible(true);
			}
		});
		
		//listener dla "Wczytaj z XML"
		calFrame.getImportMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFromXML();
			}
		});
		
		//listener dla "O programie"
		calFrame.getDescMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProgramInfo().setVisible(true);
			}
		});
		
		//listener dla "WyjdŸ"
		calFrame.getExitMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * zmniejsza rok przechowywany w modelu,
	 * aktualizuje widok
	 */
	public void decYear() {
		dateModel.setYear(dateModel.getYear() - 1);
		calFrame.updateView();
	}
	
	/**
	 * zwiêksza rok przechowywany w modelu,
	 * aktualizuje widok
	 */
	public void incYear() {
		dateModel.setYear(dateModel.getYear() + 1);
		calFrame.updateView();
	}
	
	/**
	 * zmniejsza miesi¹c przechowywany w modelu,
	 * aktualizuje widok
	 */
	public void decMonth() {
		dateModel.setMonth(dateModel.getMonth() - 1);
		calFrame.updateView();
	}
	
	/**
	 * zwiêksza miesi¹c przechowywany w modelu,
	 * aktualizuje widok
	 */
	public void incMonth() {
		dateModel.setMonth(dateModel.getMonth() + 1);
		calFrame.updateView();
	}
	
	/**
	 * wypisuje informacje o wydarzeniach w danym dniu,
	 * tworzy nowego JDialoga z informacjami
	 * @param day dzien dla którego maj¹ byæ wypisane wydarzenia
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
	 * pobiera od u¿ytkownika plik i wywo³uje metodê z Event Managera
	 * odœwie¿a widok
	 */
	public void loadFromXML() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = fc.showOpenDialog(calFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fc.getSelectedFile();
		  
		    eventManager.loadFromXML(selectedFile);
			calFrame.updateView();
			eventTableModel.setDataFromDB();
			eventTableModel.fireTableDataChanged();
		}
	}
}
