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

import controller.DateController;
import model.DateModel;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	public CalendarFrame(DateModel model) {
		this.model = model;
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
		contentPane.add(getYearLabel());
		
		//kontener na dni miesi¹ca
		JPanel panel = new JPanel();
		panel.setBounds(15, 90, 615, 393);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		contentPane.add(getSlashLabel());
		contentPane.add(getMonthLabel());
		
		for(int i=0; i<getDayNames().length; i++) {
			panel.add(dayNames[i]);
		}
		
		for(int i=0; i<getDays().length; i++) {
			panel.add(days[i]);
		}	
	}
	
	public void updateView() {
		monthLabel.setText(Integer.toString(model.getMonth()));
		yearLabel.setText(Integer.toString(model.getYear()));
	}
	
//metody ustawiaj¹ce wygl¹d komponentów
//---------------------------------------------------------------------------------
	public JButton getPrYear() {
		if(prYear == null) {
			prYear = new JButton("<<");
			prYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
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
}
