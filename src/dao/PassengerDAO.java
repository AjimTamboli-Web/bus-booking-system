package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Passenger;


public class PassengerDAO {
	
	
	public void addPassenger(Passenger passe) {	
		
		
	 String sql = "insert into passengers(name,email,phone,status,source_stop,destination_stop) values(?,?,?,?,?,?);";
	
	 try (Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);)
	 {
		stmt.setString(1,passe.getName());
		stmt.setString(2, passe.getEmail());
		stmt.setString(3,passe.getPhone());
		stmt.setString(4, passe.getStatus());
		stmt.setString(5, passe.getSourceStop());
		stmt.setString(6, passe.getDestinationStop());
		
		 stmt.executeUpdate();
		
		
	  }
	  catch (SQLException e) {
		 e.printStackTrace();
	  }
	}
	
	public List<Passenger> getAllPassengers() {
		List<Passenger> list = new ArrayList<>();
		
		String sql = "select * from passengers;";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();   )
		{
			
		  while(rs.next()) {
			  Passenger p = new Passenger(
					  			rs.getInt("passenger_id"),
		                        rs.getString("name"),
		    		                rs.getString("email"),
				  			    rs.getString("phone"),
		    		                rs.getString("status"),
				  			    rs.getString("source_stop"),
		    		                rs.getString("destination_stop"));
			  list.add(p);
			  
		   }
			
		}
		catch(Exception ex) {
			System.out.println("Cannot display.." + ex.getMessage());
		}
		return list;
	}
	
	public void updatePassengerStatus(int id,String status) {
		String sql = "update passengers set status=? where passenger_id=?";
		try(Connection con = DBConnection.getConnection();
		        PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setString(1, status);
			stmt.setInt(2, id);
			
			stmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void getAllPassengersFromDB() {
	    String sql = "SELECT * FROM passengers";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        System.out.println("\n--- All Passengers ---");

	        while (rs.next()) {
	            System.out.println(
	                rs.getInt("passenger_id") + " | " +
	                rs.getString("name") + " | " +
	                rs.getString("status")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
