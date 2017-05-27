package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CalendarFrame;
import view.NewEventFrame;
import model.DateModel;

public class DateController {
	private DateModel model;
	private CalendarFrame view;
	
	public DateController(){};
	public DateController(DateModel model, CalendarFrame view) {
		this.model = model;
		this.view = view;
		
		//ustawienie listenerów
		view.getPrYear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decYear();
			}
		});
		
		view.getNextYear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incYear();
			}
		});
		
		view.getPrMonth().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decMonth();
			}
		});
		
		view.getNextMonth().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incMonth();
			}
		});
		
		view.getAddEvent().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewEventFrame().setVisible(true);
			}
		});
	}
	
	public void decYear() {
		model.setYear(model.getYear() - 1);
		view.updateView();
	}
	
	public void incYear() {
		model.setYear(model.getYear() + 1);
		view.updateView();
	}
	
	public void decMonth() {
		model.setMonth(model.getMonth() - 1);
		view.updateView();
	}
	
	public void incMonth() {
		model.setMonth(model.getMonth() + 1);
		view.updateView();
	}
}
