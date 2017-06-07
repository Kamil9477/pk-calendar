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
/**
 * Okno wyœwitlaj¹ce informacje o wydarzeniach w danym dniu
 *
 */
public class EventInfo extends JDialog {
	public EventInfo(String data, int day, int month, int year) {
		setResizable(false);
		setBounds(100, 100, 440, 300);
		setTitle("Informacje o wydarzeniach");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 50, 398, 188);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea(data);
		scrollPane.setViewportView(textArea);
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
		
		JLabel dateLabel = new JLabel();
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setBounds(126, 10, 170, 26);
		dateLabel.setText(Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		getContentPane().add(dateLabel);
	}
}
