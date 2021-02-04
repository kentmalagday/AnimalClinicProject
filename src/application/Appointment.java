package application;

import java.time.LocalDate;

public class Appointment {
	private int ID;
	private int petID;
	private LocalDate date;
	private String time;
	private String clientName;
	
	public Appointment() {
		this.ID = 0;
		this.petID = 0;
		this.date = LocalDate.now();
		this.time = "";
		this.clientName = "";
	}
	
	public Appointment(int ID, int petID, LocalDate date, String time, String name) {
		this.ID = ID;
		this.petID = petID;
		this.date = date;
		this.time = time;
		this.clientName = name;
	}
	
	public int getID() {
		return ID;
	}
	public int getPetID() {
		return petID;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getClientName() {
		return clientName;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setPetID(int petID) {
		this.petID = petID;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setClientName(String name) {
		this.clientName = name;
	}
}
