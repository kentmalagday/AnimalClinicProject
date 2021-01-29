package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetList {
    
    public static ObservableList<Pet> getPetList(Client selected) throws Exception{
        ObservableList<Pet> pets = FXCollections.observableArrayList();
        try{
            Connection con = Database.getConnection();
            String sql = "SELECT * FROM `mork_petclinic`.`animal` WHERE '"+selected.getID()+"' in (animal_owner_id) ORDER BY `animal`.`animal_id` ASC ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Pet pet = new Pet();
                pet.setID(rs.getInt("animal_id"));
                pet.setAnimalName(rs.getString("animal_name"));
                pet.setSpecies(rs.getString("species"));
                pet.setBreed(rs.getString("breed"));
                pet.setWeight(rs.getFloat("weight"));
                pet.setAge(rs.getInt("age"));
                pet.setColor(rs.getString("color"));
                pet.setPurpose(rs.getString("purpose"));
                pets.add(pet);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return pets;
    }
    

    
}
