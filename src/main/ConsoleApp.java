package main;

	import java.util.Scanner;

	import model.Passenger;
	import service.BookingService;
	import service.BusService;
	import service.PassengerService;
	import thread.UserBookingThread;

	public class ConsoleApp {

	    public static void main(String[] args) throws InterruptedException {

	        Scanner sc = new Scanner(System.in);

	        BusService busService = new BusService();
	        BookingService bookingService = new BookingService();
	        PassengerService passengerService = new PassengerService(busService, bookingService);

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
	                    System.out.print("Enter Passenger ID: ");
	                    int id = sc.nextInt();
	                    sc.nextLine();

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

	                    Passenger p = new Passenger(id, name, email, phone,
	                            "WAITING", source, dest, 2, 0);

	                    passengerService.addPassenger(p);
	                    System.out.println("✅ Passenger Added");
	                    break;

	                case 2:
	                    System.out.println("Booking seats for all passengers...");

	                    for (Passenger passenger : passengerService.getPassenger()) {

	                        UserBookingThread t = new UserBookingThread(passenger, bookingService);
	                        t.start();
	                        t.join(); // wait for each booking
	                    }

	                    System.out.println("✅ Booking Completed");
	                    break;

	                case 3:
	                    System.out.println("\n🚍 Starting Bus Journey...\n");

	                    while (!busService.isLastStop()) {

	                        System.out.println("Current Stop: " + busService.getCurrentStop());

	                        passengerService.handlePassengers();
//	                        passengerService.getPassengersInBus();     old  showing only in-memory list
	                        passengerService.viewAllPassengersFromDB();  // new  fetching in DB

	                        Thread.sleep(2000);
	                        busService.moveToNextStop();
	                    }

	                    System.out.println("🏁 Journey Completed");
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


