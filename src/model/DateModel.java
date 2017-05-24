package model;

public class DateModel {
	private int month;
	private int year;
	private int monthDays;
	
	public DateModel() {
		//TODO zmienic to
		month = 5;
		year = 2017;
		calculateMonthDays();
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int newVal) {
		if(newVal == 13) {
			month = 1;
			year++;
		} else if(newVal == 0){
			month = 12;
			year--;
		} else {
			month = newVal;
		}
		calculateMonthDays();
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int newVal) {
		if(newVal >= 1900 && newVal <=2099) {
			year = newVal;
			calculateMonthDays();
		}	
	}
	
	public void calculateMonthDays() {
		if(month == 2) {
			monthDays = 28;
		} else if(month == 4 || month == 6 || month == 9 || month == 11 ) {
			monthDays = 30;
		} else {
			monthDays = 31;
		}
	}
	
	public int getMonthDays() {
		return monthDays;
	}
	
	//algorytm wyznaczania pierwszego dnia miesi¹ca
	public int getFirstDay() {
		int day = 1;
		int startYear = 1900;
		int startMonth = 0;
		int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		while(!(startYear == year && startMonth == month-1)) {
			//jeœli jest rok przestêpny to dodajemy 29 dni
			//a jeœli nie to dodajemy dni wed³ug miesi¹ca
			if(startMonth == 1 && leapYear()) {
				day += 29;
			} else {
				day += monthDays[startMonth];
			}
			
			//zwiêkszamy miesi¹c
			startMonth++;
			//jeœli przeszliœmy ca³y rok to zwiêkszamy rok
			if(startMonth == 12) {
				startYear++;
				startMonth=0;
			}
			
			day = day%7;
		}
		return day;
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
