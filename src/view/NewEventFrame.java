package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class NewEventFrame extends JFrame {

	private JPanel contentPane;
	private JTextField dateTextF;
	private JTextField hourTextF;
	private JTextField placeTextF;
	private JLabel dateLabel;
	private JLabel hourLabel;
	private JLabel placeLabel;
	private JLabel descLabel;
	private JTextArea descTextA;
	private JButton addButton;

	public NewEventFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 509, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(getDateLabel());
		contentPane.add(getHourLabel());
		contentPane.add(getPlaceLabel());
		contentPane.add(getDescLabel());
		
		contentPane.add(getDateTextF());
		contentPane.add(getHourTextF());
		contentPane.add(getPlaceTextF());
		contentPane.add(getDescTextA());
		
		contentPane.add(getAddButton());
	}
	
	public void clearFields() {
		dateTextF.setText("DD-MM-YYYY");
		hourTextF.setText("HH:MM");
		placeTextF.setText("");
		descTextA.setText("");
	}
	
//------------------------------------------------------------------------	
	private JLabel getDateLabel() {
		if(dateLabel == null) {
			dateLabel = new JLabel("Data:");
			dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			dateLabel.setBounds(15, 16, 76, 31);
		}
		return dateLabel;
	}
	
	private JLabel getHourLabel() {
		if(hourLabel == null) {
			hourLabel = new JLabel("Godzina:");
			hourLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			hourLabel.setBounds(15, 56, 86, 20);
		}
		return hourLabel;
	}
	
	private JLabel getPlaceLabel() {
		if(placeLabel == null) {
			placeLabel = new JLabel("Miejsce:");
			placeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			placeLabel.setBounds(15, 92, 86, 20);
		}
		return placeLabel;
	}
	
	private JLabel getDescLabel() {
		if(descLabel == null) {
			descLabel = new JLabel("Opis:");
			descLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			descLabel.setBounds(15, 128, 69, 20);
		}
		return descLabel;
	}
	
	public JTextField getHourTextF() {
		if(hourTextF == null) {
			hourTextF = new JTextField();
			hourTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			hourTextF.setText("HH:MM");
			hourTextF.setBounds(116, 54, 363, 26);
			hourTextF.setColumns(10);
		}
		return hourTextF;
	}
	
	public JTextField getDateTextF() {
		if(dateTextF == null) {
			dateTextF = new JTextField();
			dateTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			dateTextF.setText("DD-MM-YYYY");
			dateTextF.setBounds(116, 19, 363, 26);
			dateTextF.setColumns(10);
		}
		return dateTextF;
	}
	
	public JTextField getPlaceTextF() {
		if(placeTextF == null) {
			placeTextF = new JTextField();
			placeTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			placeTextF.setBounds(116, 90, 363, 26);
			placeTextF.setColumns(10);
		}
		return placeTextF;
	}
	
	public JTextArea getDescTextA() {
		if(descTextA == null) {
			descTextA = new JTextArea();
			descTextA.setFont(new Font("Tahoma", Font.PLAIN, 15));
			descTextA.setBounds(116, 134, 363, 94);
		}
		return descTextA;
	}
	
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("DODAJ");
			addButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			addButton.setBounds(191, 243, 115, 29);
		}
		return addButton;
	}
}
