package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetList {
    public static ObservableList<Pet> getPetList(Client selected) throws Exception{
        ObservableList<Pet> pets = FXCollections.observableArrayList();
        
        try{
            Connection con = Database.getConnection();
            String sql = "SELECT * FROM `mork_petclinic`.`animal` WHERE '"+selected.getID()+"' in (animal_owner_id)";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Pet pet = new Pet();
                pet.setID(rs.getInt("animal_id"));
                pet.setAnimalName(rs.getString("animal_name"));
                pet.setSpecies(rs.getString("species"));
                pet.setBreed(rs.getString("breed"));
                pet.setWeight(rs.getFloat("weight"));
                pet.setAge(rs.getString("age"));
                pet.setColor(rs.getString("color"));
                pet.setPurpose(rs.getString("purpose"));
                Date date = rs.getDate("appointmentDate");
                if(date != null)
                	pet.setAppointmentDate(date.toLocalDate());
                String time = rs.getString("appointmentTime");
                if(time != null)
                	pet.setAppointmentTime(time);
                pets.add(pet);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return pets;
    }
    
    
    
    
    
}
