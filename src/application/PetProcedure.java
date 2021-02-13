package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetProcedure {
	private int ID;
	private LocalDate date;
	private String procedure;
	private String additional;
	private int pet_id;
	
	public PetProcedure() {
		this.ID = 0;
		this.date = null;
		this.procedure = "";
		this.additional = "";
		this.pet_id = 0;
	}
	
	public PetProcedure(int ID, LocalDate date, String procedure, String additional, int pet_id) {
		this.ID = ID;
		this.date = date;
		this.procedure = procedure;
		this.additional = additional;
		this.pet_id = pet_id;
	}
	
	public int getID() {
		return ID;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getProcedure() {
		return procedure;
	}
	public String getAdditional() {
		return additional;
	}
	public int getPetID() {
		return pet_id;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public void setPetID(int pet_id) {
		this.pet_id = pet_id;
	}
	
	public static ObservableList<PetProcedure> getPetProcedureList(Pet p) throws Exception{
		ObservableList<PetProcedure> petProcedures = FXCollections.observableArrayList();
		Connection con = Database.con;
        String sql = "SELECT * FROM mork_petclinic.surgical WHERE '"+p.getID()+"' in (pet_id) Order By ID";
        ResultSet rs = con.createStatement().executeQuery(sql);
        while(rs.next()){
            PetProcedure petProcedure = new PetProcedure();
            petProcedure.setID(rs.getInt("id"));
            petProcedure.setDate(rs.getDate("date").toLocalDate());
            petProcedure.setProcedure(rs.getString("procedure"));
            petProcedure.setAdditional(rs.getString("additional"));
            petProcedure.setPetID(rs.getInt("pet_id"));
            petProcedures.add(petProcedure);
        }
        return petProcedures;
	}
}
