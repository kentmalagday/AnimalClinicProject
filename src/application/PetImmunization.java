package application;

import java.time.LocalDate;

public class PetImmunization {
	private int ID;
    private LocalDate date;
    private String weight;
    private String against;
    private String veterinarian;
    private LocalDate nextDue;
    private int pet_id;

    public PetImmunization(){
        this.ID = 0;
        this.date = null;
        this.weight = "";
        this.against = "";
        this.veterinarian = "";
        this.nextDue = null;
        this.pet_id = 0;
    }

    public PetImmunization(int ID, LocalDate date, String weight, String against, String veterinarian, LocalDate nextDue, int pet_id){
        this.ID = ID;
        this.date = date;
        this.weight = weight;
        this.against = against;
        this.veterinarian = veterinarian;
        this.nextDue = nextDue;
        this.pet_id = pet_id;
    }

    public int getID(){
        return ID;
    }
    public LocalDate getDate(){
        return date;
    }
    
    public String getWeight(){
        return weight;
    }

    public String getAgainst(){
        return against;
    }

    public String getVeterinarian(){
        return veterinarian;
    }

    public LocalDate getNextDue(){
        return nextDue;
    }
    
    public int getPetID() {
    	return pet_id;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setDate(LocalDate date) {
    	this.date = date;
    }
    public void setWeight(String weight) {
    	this.weight = weight;
    }
    public void setAgainst(String against) {
    	this.against = against;
    }
    public void setVeterinarian(String veterinarian) {
    	this.veterinarian = veterinarian;
    }
    public void setNextDue(LocalDate nextDue) {
    	this.nextDue = nextDue;
    }
    public void setPetID(int pet_id) {
    	this.pet_id = pet_id;
    }
    
    
}
