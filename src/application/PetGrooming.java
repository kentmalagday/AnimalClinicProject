package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetGrooming {
	private int ID;
	private LocalDate date;
	private String groom;
	private String treatments;
	private String groomer;
	private int pet_id;
	
	public PetGrooming() {
		this.ID = 0;
		this.date = null;
		this.groom = "";
		this.treatments = "";
		this.groomer = "";
		this.pet_id = 0;
	}
	
	public PetGrooming(int ID, LocalDate date, String groom, String treatments, String groomer, int pet_id) {
		this.ID = ID;
		this.date = date;
		this.groom = groom;
		this.treatments = treatments;
		this.groomer = groomer;
		this.pet_id = pet_id;
	}
	
	public int getID() {
		return ID;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getGroom() {
		return groom;
	}
	public String getTreatments() {
		return treatments;
	}
	public String getGroomer() {
		return groomer;
	}
	public int pet_id() {
		return pet_id;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setGroom(String groom) {
		this.groom = groom;
	}
	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}
	public void setGroomer(String groomer) {
		this.groomer = groomer;
	}
	public void setPetID(int pet_id) {
		this.pet_id = pet_id;
	}
	
	public static ObservableList<PetGrooming> getPetGroomingList(Pet p) throws Exception{
        ObservableList<PetGrooming> petGroomings = FXCollections.observableArrayList();
        try{
            Connection con = Database.con;
            String sql = "SELECT * FROM mork_petclinic.grooming WHERE '"+p.getID()+"' in (pet_id) Order By ID";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                PetGrooming petGrooming = new PetGrooming();
                petGrooming.setID(rs.getInt("id"));
                petGrooming.setDate(rs.getDate("date").toLocalDate());
                petGrooming.setGroom(rs.getString("groom"));
                petGrooming.setTreatments(rs.getString("treatments"));
                petGrooming.setGroomer(rs.getString("groomer"));
                petGrooming.setPetID(rs.getInt("pet_id"));
                petGroomings.add(petGrooming);
            }
         
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return petGroomings;
    }
}
