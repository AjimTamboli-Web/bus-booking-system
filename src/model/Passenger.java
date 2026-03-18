package model;

public class Passenger {

	private int passengerId;
	private String name;
	private String email;
	private String phone;
	private String status;
	private String sourceStop;
	private String destinationStop;
	private int busId;
	private int bookingId;
	
	public Passenger() {
		
	}
	
	public Passenger(int passengerId, String name, String email, String phone,
            String status, String sourceStop, String destinationStop) {
			this.passengerId = passengerId;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.status = status;
			this.sourceStop = sourceStop;
			this.destinationStop = destinationStop;
	}
	
	public Passenger(int passengerId, String name, String email, String phone, String status, String sourceStop,
			String destinationStop, int busId, int bookingId) {
		this.passengerId = passengerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.sourceStop = sourceStop;
		this.destinationStop = destinationStop;
		this.busId = busId;
		this.bookingId = bookingId;
	}

	
	
	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSourceStop() {
		return sourceStop;
	}

	public void setSourceStop(String sourceStop) {
		this.sourceStop = sourceStop;
	}

	public String getDestinationStop() {
		return destinationStop;
	}

	public void setDestinationStop(String destinationStop) {
		this.destinationStop = destinationStop;
	}

	
	
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", status=" + status + ", sourceStop=" + sourceStop + ", destinationStop=" + destinationStop
				+ ", busId=" + busId + ", bookingId=" + bookingId + "]";
	}
	
}
