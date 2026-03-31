package main;

	import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.BusDAO;
import model.Bus;
import model.Passenger;
	import service.BookingService;
	import service.BusService;
	import service.PassengerService;
	import thread.UserBookingThread;

	public class ConsoleApp {

	    public static void main(String[] args) throws InterruptedException {

	        Scanner sc = new Scanner(System.in);
	       BusDAO busDAO = new BusDAO();
	        if (busDAO.getBusById(1) == null) {
	            busDAO.addBus(new Bus(1, "Pune Express", "Pune Station", "Katraj", 30, 30));
	        }
	        
	        BusService busService = new BusService();
	        BookingService bookingService = new BookingService();
	        PassengerService passengerService = new PassengerService(busService, bookingService);
//	        PassengerDAO passengerDAO = new PassengerDAO();
	        
	        
	        while (true) {

	            System.out.println("\n===== BUS BOOKING SYSTEM =====");
	            System.out.println("1. Add Passenger");
	            System.out.println("2. Book Seat");
	            System.out.println("3. Start Bus Journey");
	            System.out.println("4. View Passengers");
	            System.out.println("5. Exit");

	            System.out.print("Enter choice: ");
	            int choice = sc.nextInt();
	            sc.nextLine(); // clear buffer

	            switch (choice) {

	                case 1:
//	                    System.out.print("Enter Passenger ID: ");
//	                    int id = passengerDAO.addPassenger(passenger2);
//	                    sc.nextLine();

	                    System.out.print("Enter Name: ");
	                    String name = sc.nextLine();

	                    System.out.print("Enter Email: ");
	                    String email = sc.nextLine();

	                    System.out.print("Enter Phone: ");
	                    String phone = sc.nextLine();

	                    System.out.print("Enter Source Stop: ");
	                    String source = sc.nextLine();

	                    System.out.print("Enter Destination Stop: ");
	                    String dest = sc.nextLine();

	                    Passenger p = new Passenger(0, name, email, phone,
	                            "WAITING", source, dest, 1, 0);
	                    
//	                    int id = passengerDAO.addPassenger(p);
//	                    p.setPassengerId(id);
	                    
	                    passengerService.addPassenger(p);
	                    System.out.println("✅ Passenger Added with ID: " );
	                    break;

	                case 2:
	                	List<UserBookingThread> threads = new ArrayList<>();
	                    System.out.println("Booking seats for all passengers...");
	                    passengerService.loadPassengerfromDB();
	                    
	                    
	                    for (Passenger passenger : passengerService.getPassenger()) {

	                    	if (passenger.getStatus().equalsIgnoreCase("WAITING")) {
	                    	
	                        UserBookingThread t = new UserBookingThread(passenger, bookingService);
	                        threads.add(t);  
	                        t.start();
	                        
	                     }
	                    }
	                 // wait for all threads
	                    for (UserBookingThread t : threads) {
	                        t.join();
	                    }
	                    System.out.println("✅ Booking Completed");
	                    break;

	                case 3:
	                    System.out.println("\n🚍 Starting Bus Journey...\n");

	                        passengerService.loadPassengerfromDB();
	                    while (!busService.isLastStop()) {

	                        System.out.println("Current Stop: " + busService.getCurrentStop());
	                        passengerService.handlePassengers();
//	                        passengerService.getPassengersInBus();     old  showing only in-memory list
	                        passengerService.viewAllPassengersFromDB();  // new  fetching in DB

	                        Thread.sleep(2000);
	                        busService.moveToNextStop();
	                    }

	                    System.out.println("🏁 Journey Completed");
	                    busService.resetSeats();
	                    break;

	                case 4:
//	                    passengerService.getPassengersInBus();  // old empty list 
	                	   passengerService.viewAllPassengersFromDB();// new from DB
	                    break;

	                case 5:
	                    System.out.println("Exiting...");
	                    sc.close();
	                    return;

	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	    }
	}


