package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetIllness {
	private int ID;
	private String diagnosis;
	private String treatment;
	private String information;
	private int pet_id;
	
	public PetIllness() {
		this.ID = 0;
		this.diagnosis = "";
		this.treatment = "";
		this.information = "";
		this.pet_id = 0;
	}
	
	public PetIllness(int ID, String diagnosis, String treatment, String information, int pet_id) {
		this.ID = ID;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.information = information;
		this.pet_id = pet_id;
	}
	
	public int getID() {
		return ID;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public String getInformation() {
		return information;
	}
	public int getPetID() {
		return pet_id;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public void setPetID(int pet_id) {
		this.pet_id = pet_id;
	}
	
	public static ObservableList<PetIllness> getPetIllnessList(Pet p) throws Exception{
		ObservableList<PetIllness> petIllnesses = FXCollections.observableArrayList();
		Connection con = Database.con;
        String sql = "SELECT * FROM mork_petclinic.illness WHERE '"+p.getID()+"' in (pet_id) Order By ID";
        ResultSet rs = con.createStatement().executeQuery(sql);
        while(rs.next()){
            PetIllness petIllness = new PetIllness();
            petIllness.setID(rs.getInt("id"));;
            petIllness.setDiagnosis(rs.getString("diagnosis"));
            petIllness.setTreatment(rs.getString("treatment"));
            petIllness.setInformation(rs.getString("information"));
            petIllness.setPetID(rs.getInt("pet_id"));
            petIllnesses.add(petIllness);
        }
        return petIllnesses;
	}
        
}
