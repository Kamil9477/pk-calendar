package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CalendarFrame;
import view.NewEventFrame;
import model.Event;
import model.EventManager;

public class NewEventController {
	private NewEventFrame newEventFrame;
	private EventManager eventMan;
	private CalendarFrame calFrame;
	
	public NewEventController(NewEventFrame newEventFrame, EventManager eventMan, CalendarFrame calFrame) {
		this.newEventFrame = newEventFrame;
		this.eventMan = eventMan;
		this.calFrame = calFrame;
		
		newEventFrame.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEvent();
			}
		});
	}
	
	/**
	 * metoda która dodaje do listy nowe wydarzenie pobieraj¹c dane z pól tekstowych
	 * a nastêpnie wywo³uje metodê czyszcz¹c¹ te pola
	 */
	public void createEvent() {
		eventMan.addEvent(new Event(newEventFrame.getDateTextF().getText(), newEventFrame.getHourTextF().getText(), 
				newEventFrame.getPlaceTextF().getText(), newEventFrame.getDescTextA().getText()));
		newEventFrame.clearFields();
		calFrame.updateView();
	}
	
}
