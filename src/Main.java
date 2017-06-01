import java.awt.EventQueue;

import javax.swing.UIManager;

import controller.CalendarFrameController;
import model.DateModel;
import view.CalendarFrame;

public class Main {

	public static void main(String[] args) {
		try { 
	        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel"); 
	    } catch(Exception e){}
		
		EventQueue.invokeLater(new Runnable() {
            public void run(){	
            	CalendarFrameController controller = new CalendarFrameController();
            }
        });
	}
}