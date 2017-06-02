package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Font;

public class EventInfo extends JDialog {
	public EventInfo(String data) {
		setBounds(100, 100, 450, 300);
		setTitle("Wydarzenia z dnia..");
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea(data);
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setBounds(15, 16, 398, 212);
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        
        JScrollPane jScrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
		getContentPane().add(textArea);
	}
}
