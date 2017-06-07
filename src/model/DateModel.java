package model;

/**
 * Klasa odpowiedzialna za przechowywanie obecnego miesi¹ca i roku,
 * z niej generuje siê g³ówny widok kalendarza
 *
 */
public class DateModel {
	private int month;
	private int year;
	private int monthDays;
	
	public DateModel() {
		//TODO poprawiæ
		month = 6;
		year = 2017;
		calculateMonthDays();
	}
	
	/**
	 * zwraca miesi¹c
	 * @return miesi¹c
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * zmienia miesi¹æ na podany w parametrze
	 * @param newVal nowy miesi¹c
	 */
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
	
	/**
	 * zwraca rok
	 * @return rok
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * zmienia rok na podany w parametrze
	 * @param newVal nowy rok
	 */
	public void setYear(int newVal) {
		if(newVal >= 1900 && newVal <=2099) {
			year = newVal;
			calculateMonthDays();
		}	
	}
	
	/**
	 * metoda oblicza ile dni ma dany miesi¹c
	 */
	public void calculateMonthDays() {
		if(month == 2) {
			monthDays = 28;
		} else if(month == 4 || month == 6 || month == 9 || month == 11 ) {
			monthDays = 30;
		} else {
			monthDays = 31;
		}
	}
	
	/**
	 * zwraca liczbe dni w miesiacu
	 * @return liczba dni
	 */
	public int getMonthDays() {
		return monthDays;
	}
	
	/**
	 * metoda zwraca którego dnia zaczyna siê obecny miesi¹c
	 * @return dzieñ w którym zaczyna siê miesi¹c
	 */
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
	
	/**
	 * sprawdza czy rok jest przestêpny
	 * @return true jeœli tak
	 */
	private boolean leapYear() {		
		if((year%4==0 && year%100!=0) || year%400==0) {
			return true;
		}
		return false;
	}	
}
