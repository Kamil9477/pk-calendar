import java.awt.EventQueue;
import view.CalendarFrame;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
				new CalendarFrame().setVisible(true);
            }
        });
	}

}
