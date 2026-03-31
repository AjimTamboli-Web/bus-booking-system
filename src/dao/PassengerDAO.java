package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Passenger;


public class PassengerDAO {
	
	
	public int addPassenger(Passenger passe) {	
		
	String sql = "insert into passengers(name,email,phone,status,source_stop,destination_stop,bus_id,booking_id) values(?,?,?,?,?,?,?,?)";	
		
	 try (Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);)
	 {
		stmt.setString(1,passe.getName());
		stmt.setString(2, passe.getEmail());
		stmt.setString(3,passe.getPhone());
		stmt.setString(4, passe.getStatus());
		stmt.setString(5, passe.getSourceStop());
		stmt.setString(6, passe.getDestinationStop());
		stmt.setInt(7, passe.getBusId());      
		stmt.setInt(8, passe.getBookingId()); 
		
		 stmt.executeUpdate();
		 
		 // 🔥 GET GENERATED ID
	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            int id = rs.getInt(1);
	            System.out.println("Generated Passenger ID: " + id);
	            return id;
	        }
		
		
	  }
	  catch (SQLException e) {
		 e.printStackTrace();
	  }
	 return -1;
	}
	
	public List<Passenger> getAllPassengers() {
		List<Passenger> list = new ArrayList<>();
		
//		String sql = "select * from passengers;";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM passengers");
				ResultSet rs = stmt.executeQuery();   )
		{
			
		  while(rs.next()) {
			  Passenger p = new Passenger();
			  
			    p.setPassengerId(rs.getInt("passenger_id"));
	            p.setName(rs.getString("name"));
	            p.setEmail(rs.getString("email"));
	            p.setPhone(rs.getString("phone"));
	            p.setStatus(rs.getString("status"));
	            p.setSourceStop(rs.getString("source_stop"));
	            p.setDestinationStop(rs.getString("destination_stop"));
	            p.setBusId(rs.getInt("bus_id"));
	            p.setBookingId(rs.getInt("booking_id"));
	            
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
			
			int rows = stmt.executeUpdate();
			System.out.println("Rows updated: " + rows + " for ID: " + id);
			
			
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
	
	public void updateBookingId(int passengerId, int bookingId) {
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(
	             "UPDATE passengers SET booking_id=? WHERE passenger_id=?")) {

	        ps.setInt(1, bookingId);
	        ps.setInt(2, passengerId);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
