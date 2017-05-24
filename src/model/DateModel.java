package model;

public class DateModel {
	private int month;
	private int year;
	private int monthDays; //przechowuje iloœæ dni w miesi¹cu
	
	public DateModel() {
		//TODO zmienic to
		month = 5;
		year = 2017;
		
		if(month == 2) {
			monthDays = 28;
		} else if(month == 4 || month == 6 || month == 9 || month == 11 ) {
			monthDays = 30;
		} else {
			monthDays = 31;
		}
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	private boolean leapYear() {		
		if((year%4==0 && year%100!=0) || year%400==0) {
			return true;
		}
		return false;
	}
	
	public void print() {
		System.out.println("Rok " + year + " miesiac " + month);
	}
}
