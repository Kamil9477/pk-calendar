package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import model.EventTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Okno wy�wietlaj�ce wszystkie wydarze� w formie tabeli
 *
 */
public class EventsTableFrame extends JFrame {
	
	private EventTableModel evTableModel;
	private JPanel contentPane;
	private JTable table;
	private JButton removeButton;
	private JTextField dateTextF;
	private JTextField hourTextF;
	private JTextField placeTextF;
	private JTextField descTextF;
	private JButton filterButton;
	private JButton delFilterButton;
	private JButton exportToXML;
	private JButton exportToICal;
	
	/**
	 * ustawia wygl�d okna 
	 * @param evTableModel model tabeli do wy�wietlenia
	 */
	public EventsTableFrame(EventTableModel evTableModel) {
		this.evTableModel = evTableModel;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1000, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 960, 249);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(getTable());
		
		//przycisk do usuwania
		contentPane.add(getRemoveButton());
		
		//etykietki tekstowe
		JLabel dateLabel = new JLabel("Data:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateLabel.setBounds(240, 300, 69, 20);
		contentPane.add(dateLabel);
		
		JLabel hourlabel = new JLabel("Godzina:");
		hourlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hourlabel.setBounds(240, 336, 86, 20);
		contentPane.add(hourlabel);
		
		JLabel placeLabel = new JLabel("Miejsce:");
		placeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		placeLabel.setBounds(502, 300, 69, 20);
		contentPane.add(placeLabel);
		
		JLabel descLabel = new JLabel("Opis:");
		descLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		descLabel.setBounds(502, 336, 69, 20);
		contentPane.add(descLabel);
		
		//pola tekstowe na filtry
		contentPane.add(getDateTextF());
		contentPane.add(getHourTextF());
		contentPane.add(getPlaceTextF());
		contentPane.add(getDescTextF());
		contentPane.add(getFilterButton());
		contentPane.add(getDelFilterButton());
		contentPane.add(getExportToXML());
		
		
		contentPane.add(getExportToICal());
	}

	/**
	 * metoda kt�ra czy�ci pola tekstowe filtr�w
	 */
	public void clearFields() {
		dateTextF.setText("");
		hourTextF.setText("");
		placeTextF.setText("");
		descTextF.setText("");
	}
//----------------------------------------------------------------------------------------
	public JTable getTable(){
		if(table == null){
			table = new JTable();
			table.setModel(evTableModel);
			table.getColumnModel().getColumn(2).setPreferredWidth(300);
			table.getColumnModel().getColumn(3).setPreferredWidth(600);
			table.getTableHeader().setResizingAllowed(false);
			table.getTableHeader().setReorderingAllowed(false);
		}
		return table;
	}
	
	public JButton getRemoveButton() {
		if(removeButton == null){
			removeButton = new JButton("Usu\u0144 wybrane");
			removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			removeButton.setBounds(15, 281, 199, 29);
		}
		return removeButton;
	}
	
	public JTextField getDateTextF() {
		if(dateTextF == null){
			dateTextF = new JTextField();
			dateTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			dateTextF.setBounds(341, 300, 140, 20);
			dateTextF.setColumns(10);
		}
		return dateTextF;
	}
	
	public JTextField getHourTextF() {
		if(hourTextF == null){
			hourTextF = new JTextField();
			hourTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			hourTextF.setBounds(341, 336, 140, 20);
			hourTextF.setColumns(10);
		}
		return hourTextF;
	}
	
	public JTextField getPlaceTextF() {
		if (placeTextF == null) {
			placeTextF = new JTextField();
			placeTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			placeTextF.setBounds(596, 300, 185, 20);
			placeTextF.setColumns(10);
		}
		return placeTextF;
	}
	
	public JTextField getDescTextF() {
		if (descTextF == null) {
			descTextF = new JTextField();
			descTextF.setFont(new Font("Tahoma", Font.PLAIN, 18));
			descTextF.setBounds(596, 335, 185, 22);
			descTextF.setColumns(10);
		}
		return descTextF;
	}
	
	public JButton getFilterButton() {
		if (filterButton == null) {
			filterButton = new JButton("Filtruj");
			filterButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			filterButton.setBounds(848, 296, 127, 29);
		}
		return filterButton;
	}
	
	public JButton getDelFilterButton() {
		if (delFilterButton == null) {
			delFilterButton = new JButton("Usu\u0144 filtry");
			delFilterButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			delFilterButton.setBounds(848, 332, 127, 29);
		}
		return delFilterButton;
	}
	
	public JButton getExportToXML() {
		if (exportToXML == null) {
			exportToXML = new JButton("Eksportuj wybrane XML");
			exportToXML.setFont(new Font("Tahoma", Font.PLAIN, 16));
			exportToXML.setBounds(15, 314, 199, 29);
		}
		return exportToXML;
	}
	
	public JButton getExportToICal() {
		if(exportToICal == null){
			exportToICal = new JButton("Eksportuj wybrane iCal");
			exportToICal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			exportToICal.setBounds(15, 348, 199, 29);
		}
		return exportToICal;
	}
}
