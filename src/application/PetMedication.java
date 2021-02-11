package application;

public class PetMedication {
	private int ID;
	private String drugName;
    private String description;
    private String dosage;
    private String status;
    private int pet_id;

    public PetMedication(){
        this.ID = 0;
        this.drugName = "";
        this.description = "";
        this.dosage = "";
        this.status = "";
        this.pet_id = 0;
    }

    public PetMedication(int ID, String drugName, String description, String dosage, String status, int pet_id){
        this.ID = ID;
        this.drugName = drugName;
        this.description = description;
        this.dosage = dosage;
        this.status = status;
        this.pet_id = pet_id;
    }

    public int getID(){
        return ID;
    }
    public String getDrugName() {
    	return drugName;
    }
    public String getDescription(){
    	return description;
    }
    
    public String getDosage(){
        return dosage;
    }

    public String getStatus(){
        return status;
    }
    
    public int getPetID() {
    	return pet_id;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public void setDrguName(String drugName) {
    	this.drugName = drugName;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    public void setDosage(String dosage) {
    	this.dosage = dosage;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    public void setPetID(int pet_id) {
    	this.pet_id = pet_id;
    }
}
