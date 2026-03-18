package main;


import java.sql.SQLException;


import model.Passenger;
import service.BookingService;
import service.BusService;
import service.PassengerService;
import thread.UserBookingThread;


public class MainApp {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		
//		 for BusMovementThread
//		BusService bus = new BusService();
//		BusMovementThread thread = new BusMovementThread(bus);
//		thread.start();
		

		
		
		
		
		
		
		
		
		
//		Connection con = DBConnection.getConnection();
		// for DBConnection
//		if(con != null) {
//			System.out.println("Connection success...");
//		}
//		else {
//			System.out.println("Connection failed...");
//		}
		 
		// for class PassengerDAO in addPassenger() method
//		PreparedStatement stmt = con.prepareStatement("insert into passengers values(?,?,?,?,?);");
//		stmt.setInt(1, 1);
//		stmt.setString(2,"Deamon Targaryen");
//		stmt.setString(3, "Demo@hotd");
//		stmt.setInt(4,54652344);
//		stmt.setString(5, "Confirm");
//		
//		int a =  stmt.executeUpdate();
//		System.out.println("rows affected.... " + a );
		
		// for class PassengerDAO in getPassenger() method
//		PreparedStatement stmt = con.prepareStatement("select * from passengers;");
//		ResultSet rs = stmt.executeQuery();
//		while(rs.next()) {
//		  System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + 
//				  				rs.getString(4) + "|" + rs.getString(5));
//		}
		
		// testing passsengerDAO
//		PassengerDAO pa = new PassengerDAO();
//		Passenger pass = new Passenger();
//		pass.setName("John snow");
//		pass.setEmail("KnowNothing@commander");
//		pass.setPhone("765468756");
//		pass.setStatus("Active");
//		
//		pa.addPassenger(pass);
//		pa.getPassenger();
		
		// for PassengerService
//		Passenger pas = new Passenger(21,"John","j@mail","32456","On_Board");
//		Passenger pas2 = new Passenger(54,"Beka","BB@mail","432","On_Board");
//		PassengerService ps = new PassengerService();
//		ps.addPassenger(pas);
//		ps.addPassenger(pas2);
//		ps.boardPassenger(pas);
//		ps.boardPassenger(pas2);
//		ps.getPassengersInBus();
//		ps.dropPassenger(pas);
//		ps.getPassengersInBus();
		
		// for PassengerService upgrades
//		Passenger pas = new Passenger(21,"John","j@mail","32456","not","Pune Station","Wakad");
//		Passenger pas2 = new Passenger(54,"Beka","BB@mail","432","not","Wakad","Katraj");
//		
//		BusService bu = new BusService();
//		PassengerService ps = new PassengerService(bu);
//		ps.addPassenger(pas2);
//		ps.addPassenger(pas);
//		
//		BusMovementThread thread = new BusMovementThread(bu,ps);
//		thread.start();
		
		// Start all threads almost simultaneously to test concurrent bookings
		
//		BusService bus = new BusService();
//		BookingService bookser = new BookingService();
//		UserBookingThread pass1 = new UserBookingThread(2,101,"Shivaji Nagar","Pune Station",bookser);
//		UserBookingThread pass2 = new UserBookingThread(2,102,"University","Katraj",bookser);
//		UserBookingThread pass3 = new UserBookingThread(2,103,"Shivaji Nagar","Katraj",bookser);
//		UserBookingThread pass4 = new UserBookingThread(2,104,"University","Katraj",bookser);
//		UserBookingThread pass5 = new UserBookingThread(2,105,"Shivaji Nagar","Katraj",bookser);
//		
//		pass1.start();
//		pass2.start();
//		pass3.start();
//		pass4.start();
//		pass5.start();
//		
//		pass1.join();
//        pass2.join();
//        pass3.join();
//        pass4.join();
//        
//        System.out.println("Available seats after first stop: ");
//
//        // move bus to next stop
//        bus.moveToNextStop();
//        System.out.println("Bus moved to: " + bus.getCurrentStop());
		
		// final call 
		
//		BusService bus = new BusService();
//		BookingService bookingService = new BookingService();
//		PassengerService passengerService = new PassengerService(bus, bookingService);
//
//		// Load passengers from DB
//		passengerService.loadPassengerfromDB();

		// Start booking threads
//		UserBookingThread t1 = new UserBookingThread(2,101,"Shivaji Nagar","Pune Station",bookingService);
//		UserBookingThread pass1 = new UserBookingThread(2,101,"Shivaji Nagar","Pune Station",bookser);
//		UserBookingThread pass2 = new UserBookingThread(2,102,"University","Katraj",bookser);
//		UserBookingThread pass3 = new UserBookingThread(2,103,"Shivaji Nagar","Katraj",bookser);
//		UserBookingThread pass4 = new UserBookingThread(2,104,"University","Katraj",bookser);
//		UserBookingThread pass5 = new UserBookingThread(2,105,"Shivaji Nagar","Katraj",bookser);

//		t1.start();
//
//		// Wait
//		t1.join();
//
//		// Start bus movement
//		BusMovementThread busThread = new BusMovementThread(bus, passengerService);
//		busThread.start();
//		
		
		// Final test package main;


        // Step 1: Create services
        BusService busService = new BusService();
        BookingService bookingService = new BookingService();
        PassengerService passengerService = new PassengerService(busService, bookingService);

        // Step 2: Create passengers
        Passenger p1 = new Passenger(107, "A", "a@mail.com", "111", "WAITING", "Pune Station", "Shivaji Nagar", 2, 0);
        Passenger p2 = new Passenger(108, "B", "b@mail.com", "222", "WAITING", "University","Shivaji Nagar", 2, 0);
        Passenger p3 = new Passenger(109, "C", "c@mail.com", "333", "WAITING", "University", "Hinjewadi ", 2, 0);

        // Step 3: Save passengers
        passengerService.addPassenger(p1);
        passengerService.addPassenger(p2);
        passengerService.addPassenger(p3);

        // Step 4: Create booking threads
        UserBookingThread t1 = new UserBookingThread(p1, bookingService);
        UserBookingThread t2 = new UserBookingThread(p2, bookingService);
        UserBookingThread t3 = new UserBookingThread(p3, bookingService);

        // Step 5: Start booking
        t1.start();
        t2.start();
        t3.start();

        // Step 6: Wait for booking to finish
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\n--- Booking Done ---\n");

        // Step 7: Simulate bus movement
        while (!busService.isLastStop()) {

            System.out.println("\nCurrent Stop: " + busService.getCurrentStop());

            // Handle boarding + dropping
            passengerService.handlePassengers();

            // Show passengers
            passengerService.getPassengersInBus();

            Thread.sleep(2000);

            busService.moveToNextStop();
        }

        System.out.println("\n--- Journey Completed ---");
    }
}
		
		
	
