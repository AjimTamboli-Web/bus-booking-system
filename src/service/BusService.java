package service;

import java.util.ArrayList;
import java.util.List;

public class BusService {

	private List<String> stops;
	
	private int currentStopIndex;
//	private int totalSeats = 3;
//	private int availableSeats;
	
	
	public BusService() {
		stops = new ArrayList<>();
	    
	    
		stops.add("Pune Station");
		stops.add("Shivaji Nagar");
		stops.add("University");
		stops.add("Wakad");
		stops.add("Hinjewadi");
		stops.add("Katraj");
		stops.add("Katraj");
		
		currentStopIndex = 0;
	}
	
	public String getCurrentStop() {
		return stops.get(currentStopIndex);
	}
	
    public void moveToNextStop() {
    		if(currentStopIndex < stops.size()-1) {
    	     this.currentStopIndex++;
    		}
    		else {
    			System.out.println("can not be move next stop");
    		}
    }
    
    public int getCurrentStopIndex() {
    	 return this.currentStopIndex;
    }
	
    public int getStopIndexByName(String stopName) {
    	    int index = stops.indexOf(stopName);
    	    
    		if(index == -1) {
    			return -1;
    		}
    		else {
    			return index;
    		}
    }
    
    public boolean isLastStop() {
        return currentStopIndex == stops.size()-1;
    }
	
    // for seat concepts 
//    public boolean bookSeat() {
//    	 if(availableSeats > 0) {
//    		 availableSeats--;
//    		 return true;
//    	 }
//    	 return false;
//    }
//    
//    public void releaseSeat() {
//    	 if(availableSeats < totalSeats) {
//    		 availableSeats++;
//    	 }
//    }
//    
//    public int getAvailableSeats() {
//    	return availableSeats;
//    }
    
    
	
}
