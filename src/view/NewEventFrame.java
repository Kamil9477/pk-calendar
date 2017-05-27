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

	public NewEventFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 509, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dateLabel = new JLabel("Data:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateLabel.setBounds(15, 16, 76, 31);
		contentPane.add(dateLabel);
		
		JLabel hourLabel = new JLabel("Godzina:");
		hourLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hourLabel.setBounds(15, 56, 86, 20);
		contentPane.add(hourLabel);
		
		JLabel placeLabel = new JLabel("Miejsce:");
		placeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		placeLabel.setBounds(15, 92, 86, 20);
		contentPane.add(placeLabel);
		
		JLabel descLabel = new JLabel("Opis:");
		descLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descLabel.setBounds(15, 128, 69, 20);
		contentPane.add(descLabel);
		
		dateTextF = new JTextField();
		dateTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateTextF.setText("DD-MM-YYYY");
		dateTextF.setBounds(116, 19, 363, 26);
		contentPane.add(dateTextF);
		dateTextF.setColumns(10);
		
		hourTextF = new JTextField();
		hourTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hourTextF.setText("HH:MM");
		hourTextF.setBounds(116, 54, 363, 26);
		contentPane.add(hourTextF);
		hourTextF.setColumns(10);
		
		placeTextF = new JTextField();
		placeTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		placeTextF.setBounds(116, 90, 363, 26);
		contentPane.add(placeTextF);
		placeTextF.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(116, 134, 363, 94);
		contentPane.add(textArea);
		
		JButton add = new JButton("DODAJ");
		add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add.setBounds(191, 243, 115, 29);
		contentPane.add(add);
		
		
	}
}
