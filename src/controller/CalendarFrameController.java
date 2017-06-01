package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;

import view.CalendarFrame;
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
		
		//ustawienie listener�w
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
		
		//listenery dla dni tygodnia - je�li naci�niemy to pojawi si� informacja o wydarzeniach
		//je�li nie b�dzie tego dnia �adnego wydarzenia to poajwi si� odpowiedni komunikat
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
	 * Zwi�ksza rok przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incYear() {
		dateModel.setYear(dateModel.getYear() + 1);
		calFrame.updateView();
	}
	
	/**
	 * Zmniejsza miesi�c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void decMonth() {
		dateModel.setMonth(dateModel.getMonth() - 1);
		calFrame.updateView();
	}
	
	/**
	 * Zwi�ksza miesi�c przechowywany w modelu
	 * aktualizuje widok
	 */
	public void incMonth() {
		dateModel.setMonth(dateModel.getMonth() + 1);
		calFrame.updateView();
	}
	
	/**
	 * Wypisuje informacje o wydarzeniach w danym dniu, roku i miesiacu
	 */
	public void printEvents(int day) {
		Event ev = eventManager.getEvent(day, dateModel.getMonth(), dateModel.getYear());
		if(ev != null) {
			System.out.println(ev.toString());
		} else {
			System.out.println("Nie ma wydarzenia!");
		}
	}
}