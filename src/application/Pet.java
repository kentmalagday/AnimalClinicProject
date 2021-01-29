package application;

public class Pet {
    private String animalName;
    private String species;
    private String breed;
    private float weight;
    private int age;
    private String color;
    private String purpose;
    private int ID;
    private int owner_id;

    public Pet(){
        this.animalName = "";
        this.species = "";
        this.breed = "";
        this.weight = 0;
        this.age = 0;
        this.color = "";
        this.purpose = "";
        this.ID = 0;
        this.owner_id = 0;
    }

    public Pet(String name, String species, String breed, float weight, int age, String color, String purpose, int ID, int owner_id){
    	this.animalName = name;
        this.species = species;
        this.breed = breed;
        this.weight = weight;
        this.age = age;
        this.color = color;
        this.purpose = purpose;
        this.ID = ID;
        this.owner_id = owner_id;
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
    
    public int getAge() {
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
    public void setAge(int age) {
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
}
