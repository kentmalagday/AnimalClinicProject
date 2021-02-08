package application;

import java.time.LocalDate;

public class Pet {
    private String animalName;
    private String species;
    private String breed;
    private float weight;
    private String age;
    private String color;
    private String purpose;
    private int ID;
    private int owner_id;
    private LocalDate appointmentDate;
    private String appointmentTime;

    public Pet(){
        this.animalName = "";
        this.species = "";
        this.breed = "";
        this.weight = 0;
        this.age = "";
        this.color = "";
        this.purpose = "";
        this.ID = 0;
        this.owner_id = 0;
        this.appointmentDate = null;
        this.appointmentTime = "";
 
    }

    public Pet(String name, String species, String breed, float weight, String age, String color, String purpose, int ID, int owner_id, LocalDate appointmentDate, String time){
    	this.animalName = name;
        this.species = species;
        this.breed = breed;
        this.weight = weight;
        this.age = age;
        this.color = color;
        this.purpose = purpose;
        this.ID = ID;
        this.owner_id = owner_id;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = time;
     
    }

    public String getAnimalName() {
    	return animalName;
    }
    
    public String getSpecies() {
    	return species;
    }
    
    public String getBreed() {
    	return breed;
    }
    
    public float getWeight() {
    	return weight;
    }
    
    public String getAge() {
    	return age;
    }
    
    public String getColor() {
    	return color;
    }
    
    public String getPurpose() {
    	return purpose;
    }
    public int getID() {
    	return ID;
    }
    public int getOwnerID() {
    	return owner_id;
    }
    public LocalDate getAppointmentDate() {
    	return appointmentDate;
    }
    public String getAppointmentTime() {
    	return appointmentTime;
    }
   
   
    
    public void setAnimalName(String name) {
    	this.animalName = name;
    }
    public void setSpecies(String species) {
    	this.species = species;
    }
    public void setBreed(String breed) {
    	this.breed = breed;
    }
    public void setWeight(float weight) {
    	this.weight = weight;
    }
    public void setAge(String age) {
    	this.age = age;
    }
    public void setColor(String color) {
    	this.color = color;
    }
    public void setPurpose(String purpose) {
    	this.purpose = purpose;
    }
    public void setID(int ID) {
    	this.ID = ID;
    }
    public void setOwnerID(int owner_id) {
    	this.owner_id = owner_id;
    }
    public void setAppointmentDate(LocalDate appointmentDate) {
    	this.appointmentDate = appointmentDate;
    	
    }
    public void setAppointmentTime(String time) {
    	this.appointmentTime = time;
    }
   
    
}
