package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;

import view.CalendarFrame;
import view.EventInfo;
import view.NewEventFrame;
import model.DateModel;
import model.Event;
import model.EventManager;

public class CalendarFrameController {
	private DateModel dateModel;
	private CalendarFrame calFrame;
	private EventManager eventManager;
	private NewEventFrame newEventFrame;
	private NewEventController newEventController;
	
	public CalendarFrameController() {
		dateModel = new DateModel();
		eventManager = new EventManager();
		calFrame = new CalendarFrame(dateModel, eventManager);
		calFrame.setVisible(true);
		
		newEventFrame = new NewEventFrame();
		newEventController = new NewEventController(newEventFrame, eventManager);
		
		//ustawienie listenerów
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
		//jeœli nie bêdzie tego dnia ¿adnego wydarzenia to poajwi siê odpowiedni komunikat
		for(int i=0; i<calFrame.getDays().length; i++) {
			calFrame.getDays()[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//przekazujemy do funkcji dzien do ktorego byl przypisany dany przycisk
					printEvents(Integer.parseInt(((JButton) e.getSource()).getText()));
				}
			});
		}
	}
	
	/**
	 * Zmniejsza rok przechowywany w modelu
	 * aktualizuje widok
	 */
	public void decYear() {
		dateModel.setYear(dateModel.getYear() - 1);
		calFrame.updateView();
	}
	
	/**
	 * Zwiêksza rok przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incYear() {
		dateModel.setYear(dateModel.getYear() + 1);
		calFrame.updateView();
	}
	
	/**
	 * Zmniejsza miesi¹c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void decMonth() {
		dateModel.setMonth(dateModel.getMonth() - 1);
		calFrame.updateView();
	}
	
	/**
	 * Zwiêksza miesi¹c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incMonth() {
		dateModel.setMonth(dateModel.getMonth() + 1);
		calFrame.updateView();
	}
	
	/**
	 * Wypisuje informacje o wydarzeniach w danym dniu
	 * tworzy nowego JDialoga z informacjami
	 */
	public void printEvents(int day) {
		List<Event> events = eventManager.getEvent(day, dateModel.getMonth(), dateModel.getYear());
		
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
}
