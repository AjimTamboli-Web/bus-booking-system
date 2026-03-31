package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Bus;

public class BusDAO{

		public void addBus(Bus bus) {
			try (Connection con = DBConnection.getConnection();
					PreparedStatement Task = con.prepareStatement("insert into buses values(?,?,?,?,?,?)");) {
				
				Task.setInt(1, bus.getBusId());
				Task.setString(2, bus.getBusName());
				Task.setString(3, bus.getSource());
				Task.setString(4, bus.getDestination());
				Task.setInt(5, bus.getTotalSeats());
				Task.setInt(6, bus.getAvailableSeats());

				Task.executeUpdate();

				System.out.println("Bus Added...!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void getAllBus() {
			try (Connection con_2 = DBConnection.getConnection();
					PreparedStatement Task_2 = con_2.prepareStatement("select * from buses");
					ResultSet rs = Task_2.executeQuery();  ) {

				while (rs.next()) {
					System.out.println(
							"[" + rs.getInt("bus_id") + " , " + rs.getString("bus_name") + " , " + rs.getString("source")
									+ " , " + rs.getString("destination") + " , " + rs.getInt("total_seats") + "]");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}


		// for BookingService 
		public Bus getBusById(int busId) {

		    Bus bus = null;

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement("select * from buses where bus_id = ?")) {

		        ps.setInt(1, busId);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            bus = new Bus();
		            bus.setBusId(rs.getInt("bus_id"));
		            bus.setBusName(rs.getString("bus_name"));
		            bus.setSource(rs.getString("source"));
		            bus.setDestination(rs.getString("destination"));
		            bus.setTotalSeats(rs.getInt("total_seats"));

		            // IMPORTANT: if you have availableSeats column
		            bus.setAvailableSeats(rs.getInt("available_seats"));
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return bus;
		}
		
		public void updateSeat(int busId, int seats) {

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(
		             "update buses set available_seats = ? where bus_id = 1")) {

		        ps.setInt(1, seats);
//		        ps.setInt(2, busId);

		        ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		public boolean decreaseSeat(int busId) {
		    try (Connection con = DBConnection.getConnection();){
		    	
		    		con.setAutoCommit(false); // start transaction
		    		
		         PreparedStatement ps = con.prepareStatement(
		        		 "SELECT available_seats FROM buses WHERE bus_id=? FOR UPDATE");

		        ps.setInt(1, busId);
		        
		        ResultSet rs = ps.executeQuery();
		        
		        if(rs.next()) {
		            int seats = rs.getInt("available_seats");
		            System.out.println("DB seats before update: " + seats);
		            if(seats <= 0) {
		            	 	System.out.println("❌ No seats in DB");
		                con.rollback();
		                return false; // no seats left
		            }
		           
		            
		            // Decrement seat safely
		            PreparedStatement ps2 = con.prepareStatement(
		                "UPDATE buses SET available_seats=available_seats-1 WHERE bus_id=?");
		            ps2.setInt(1, busId);
		            int rows = ps2.executeUpdate(); 
		            if (rows > 0) {
		                System.out.println("✅ Seat decreased in DB");
		                con.commit();
		                return true;
		            } else {
		                System.out.println("❌ Update failed (no rows)");
		                con.rollback();
		                return false;
		            }
		        } else {
		        	 System.out.println("❌ Bus not found in DB");
		             con.rollback();
		             return false;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		public void increaseSeat(int busId) {
		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(
		             "update buses set available_seats = available_seats + 1 where bus_id = ?")) {

		        ps.setInt(1, busId);
		        ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		public int getAvailableSeats(int busId) {
		    int seats = 0;

		    try (Connection con = DBConnection.getConnection()) {

		        PreparedStatement ps = con.prepareStatement(
		                "SELECT available_seats FROM buses WHERE bus_id=?");
		        ps.setInt(1, busId);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            seats = rs.getInt("available_seats");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return seats;
		}
		
		public void updateSeats(int seats) {
		    String sql = "UPDATE buses SET available_seats = ? WHERE bus_id = 1";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, seats);
		        ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
	}

