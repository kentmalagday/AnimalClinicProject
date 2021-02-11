package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetMedicationList {
	public static ObservableList<PetMedication> getPetMedicationList(Pet p) throws Exception{
        ObservableList<PetMedication> petMedications = FXCollections.observableArrayList();
        try{
            Connection con = Database.con;
            String sql = "SELECT * FROM mork_petclinic.medication WHERE '"+p.getID()+"' in (pet_id) Order By ID";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                PetMedication petMedication = new PetMedication();
                petMedication.setID(rs.getInt("id"));
                petMedication.setDrguName(rs.getString("drugName"));
                petMedication.setDescription(rs.getString("description"));
                petMedication.setDosage(rs.getString("dosage"));
                petMedication.setStatus(rs.getString("status"));
                petMedication.setPetID(rs.getInt("pet_id"));
                petMedications.add(petMedication);
            }
         
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return petMedications;
    }
}
