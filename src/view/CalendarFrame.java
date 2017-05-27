package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.DateModel;

public class CalendarFrame extends JFrame {
	
	private DateModel model;
	private JPanel contentPane;
	private JButton prYear;
	private JButton prMonth;
	private JButton nextMonth;
	private JButton nextYear;
	private JLabel[] dayNames;
	private JTextField[] days;
	private JLabel monthLabel;
	private JLabel yearLabel;
	private JLabel slashLabel;
	private JButton addEvent;

	public CalendarFrame(DateModel model) {
		setTitle("Kalendarz");
		this.model = model;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//dodajemy przyciski przewijaj�ce date i etykiety j� wy�wietlaj�ce
		contentPane.add(getPrYear());
		contentPane.add(getPrMonth());
		contentPane.add(getNextYear());
		contentPane.add(getNextMonth());
		contentPane.add(getYearLabel());
		contentPane.add(getSlashLabel());
		contentPane.add(getMonthLabel());
		//dolne przyciski
		contentPane.add(getAddEvent());
		
		//kontener na dni miesi�ca
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
		
		//�eby po uruchomieniu by�a wy�wietlona plansza
		updateView();
	}
	
	public void updateView() {
		//czy�cimy plansz� kalendarza
		for(int i=0; i<days.length; i++) {
			days[i].setText("");
		}
		
		//aktualizujemy tekst na g�rze
		monthLabel.setText(Integer.toString(model.getMonth()));
		yearLabel.setText(Integer.toString(model.getYear()));
		
		//i plansz� kalendarza
		for(int i=1, j=model.getFirstDay(); i<model.getMonthDays()+1; i++, j++) {
			days[j].setText(Integer.toString(i));
		}
	
		for(int i=0; i<days.length; i++) {
			if(!days[i].getText().equals("")) {
				days[i].setBorder(new LineBorder(Color.black, 1, false));
			} else {
				days[i].setBorder(new LineBorder(Color.black, 0, false));
			}
		}
	}
	
//metody ustawiaj�ce wygl�d komponent�w
//---------------------------------------------------------------------------------
	public JButton getPrYear() {
		if(prYear == null) {
			prYear = new JButton("<<");
			prYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prYear.setBounds(15, 16, 83, 58);
		}
		return prYear;
	}
	
	public JButton getPrMonth() {
		if(prMonth == null) {
			prMonth = new JButton("<");
			prMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prMonth.setBounds(127, 16, 83, 58);
		}
		return prMonth;
	}
	
	public JButton getNextYear() {
		if(nextYear == null) {
			nextYear = new JButton(">>");
			nextYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextYear.setBounds(546, 16, 83, 58);
		}
		return nextYear;
	}
	
	public JButton getNextMonth() {
		if(nextMonth == null) {
			nextMonth = new JButton(">");
			nextMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextMonth.setBounds(436, 16, 83, 58);
		}
		return nextMonth;
	}
	
	private JLabel[] getDayNames() {
		if(dayNames == null) {
			dayNames = new JLabel[7];
			String[] names = {"pon.", "wt.", "�r.", "czw.", "pt.", "sob.", "niedz."};
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
	
	private JTextField[] getDays() {
		if(days == null) {
			days = new JTextField[42];
			for(int i=0; i<days.length; i++) {
				days[i] = new JTextField();
				days[i].setHorizontalAlignment(SwingConstants.CENTER);
				days[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
				days[i].setEditable(false);
			}
		}
		return days;
	}
	
	private JLabel getMonthLabel() {
		if(monthLabel == null) {
			monthLabel = new JLabel(Integer.toString(model.getMonth()));
			monthLabel.setBounds(241, 16, 58, 58);
			monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			monthLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		}
		return monthLabel;
	}
	
	private JLabel getYearLabel() {
		if (yearLabel == null) {
			yearLabel = new JLabel(Integer.toString(model.getYear()));
			yearLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
			yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
			yearLabel.setBounds(298, 16, 108, 58);
		}
		return yearLabel;
	}
	private JLabel getSlashLabel() {
		if (slashLabel == null) {
			slashLabel = new JLabel("/");
			slashLabel.setHorizontalAlignment(SwingConstants.CENTER);
			slashLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
			slashLabel.setBounds(291, 16, 20, 58);
		}
		return slashLabel;
	}
	
	public JButton getAddEvent() {
		if (addEvent == null) {
			addEvent = new JButton("Dodaj wydarzenie");
			addEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
			addEvent.setBounds(458, 499, 171, 45);
		}
		return addEvent;
	}
}