import java.awt.EventQueue;

import javax.swing.UIManager;

import controller.DateController;
import model.DateModel;
import view.CalendarFrame;

public class Main {

	public static void main(String[] args) {
		try { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } catch(Exception e){}
		
		EventQueue.invokeLater(new Runnable() {
            public void run(){	
            	DateModel model = new DateModel();
            	CalendarFrame view = new CalendarFrame(model);
            	DateController controller = new DateController(model, view);
				view.setVisible(true);
            }
        });
	}

}