package application;

public class Client {
    private String lastName;
    private String firstName;
    private int age;
    private String email;
    private String contact;
    private String address;
    private int ID;

    public Client(){
        this.lastName = "";
        this.firstName = "";
        this.age = 0;
        this.email = "";
        this.contact = "";
        this.address = "";
        this.ID = 0;
    }

    public Client(String lastName, String firstName, int age, String email, String contact, String address){
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.ID = 0;
    }

    public String getLastName(){
        return lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }

    public int getAge(){
        return age;
    }

    public String getEmail(){
        return email;
    }

    public String getContact(){
        return contact;
    }
    
    public String getAddress() {
    	return address;
    }

    public int getID(){
        return ID;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public void setAddress(String address) {
    	this.address = address;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
}
