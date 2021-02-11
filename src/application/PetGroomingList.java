package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetGroomingList {
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
