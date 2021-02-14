package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetAllergiesList {
    public static ObservableList<PetAllergy> getPetAllergiesList(Pet p) throws Exception{
        ObservableList<PetAllergy> petAllergies = FXCollections.observableArrayList();
        try{
            Connection con = Database.con;
            String sql = "SELECT * FROM mork_petclinic.allergy WHERE '"+p.getID()+"' in (pet_id) Order By ID";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                PetAllergy petAllergy = new PetAllergy();
                petAllergy.setID(rs.getInt("id"));
                petAllergy.setAllergyName(rs.getString("allergy_name"));
                petAllergy.setDescription(rs.getString("description"));
                petAllergy.setPet_ID(rs.getInt("pet_id"));
                petAllergies.add(petAllergy);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return petAllergies;
        
    }
}
