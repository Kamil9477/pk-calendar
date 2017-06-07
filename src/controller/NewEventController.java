package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import view.CalendarFrame;
import view.EventsTableFrame;
import view.NewEventFrame;
import model.Event;
import model.EventManager;
import model.EventTableModel;

/**
 * kontroler okienka do dodawania nowych wydarzeñ
 *
 */
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
	 * dodaje do listy nowe wydarzenie pobieraj¹c dane z pól tekstowych (po pomyœlnej walidacji)
	 * a nastêpnie wywo³uje metodê czyszcz¹c¹ te pola i metody aktualizuj¹ce widok
	 */
	public void createEvent() {
		if(isValidDate(newEventFrame.getDateTextF().getText())) {
			if(isValidHour(newEventFrame.getHourTextF().getText())) {
				if(!newEventFrame.getPlaceTextF().getText().equals("")) {
					eventMan.addEvent(new Event(newEventFrame.getDateTextF().getText(), newEventFrame.getHourTextF().getText(), 
							newEventFrame.getPlaceTextF().getText(), newEventFrame.getDescTextA().getText()));
					newEventFrame.clearFields();
					//aktualizacja danych
					calFrame.updateView();
					evTabModel.setDataFromDB();
					evTabModel.fireTableDataChanged();
				} else {
					JOptionPane.showMessageDialog(null, "WprowadŸ miejce!", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "B³êdny format godziny!", "B³¹d", JOptionPane.ERROR_MESSAGE);
			}	
		} else {
			JOptionPane.showMessageDialog(null, "B³êdny format daty!", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * sprawdza (za pomoc¹ wyra¿eñ regularnych) czy podana w parametrze godzina jest poprawna
	 * @param hour godzina
	 * @return true jeœli godzina jest poprawna
	 */
	private boolean isValidHour(String hour) {
		return hour.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
	}
	
	/**
	 * sprawdza czy podana w parametrze data jest w formacie dd-mm-yyyy
	 * @param date data
	 * @return true jeœli data jest poprawna
	 */
	public boolean isValidDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(date.trim());
	    } catch (ParseException pe) {
	    	return false;
	    }
	    return true;
	}
}
