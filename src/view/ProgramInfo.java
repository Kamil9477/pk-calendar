package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;

/**
 * Okienko wyœwietlaj¹ce informacje "O programie"
 *
 */
public class ProgramInfo extends JDialog {

	public ProgramInfo() {
		setResizable(false);
		setBounds(100, 100, 440, 219);
		setTitle("O programie");
		getContentPane().setLayout(null);
		
		JLabel authorsLbl = new JLabel("Autorzy:");
		authorsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		authorsLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorsLbl.setBounds(15, 77, 404, 20);
		getContentPane().add(authorsLbl);
		
		JLabel krLbl = new JLabel("Kamil Recheta 205310");
		krLbl.setHorizontalAlignment(SwingConstants.CENTER);
		krLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		krLbl.setBounds(15, 103, 404, 20);
		getContentPane().add(krLbl);
		
		JLabel lblNewLabel = new JLabel("Dominik Dominiak 204193");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(15, 127, 404, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ORGANIZER");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(15, 29, 404, 20);
		getContentPane().add(lblNewLabel_1);
	}
}
