import java.awt.EventQueue;

import controller.DateController;
import model.DateModel;
import view.CalendarFrame;


public class Main {

	public static void main(String[] args) {
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
