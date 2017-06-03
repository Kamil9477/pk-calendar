package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CalendarFrame;
import view.EventsTableFrame;
import view.NewEventFrame;
import model.Event;
import model.EventManager;
import model.EventTableModel;

public class NewEventController {
	private NewEventFrame newEventFrame;
	private EventManager eventMan;
	private CalendarFrame calFrame;
	private EventTableModel evTabModel;
	
	public NewEventController(NewEventFrame newEventFrame, EventManager eventMan, 
			CalendarFrame calFrame, EventTableModel evTabModel) {
		this.newEventFrame = newEventFrame;
		this.eventMan = eventMan;
		this.calFrame = calFrame;
		this.evTabModel = evTabModel;
		
		newEventFrame.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEvent();
			}
		});
	}
	
	/**
	 * metoda kt�ra dodaje do listy nowe wydarzenie pobieraj�c dane z p�l tekstowych
	 * a nast�pnie wywo�uje metod� czyszcz�c� te pola
	 */
	public void createEvent() {
		eventMan.addEvent(new Event(newEventFrame.getDateTextF().getText(), newEventFrame.getHourTextF().getText(), 
				newEventFrame.getPlaceTextF().getText(), newEventFrame.getDescTextA().getText()));
		newEventFrame.clearFields();
		
		//aktualizacja danych
		calFrame.updateView();
		evTabModel.setDataFromDB();
		evTabModel.fireTableDataChanged();
	}
	
}
