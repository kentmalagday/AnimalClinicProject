package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetImmunizationList {
	 public static ObservableList<PetImmunization> getPetImmunizationList(Pet p) throws Exception{
	        ObservableList<PetImmunization> petImmunizations = FXCollections.observableArrayList();
	        try{
	            Connection con = Database.con;
	            String sql = "SELECT * FROM mork_petclinic.immunization WHERE '"+p.getID()+"' in (pet_id) Order By ID";
	            ResultSet rs = con.createStatement().executeQuery(sql);
	            while(rs.next()){
	                PetImmunization petImmunization = new PetImmunization();
	                petImmunization.setID(rs.getInt("id"));
	                petImmunization.setDate(rs.getDate("date").toLocalDate());
	                petImmunization.setWeight(rs.getString("weight"));
	                petImmunization.setAgainst(rs.getString("against"));
	                petImmunization.setVeterinarian(rs.getString("veterinarian"));
	                petImmunization.setNextDue(rs.getDate("nextDue").toLocalDate());
	                petImmunization.setPetID(rs.getInt("pet_id"));
	                petImmunizations.add(petImmunization);
	            }
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }
	        return petImmunizations;
	    }
}
