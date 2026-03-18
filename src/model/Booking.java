package model;

import java.time.LocalDateTime;

public class Booking {

	private int	bookingId;
	private int 	passengerId;
	private String passengerName;
	private int busId;
	private String	destination;
	private LocalDateTime bookingTime;
	private String busStopWhenBooked;
	private String status;

	public Booking() {
		
	}
	
	public Booking(int id,int id2,String passName,int id3,String desti,LocalDateTime loc,String stops,String status) {
	
	this.bookingId = id;
	this.passengerId = id2;
	this.passengerName = passName;
	this.busId = id3;
	this.destination = desti;
	this.bookingTime = loc;
	this.busStopWhenBooked = stops;
	this.status = status;
	}
	
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public void setBookingId(int book) {
		this.bookingId = book;
	}
	public int getBookingId() {
		return bookingId;
	}
	
	public void setPassengerId(int pass) {
		this.passengerId = pass;	
	}
	public int getPassengerId() {
		return passengerId;
	}
	
	public void setBusId(int bu) {
		this.busId = bu;
	}
	public int getBusId() {
		return busId;
	}
	
	public void setDestination(String desti) {
		this.destination = desti;
	}
	public String getDestination() {
		return destination;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getBusStopWhenBooked() {
		return busStopWhenBooked;
	}
	public void setBusStopWhenBooked(String busStopWhenBooked) {
		this.busStopWhenBooked = busStopWhenBooked;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", passengerId=" + passengerId + ", passengerName=" + passengerName
				+ ", busId=" + busId + ", destination=" + destination + ", bookingTime=" + bookingTime
				+ ", busStopWhenBooked=" + busStopWhenBooked + ", status=" + status + "]";
	}
	
	
	

	
}
