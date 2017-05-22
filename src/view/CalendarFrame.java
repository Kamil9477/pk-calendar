package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Color;

public class CalendarFrame extends JFrame {

	private JPanel contentPane;
	private JButton prYear;
	private JButton prMonth;
	private JLabel dateLabel;
	private JButton nextMonth;
	private JButton nextYear;
	private JLabel[] dayNames;
	private JTextField[] days;

	public CalendarFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//dodajemy przyciski przewijaj¹ce date
		contentPane.add(getPrYear());
		contentPane.add(getPrMonth());
		contentPane.add(getNextYear());
		contentPane.add(getNextMonth());
		contentPane.add(getDateLabel());
		
		//kontener na dni miesi¹ca
		JPanel panel = new JPanel();
		panel.setBounds(15, 90, 615, 393);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		
		for(int i=0; i<getDayNames().length; i++) {
			panel.add(dayNames[i]);
		}
		
		for(int i=0; i<getDays().length; i++) {
			panel.add(days[i]);
		}	
	}
	
//metody ustawiaj¹ce wygl¹d komponentów
//---------------------------------------------------------------------------------
	private JButton getPrYear() {
		if(prYear == null) {
			prYear = new JButton("<<");
			prYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prYear.setBounds(15, 16, 83, 58);
		}
		return prYear;
	}
	
	private JButton getPrMonth() {
		if(prMonth == null) {
			prMonth = new JButton("<");
			prMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			prMonth.setBounds(127, 16, 83, 58);
		}
		return prMonth;
	}
	
	private JButton getNextYear() {
		if(nextYear == null) {
			nextYear = new JButton(">>");
			nextYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextYear.setBounds(546, 16, 83, 58);
		}
		return nextYear;
	}
	
	private JButton getNextMonth() {
		if(nextMonth == null) {
			nextMonth = new JButton(">");
			nextMonth.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nextMonth.setBounds(436, 16, 83, 58);
		}
		return nextMonth;
	}
	
	private JLabel getDateLabel() {
		if(dateLabel == null) {
			dateLabel = new JLabel("5/2017");
			dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
			dateLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
			dateLabel.setBounds(240, 16, 180, 58);
		}
		return dateLabel;
	}
	
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
	
	private JTextField[] getDays() {
		if(days == null) {
			days = new JTextField[42];
			for(int i=0; i<days.length; i++) {
				days[i] = new JTextField();
				days[i].setHorizontalAlignment(SwingConstants.CENTER);
				days[i].setEditable(false);
			}
		}
		return days;
	}
}
