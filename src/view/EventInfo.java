package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class EventInfo extends JDialog {
	public EventInfo(String data) {
		setBounds(100, 100, 450, 300);
		setTitle("Wydarzenia z dnia..");
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea(data);
		textArea.setBounds(15, 16, 398, 212);
		getContentPane().add(textArea);
		
	}
}
