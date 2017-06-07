package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.validate.ValidationException;

/**
 * Klasa odpowiedzialna za eksport wydarzeñ do formatu iCalendar
 *
 */
public class ICalManager {
	public void export(File file, List<Event> events) {
		net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
		icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
		icsCalendar.getProperties().add(CalScale.GREGORIAN);
		
		for(Event ev : events) {
			//ustawiamy date
			java.util.Calendar startDate = new GregorianCalendar();
			startDate.set(java.util.Calendar.MONTH, ev.getMonth());
			startDate.set(java.util.Calendar.DAY_OF_MONTH, ev.getDay());
			startDate.set(java.util.Calendar.YEAR, ev.getYear());
			startDate.set(java.util.Calendar.HOUR_OF_DAY, ev.getHourInt());
			startDate.set(java.util.Calendar.MINUTE, ev.getMin());
			startDate.set(java.util.Calendar.SECOND, 0);
			DateTime date = new DateTime(startDate.getTime());
			
			//ustawiamy miejsce
			Location location = new Location(ev.getPlace());
			
			//ustawiamy opis
			String desc = ev.getDesc();
			
			//tworzymy icsEvent
			VEvent icsEvent = new VEvent(date, desc);
			icsEvent.getProperties().add(location);
			
			//ustawiamy UID
			UidGenerator ug;
			try {
				ug = new UidGenerator("uidGen");
				Uid uid = ug.generateUid();
				icsEvent.getProperties().add(uid);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			
			//dodajemy do kalendarza
			icsCalendar.getComponents().add(icsEvent);	
		}
		
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(icsCalendar, fout);
			JOptionPane.showMessageDialog(null, "Wydarzenia wyeksportowano do pliku " + file.getAbsolutePath());  
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "B³¹d eksportu do iCal", "B³¹d", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
}
