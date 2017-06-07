package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.DateModel;
import model.EventManager;
import model.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * G³owne okno kalendarza
 *
 */
public class CalendarFrame extends JFrame {
	
	private DateModel dateModel;
	private EventManager eventManager;
	
	private JPanel contentPane;
	private JButton prYear;
	private JButton prMonth;
	private JButton nextMonth;
	private JButton nextYear;
	private JLabel[] dayNames;
	private JButton[] days;
	private JLabel monthLabel;
	private JLabel yearLabel;
	private JLabel slashLabel;
	private JButton addEvent;
	private JMenuBar menuBar;
	private JMenuItem importMenuItem;
	private JMenuItem descMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem addEventMenuItem;
	private JMenuItem showEventsMenuItem;
	
	/**
	 * ustawienie wygl¹du okna 
	 * @param dateModel obiekt odpowiadajacy za miesi¹c i rok wyœwietlany na planszy kalendarza
	 * @param eventManager obiekt zarz¹dzaj¹cy wydarzeniami
	 */
	public CalendarFrame(DateModel dateModel, EventManager eventManager) {
		this.dateModel = dateModel;
		this.eventManager = eventManager;
		setTitle("Kalendarz");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 619);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//dodajemy przyciski przewijaj¹ce date i etykiety j¹ wyœwietlaj¹ce
		contentPane.add(getPrYear());
		contentPane.add(getPrMonth());
		contentPane.add(getNextYear());
		contentPane.add(getNextMonth());
		contentPane.add(getYearLabel());
		contentPane.add(getSlashLabel());
		contentPane.add(getMonthLabel());
		//dolne przyciski
		contentPane.add(getAddEvent());
		
