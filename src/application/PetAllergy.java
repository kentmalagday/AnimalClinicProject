package application;

public class PetAllergy {
    private int ID;
    private String allergyName;
    private String description;
    private int pet_id;

    public PetAllergy(){
        this.ID = 0;
        this.allergyName = "";
        this.description = "";
        this.pet_id = 0;
    }
    public PetAllergy(int ID, String allergyName, String description, int pet_id){
        this.ID = ID;
        this.allergyName = allergyName;
        this.description = description;
        this.pet_id = pet_id;
    }
    public int getID(){
        return ID;
    }
    public String getAllergyName(){
        return allergyName;
    }
    public String getDescription(){
        return description;
    }
    public int getPet_ID(){
        return pet_id;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setAllergyName(String allergyName){
        this.allergyName = allergyName;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPet_ID(int pet_id){
        this.pet_id = pet_id;
    }
}
