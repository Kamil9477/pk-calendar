package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;

/**
 * klasa odpowiedzialna za zapis i odczyt z XML
 *
 */
public class XMLManager {
	
	/**
	 * metoda zwraca liste wydarzen wczytana z pliku pod sciezka podana w paramatrze
	 * @param path sciezka
	 * @return lista wydarzen
	 */
	public List<Event> loadFromXML(String path) {
		List<Event> events = new ArrayList<Event>();
		XStream xstream = new XStream();
		xstream.alias("Events", List.class);
		xstream.alias("Event", Event.class);
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(path);
			events = (List<Event>) (xstream.fromXML(fis));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			 try {
			 	 fis.close();
			 } catch(IOException e) {
				 e.printStackTrace();
			 }	
		}
		
		return events;
	}
	
	/**
	 * metoda ekportuje do pliku pod œcie¿k¹ podan¹ w drugim parametrze 
	 * wydarzenia podane w pierwszym parametrze
	 * @param events lista wydarzen
	 * @param path sciezka
	 */
	public void exportToXML(List<Event> events, String path) {
		XStream xstream = new XStream();
		
		xstream.alias("Events", List.class);
		xstream.alias("Event", Event.class);
		
		FileOutputStream fos = null;
		String xml;	
	    
	    try {
			fos = new FileOutputStream(path + ".xml");
			fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
		    xml = xstream.toXML(events);
		    byte[] bytes = xml.getBytes("UTF-8");
			fos.write(bytes);
			JOptionPane.showMessageDialog(null, "Wydarzenia wyeksportowano do pliku " + path + ".xml");   
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