		//kontener na dni miesi¹ca
		JPanel panel = new JPanel();
		panel.setBounds(15, 90, 615, 393);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(7, 7, 3, 3));				
				
		//dodajemy do panelu nazwy dni tygodnia
		for(int i=0; i<getDayNames().length; i++) {
			panel.add(dayNames[i]);
		}
		
		//i pola kalendarza
		for(int i=0; i<getDays().length; i++) {
			panel.add(days[i]);
		}
		
		//¿eby po uruchomieniu by³a wyœwietlona plansza
		updateView();
	}
	
	/**
	 * metoda aktualizuj¹ca widok
	 */
	public void updateView() {
		//czyœcimy planszê kalendarza
		for(int i=0; i<days.length; i++) {
			days[i].setText("");
			days[i].setEnabled(true);
			days[i].setBackground(null);
		}
		
		//aktualizujemy tekst na górze
		monthLabel.setText(Integer.toString(dateModel.getMonth()));
		yearLabel.setText(Integer.toString(dateModel.getYear()));
		
		//aktualizujemy planszê kalendarza
		for(int i=1, j=dateModel.getFirstDay(); i<dateModel.getMonthDays()+1; i++, j++) {
			days[j].setText(Integer.toString(i));
		}
		
		//ustawiamy obramowanie
		for(int i=0; i<days.length; i++) {
			if(!days[i].getText().equals("")) {
				days[i].setBorder(new LineBorder(Color.black, 1, false));
			} else {
				days[i].setBorder(new LineBorder(Color.black, 0, false));
				days[i].setEnabled(false);
			}
		}
		
		//sprawdzamy czy w tym miesi¹cu odbywaj¹ siê jakieœ wydarzenia
		//jeœli tak to zmieniamy wygl¹d odpowiednich dni
		for(int i=0; i<days.length; i++) {
			for(Event item : eventManager.getEvents(dateModel.getMonth(), dateModel.getYear())) {
				if (days[i].getText().equals(Integer.toString(item.getDay()))) {
					days[i].setBackground(new Color(255, 162, 128));
					break;
				}
			}
		}
	}
	
	/**
	 * ustawia wygl¹d i zwraca przycisk prYear
	 * @return prYear przycisk do zmniejszenia obecnego roku
	 */
	public JButton getPrYear() {
		if(prYear == null) {
			prYear = new JButton("<<");
			prYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prYear.setBounds(15, 16, 83, 58);
		}
		return prYear;
	}
	
	/**
	 * ustawia wygl¹d i zwraca przycisk prMonth
	 * @return prMonth przycisk do zmniejszenia obecnego miesi¹ca
	 */
	public JButton getPrMonth() {
		if(prMonth == null) {
			prMonth = new JButton("<");
			prMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prMonth.setBounds(113, 16, 83, 58);
		}
		return prMonth;
	}
	
	/**
	 * ustawia wygl¹d i zwraca przycisk nextYear
	 * @return nextYear przycisk do zwiêkszenia obecnego roku
	 */
	public JButton getNextYear() {
		if(nextYear == null) {
			nextYear = new JButton(">>");
			nextYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextYear.setBounds(548, 16, 83, 58);
		}
		return nextYear;
	}
	
	/**
	 * ustawia wygl¹d i zwraca przycisk nextMonth
	 * @return nextMonth przycisk do zwiêkszenia obecnego miesi¹ca
	 */
	public JButton getNextMonth() {
		if(nextMonth == null) {
			nextMonth = new JButton(">");
			nextMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextMonth.setBounds(450, 16, 83, 58);
		}
		return nextMonth;
	}
	
	/**
	 * ustawia wygl¹d i zwraca etykiety wyœwietlaj¹ce nazwy dni tygodnia
	 * @return dayNames etykiety z dniami tygodnia
	 */
	private JLabel[] getDayNames() {
		if(dayNames == null) {
			dayNames = new JLabel[7];
			String[] names = {"pon.", "wt.", "œr.", "czw.", "pt.", "sob.", "niedz."};
			for(int i=0; i<dayNames.length; i++) {
				dayNames[i] = new JLabel(names[i]);
				dayNames[i].setHorizontalAlignment(SwingConstants.CENTER);
				dayNames[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			}
			dayNames[5].setForeground(Color.RED);
			dayNames[6].setForeground(Color.RED);
		}
		return dayNames;
	}
	
	/**
	 * ustawia wygl¹d i zwraca tablicê przycisków reprezentuj¹cych pojedyñczy dzieñ w misi¹cu
	 * @return days tablica przycisków reprezentuj¹cych pojedyñczy dzieñ w miesi¹cu
	 */
	public JButton[] getDays() {
		if(days == null) {
			days = new JButton[42];
			for(int i=0; i<days.length; i++) {
				days[i] = new JButton();
				days[i].setHorizontalAlignment(SwingConstants.CENTER);
				days[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			}
		}
		return days;
	}
	
	/**
	 * ustawia wygl¹d i zwraca etykietê wyœwietlaj¹c¹ obecny miesi¹c
	 * @return monthLabel etykieta wyœwietlaj¹ca obecny miesi¹c
	 */
	private JLabel getMonthLabel() {
		if(monthLabel == null) {
			monthLabel = new JLabel(Integer.toString(dateModel.getMonth()));
			monthLabel.setBounds(241, 15, 58, 58);
			monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			monthLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		}
		return monthLabel;
	}
	
	/**
	 * ustawia wygl¹d i zwraca etykietê wyœwietlaj¹c¹ obecny rok
	 * @return yearLabel etykieta wyœwietlaj¹ca obecny rok
	 */
	private JLabel getYearLabel() {
		if (yearLabel == null) {
			yearLabel = new JLabel(Integer.toString(dateModel.getYear()));
			yearLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
			yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
			yearLabel.setBounds(313, 15, 108, 58);
		}
		return yearLabel;
	}
	private JLabel getSlashLabel() {
		if (slashLabel == null) {
			slashLabel = new JLabel("/");
			slashLabel.setHorizontalAlignment(SwingConstants.CENTER);
			slashLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
			slashLabel.setBounds(296, 16, 20, 58);
		}
		return slashLabel;
	}
	
	/**
	 * ustawia wygl¹d i zwraca przycisk addEvent
	 * @return prYear przycisk do dodawania wydarzenia
	 */
	public JButton getAddEvent() {
		if (addEvent == null) {
			addEvent = new JButton("Dodaj wydarzenie");
			addEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
			addEvent.setBounds(241, 499, 171, 45);
		}
		return addEvent;
	}
	
	/**
	 * ustawia wygl¹d i zwraca górny pasek menu
	 * @return menuBar górny pasek menu
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			
			JMenu programMenu = new JMenu("Program");
			menuBar.add(programMenu);
			
			programMenu.add(getImportMenuItem());
			programMenu.add(getDescMenuItem());
			programMenu.add(getExitMenuItem());
			
			JMenu eventsMenu = new JMenu("Wydarzenia");
			menuBar.add(eventsMenu);
		
			eventsMenu.add(getAddEventMenuItem());
			eventsMenu.add(getShowEventsMenuItem());
		}
		return menuBar;
	}
	
	/**
	 * ustawia wygl¹d i zwraca importMenuItem
	 * @return importMenuItem element menu, po klikniêciu nastêpuje import z XML
	 */
	public JMenuItem getImportMenuItem() {
		if(importMenuItem == null) {
			importMenuItem = new JMenuItem("Importuj z XML");
		}
		return importMenuItem;
	}
	
	/**
	 * ustawia wygl¹d i zwraca descMenuItem
	 * @return descMenuItem element menu, po klikniêciu nastêpuje wyœwietlenie okna "O programie"
	 */
	public JMenuItem getDescMenuItem() {
		if(descMenuItem == null) {
			descMenuItem = new JMenuItem("O programie");
		}
		return descMenuItem;
	}
	
	/**
	 * ustawia wygl¹d i zwraca exitMenuItem
	 * @return exitMenuItem element menu, po klikniêciu program koñczy pracê
	 */
	public JMenuItem getExitMenuItem() {
		if(exitMenuItem == null) {
			exitMenuItem = new JMenuItem("Wyjd\u017A");
		}
		return exitMenuItem;
	}
	
	/**
	 * ustawia wygl¹d i zwraca addEventMenuItem
	 * @return addEventMenuItem element menu, po klikniêciu pojawia siê okno dodawania nowego wydarzenia
	 */
	public JMenuItem getAddEventMenuItem() {
		if(addEventMenuItem == null) {
			addEventMenuItem = new JMenuItem("Dodaj wydarzenie");
		}
		return addEventMenuItem;
	}
	
	/**
	 * ustawia wygl¹d i zwraca showEventsMenuItem
	 * @return showEventsMenuItem element menu, po klikniêciu pojawia siê okno z tabel¹ z wszystkimi wydarzeniami
	 */
	public JMenuItem getShowEventsMenuItem() {
		if(showEventsMenuItem == null) {
			showEventsMenuItem = new JMenuItem("Przegl\u0105daj wydarzenia");
		}
		return showEventsMenuItem;
	}
}
