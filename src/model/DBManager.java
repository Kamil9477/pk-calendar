package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Klasa odpowiedzialna za komunikacjê z baz¹ danych,
 * przy ka¿dym odczycie/zapisie tworzymy nowe po³¹czenie i je zamykamy
 */
public class DBManager {
	
	/**
	 * zwraca liste wydarzen które znajduj¹ siê w bazie danych
	 * @return lista wydarzen
	 */
	public List<Event> getEventsFromDB() {
		List<Event> events = new ArrayList<Event>();
		String query = "SELECT * FROM events";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
        try {
        	con = connectToDB();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
           
            while (rs.next()) {
			    events.add(new Event(rs.getString("DATE"), rs.getString("HOUR"), 
			    		rs.getString("PLACE"), rs.getString("DESCRIPTION")));
			}
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				stm.close();
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
        }
		return events;
	}
	
	/**
	 * dodaje wydarzenie do bazy
	 * @param ev wydarzenie
	 */
	public void addEventToDB(Event ev) {
		String query = "INSERT INTO events (DATE,HOUR,PLACE,DESCRIPTION) VALUES(" +
					   "'" + ev.getDate()  + "', " +
					   "'" + ev.getHour()  + "', " +
					   "'" + ev.getPlace() + "',"  +
					   "'" + ev.getDesc()  + "');";
		
		Connection con = null;
		Statement stm = null;
		
		try {
        	con = connectToDB();
            stm = con.createStatement();
            stm.executeUpdate(query);
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, "B³¹d przy dodawaniu wydarzenia do bazy danych ");  
            e.printStackTrace();
        } finally {
        	try {
				stm.close();
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
        }
		
	}
	
	/**
	 * nawi¹zuje po³¹czenie z baz¹ danych
	 * @return con po³¹czenie z baz¹ danych
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private Connection connectToDB() throws ClassNotFoundException, SQLException {
		Connection con = null;
        Class.forName("org.sqlite.JDBC");           
        con = DriverManager.getConnection("jdbc:sqlite:database.db");
        return con;
	}
	
	/**
	 * metoda usuwa podane w parametrze wydarzenie z bazy danych
	 * @param ev wydarzenie
	 */
	public void removeEventFromDB(Event ev) {
		String query = "DELETE FROM events WHERE " +
					   "DATE = " + "'" + ev.getDate() + "'" +
					   "AND HOUR = " + "'" + ev.getHour() + "';";
		Connection con = null;
		Statement stm = null;
		
		try {
        	con = connectToDB();
            stm = con.createStatement();
            stm.executeUpdate(query);
            System.out.println("Polecenie wykonane");
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "B³¹d przy usuwaniu wydarzenia z bazy danych");
        } finally {
        	try {
				stm.close();
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * metoda zwraca liste wydarzeñ które pasuj¹ do podanych w parametrze filtrów
	 * @param fields pola po których filtrujemy
	 * @param filters filtry
	 * @return events lista wydarzeñ
	 */
	public List<Event> getFilteredEvents(List<String> fields, List<String> filters) {
		List<Event> events = new ArrayList<Event>();
		String query = "SELECT * FROM events WHERE";
		for(int i=0; i<fields.size(); i++){
			if(i>0) {
				query += " AND " + fields.get(i) + " LIKE '" + filters.get(i) + "%'";
			} else {
				query += " " + fields.get(i) + " LIKE '" + filters.get(i) + "%'";
			}
		}
		
		query += ";";
		
		System.out.println(query);
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
        	con = connectToDB();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
           
            while (rs.next()) {
			    events.add(new Event(rs.getString("DATE"), rs.getString("HOUR"), 
			    		rs.getString("PLACE"), rs.getString("DESCRIPTION")));
			}
            
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, "B³¹d przy filtrowaniu wydarzeñ");
        	e.printStackTrace();
        } finally {
        	try {
				stm.close();
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
        }
		
		return events;
	}
}
