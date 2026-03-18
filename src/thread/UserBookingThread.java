package thread;

import model.Passenger;
import service.BookingService;

public class UserBookingThread extends Thread{
	

	 private BookingService bookingService;
	 private  Passenger passenger;
	 
	 public UserBookingThread(Passenger passenger,
		BookingService bookingService) {
		this.passenger = passenger;
		this.bookingService = bookingService;
	 }
	
//	 @Override
//	    public void run() {
//	        boolean booked = bookingService.bookSeat(busId, passengerId, destination, currentStop);
//	        if (booked) {
//	            System.out.println("Passenger " + passengerId + " successfully booked a seat to " + destination);
//	        } else {
//	            System.out.println("Passenger " + passengerId + " failed to book a seat to " + destination);
//	        }
//	    }
	
	 // old ways to try passing db data direct
//	 @Override   
//	 public void run() {
//	     synchronized (bookingService) {  // synchronize on the service or shared Bus object
//	         int bookedId = bookingService.bookSeat(busId, passengerId, destination, currentStop);
//	         if (bookedId > 0) {
//	        	 	passenger.setBookingId(bookedId);
//	        	    passenger.setStatus("CONFIRMED");	
//	             System.out.println("Passenger " + passengerId + " successfully booked a seat to " + destination);
//	         } else {
//	             System.out.println("Passenger " + passengerId + " failed to book a seat to " + destination);
//	         }
//	     }
//	 }
//}
	 
	 @Override
	 public void run() {
		 int bookingId = bookingService.bookSeat(
		            passenger.getBusId(),
		            passenger.getPassengerId(),
		            passenger.getDestinationStop(),
		            passenger.getSourceStop()
		    );
		 
		 if (bookingId > 0) {
		        passenger.setBookingId(bookingId);
		        passenger.setStatus("CONFIRMED");

		        System.out.println("Passenger " + passenger.getPassengerId() +
		                " successfully booked a seat to " + passenger.getDestinationStop());
		    } else {
		        System.out.println("Passenger " + passenger.getPassengerId() +
		                " failed to book a seat to " + passenger.getDestinationStop());
		    }
		}
	 }
	

//package thread;
//
//import service.BusService;
//
//public class UserBookingThread extends Thread {
//
//    private int passengerId;
//    private String destination;
//    private BusService bus;
//
//    public UserBookingThread(int passengerId, String destination, BusService bus) {
//        this.passengerId = passengerId;
//        this.destination = destination;
//        this.bus = bus;
//    }
//
//    @Override
//    public void run() {
//        synchronized (bus) { // synchronize on shared bus object
//            if (bus.bookSeat()) {
//                System.out.println("Passenger " + passengerId + " booked a seat to " + destination + " at stop " + bus.getCurrentStop());
//            } else {
//                System.out.println("Passenger " + passengerId + " failed to book a seat to " + destination + " at stop " + bus.getCurrentStop());
//            }
//        }
//    }
//}



