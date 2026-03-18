package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import model.Booking;

public class BookingDAO {

	 public int createBooking(Booking booking) {
		String sql ="insert into bookings(passenger_id,bus_id,destination,booking_time,bus_stop_when_booked,status)values(?,?,?,?,?,?)"	;
		try (Connection con = DBConnection.getConnection();
			  PreparedStatement pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				)
		{

				pre.setInt(1, booking.getPassengerId());
				pre.setInt(2, booking.getBusId());
				pre.setString(3, booking.getDestination());
				pre.setTimestamp(4, Timestamp.valueOf(booking.getBookingTime()));
				pre.setString(5, booking.getBusStopWhenBooked());
				pre.setString(6, booking.getStatus());
				
				

			int	rows = pre.executeUpdate();
				System.out.println("Rows Affected ✅ : " + rows);
				
				ResultSet rs = pre.getGeneratedKeys();
				if (rs.next()) {
				    return rs.getInt(1);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return -1;
		}

		public void cancelBooking(int booking_id) {

			try (Connection con_1 = DBConnection.getConnection();
					PreparedStatement pre_1 = con_1
						.prepareStatement("update bookings set status='CANCELLED' where booking_id=?");) {
				
				pre_1.setInt(1, booking_id);

				int rows = pre_1.executeUpdate();
				System.out.println("rows affected.." + rows);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	
		}

		public void getBookingByPassenger(int passenger_id) {

			try (Connection con_2 = DBConnection.getConnection();
					PreparedStatement pres = con_2.prepareStatement("select * from bookings where passenger_id=?");) {

				
				pres.setInt(1, passenger_id);

				try( ResultSet rs = pres.executeQuery();){
						System.out.println("Booking for passenger: " + passenger_id);
				while (rs.next()) {
					System.out.println(rs.getInt("booking_id") + " " + rs.getInt("passenger_id") + " " +
							 rs.getInt("bus_id") + " " + rs.getString("destination") + " "
							+ rs.getTimestamp("booking_time") + " " + rs.getString("bus_stop_when_booked") + " "
							+ rs.getString("status"));

					System.out.println("Fetching Booking for The Passenger:  "+ passenger_id);
				}
			 }
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		
		public boolean isAlreadyBooked(int passengerId, int busId) {
		    String sql = "SELECT * FROM bookings WHERE passenger_id=? AND bus_id=? AND status='CONFIRMED'";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, passengerId);
		        ps.setInt(2, busId);

		        ResultSet rs = ps.executeQuery();

		        return rs.next(); // true if already booked

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return false;
		}
	}

