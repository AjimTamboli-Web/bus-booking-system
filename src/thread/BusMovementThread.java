package thread;

import service.BusService;
import service.PassengerService;

public class BusMovementThread extends Thread{

	private BusService bus;
	private PassengerService pase;
	
	public BusMovementThread(BusService bus,PassengerService pase) {
		this.bus = bus;
		this.pase = pase;
	}
	
		
		public void run() {
			System.out.println("Bus started journey..");
			while(!bus.isLastStop()) {
			System.out.println(bus.getCurrentStop());
			pase.handlePassengers();
			pase.getPassengersInBus();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bus.moveToNextStop();
			}
			System.out.println(bus.getCurrentStop());
			pase.handlePassengers();
			System.out.println("Bus reached final stop");
			
			
		}	
	}


	
	

