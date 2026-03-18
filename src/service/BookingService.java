package service;

import java.time.LocalDateTime;


import dao.BookingDAO;
import dao.BusDAO;
import model.Booking;
import model.Bus;

public class BookingService {

	 private BusDAO busDAO = new BusDAO();
	 private BookingDAO bookingDAO = new BookingDAO();
	
	 public synchronized int bookSeat(int busId, int passengerId, String destination, String currentStop) {

		    Bus bus = busDAO.getBusById(busId);

		    if (bus == null) {
		        System.out.println("Bus not found!");
		        return -1;
		    }
//		    
		    if (bookingDAO.isAlreadyBooked(passengerId, busId)) {
		        System.out.println("Passenger " + passengerId + " already booked!");
		        return -1;
		    }
//		    
		    boolean seatBooked = busDAO.decreaseSeat(busId);
		    if (!seatBooked) {
		        System.out.println("No seats Available....");
		        return -1;
		    }
		    
//		    if (bus.getAvailableSeats() <= 0) {
//		        System.out.println("No seats Available....");
//		        return false;
//		    }
		    
		   //  busDAO.decreaseSeat(busId);
		 // decrement seat in memory
		   // bus.setAvailableSeats(bus.getAvailableSeats() - 1);
		    
//		    boolean seatBooked = busDAO.decreaseSeat(busId);
//		    
//		    if (seatBooked) {

		        // This avoids race condition at DB level
//		    			busDAO.decreaseSeat(busId);
		    			
		        // create booking
		        Booking booking = new Booking();
		        booking.setPassengerId(passengerId);
		        booking.setBusId(busId);
		        booking.setDestination(destination);
		        booking.setBookingTime(LocalDateTime.now());
		        booking.setBusStopWhenBooked(currentStop);
		        
		        booking.setStatus("CONFIRMED");

		        int bookingId = bookingDAO.createBooking(booking);

		        return bookingId;
//		    }
//		    System.out.println("No seats Available....");
//		    return false;
		}
	 
	 
	 public void cancelBooking(int bookingId, int busId) {

		    bookingDAO.cancelBooking(bookingId);

		    
		    busDAO.increaseSeat(busId);
//		    Bus bus = busDAO.getBusById(busId);
//		    
//		    if(bus != null) {
//		    busDAO.updateSeat(busId, bus.getAvailableSeats() + 1);
//		    }
		}
	 
	 
	 public void getBookingsByPassenger(int passengerId) {
	        bookingDAO.getBookingByPassenger(passengerId);
	    }
	
}
