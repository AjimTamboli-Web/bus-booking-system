package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.PassengerDAO;
import model.Passenger;

public class PassengerService {
	private BookingService bookingService;
	PassengerDAO dao	 = new PassengerDAO();
	
	List<Passenger> passenger = new ArrayList<>();
	BusService bus;
	
	public List<Passenger> getPassenger(){
		return passenger;
	}
	
	public PassengerService(BusService bus,BookingService bookingService) {
		this.bus = bus;
		this.bookingService = bookingService;
	}
	
	public void addPassenger(Passenger pass) {
		int id = dao.addPassenger(pass);
			pass.setPassengerId(id);
			passenger.add(pass);
			System.out.println("Passenger stored with ID: " + id);
	}
	
	public void loadPassengerfromDB() {
		passenger = dao.getAllPassengers();
	}

/*	
//	public void boardPassenger(Passenger p) {
//		if( bus.getCurrentStop().equals(p.getSourceStop())) {
//			p.setStatus("ON_BUS");
//		}
//		else {
//			System.out.println("Passenger is not boarding on bus.");
//		}
//	}
//	
//	public void dropPassenger(Passenger p) {
//		if(bus.getCurrentStop().equals(p.getDestinationStop())) {
//		p.setStatus("DROPPED");
//		passenger.remove(p);
//		}else {
//			System.out.println("Passenger can not be dropped.");
//		}
//	}
*/
	
	public void getPassengersInBus() {
		Iterator<Passenger> it = passenger.iterator();
		
		while(it.hasNext()) {
			Passenger p = it.next();
			System.out.println("Name: " + p.getName() + " " 
					+ "status: " + p.getStatus());
		}
	}
	
	public void handlePassengers() {
		Iterator<Passenger> it = passenger.iterator();
		int currentIndex = bus.getCurrentStopIndex();
		
		while(it.hasNext()) {
			Passenger p = it.next();
			
//			if(p.getBusId() == 0) {
//				p.setBusId(2);
//			}
//			if(bus.getCurrentStop().equals(p.getSourceStop()) && !p.getStatus().equalsIgnoreCase("ON_BUS")) {
//				p.setStatus("ON_BUS");  
//				System.out.println("Passenger " + p.getName() + " boarded....");
//			}
//			if(bus.getCurrentStop().equals(p.getDestinationStop()) && p.getStatus().equalsIgnoreCase("ON_BUS")){
//				p.setStatus("DROPPED");
//				it.remove();
//				System.out.println("Passenger " + p.getName() + " dropped...");
			
			// improve logic by accessing indexes
			int sourceIndex = bus.getStopIndexByName(p.getSourceStop());
			int destIndex = bus.getStopIndexByName(p.getDestinationStop()	);
			
			// handle invalid stops before proceed to board and drop.
						if(sourceIndex == -1 || destIndex == -1) {
						    System.out.println("Invalid stop for passenger: " + p.getName());
						    continue;
						}
				
			// Boarding Logic			
			if( currentIndex == sourceIndex  && p.getStatus().equalsIgnoreCase("CONFIRMED")) {
											//	below one is old
//											&& !p.getStatus().equalsIgnoreCase("ON_BUS")
//										    && !p.getStatus().equalsIgnoreCase("DROPPED")) {
				
				
				
//				int bookingID =  // true;   // already booked by thread that's why we comment below lines  
//					     bookingService.bookSeat(
//				        p.getBusId(),              // make sure Passenger has busId
//				        p.getPassengerId(),
//				        p.getDestinationStop(),
//				        bus.getCurrentStop()
//				);
				
				// boarding logic fetch from busService
//				if(bookingID != -1) {
				p.setStatus("ON_BUS");
				
//				p.setBookingId(bookingID);
				dao.updatePassengerStatus(p.getPassengerId(), "ON_BUS");
//				int bookingId = bookingService.bookSeat(sourceIndex, destIndex, p.getDestinationStop(), p.getSourceStop());
				
//				if(bookingId > 0	) {
//				p.setBookingId(bookingId); //  ❌ not correct but prevents crash
//				}
				
				System.out.println("Passenger " + p.getName() + " boarded....");
//				}else {
//					p.setStatus("WAITING");
//					System.out.println("No seats available for:: " + p.getName());
//				}
			}
			
			// Dropping Logic
			if(currentIndex >= destIndex && p.getStatus().equalsIgnoreCase("ON_BUS")){
				p.setStatus("DROPPED");
				
				dao.updatePassengerStatus(p.getPassengerId(), "DROPPED");
				
				if(p.getBookingId() > 0) {
				bookingService.cancelBooking(p.getBookingId(), p.getBusId());    // now decreasing the seats
				}
				System.out.println("Checking drop for: " + p.getName() +
					    " | Current: " + currentIndex +
					    " | Dest: " + destIndex +
					    " | Status: " + p.getStatus());
				
				it.remove();
				System.out.println("Passenger " + p.getName() + " dropped...");
			}
			
			
		}
	}
	
	public void viewAllPassengersFromDB() {
	    dao.getAllPassengersFromDB();
	}
}
