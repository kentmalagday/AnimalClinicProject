package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentList {
	public static ObservableList<Appointment> getAppointmentList() throws Exception{ 
		 ObservableList<Appointment> appointments = FXCollections.observableArrayList();
	        
	        try{
	            Connection con = Database.con;
	            String sql = "SELECT * FROM `mork_petclinic`.`appointment` WHERE '0' in (deleted) ORDER BY date, SUBSTRING_INDEX(time, \" \", -1), SUBSTRING_INDEX(time, \" \", 1)";
	            ResultSet rs = con.createStatement().executeQuery(sql);
	            while(rs.next()){
	                Appointment appointment = new Appointment();
	                appointment.setDate(rs.getDate("date").toLocalDate());
	                appointment.setTime(rs.getString("time"));
	                appointment.setID(rs.getInt("ID"));
	                appointment.setPetID(rs.getInt("pet_id"));
	                
	                String getPet = "SELECT * FROM `mork_petclinic`.`animal` WHERE '"+appointment.getPetID()+"' in (animal_id) ";
		            ResultSet resultPetSet = con.createStatement().executeQuery(getPet);
		            Pet resultPet = new Pet();
		            while(resultPetSet.next()) {
		            	resultPet.setAnimalName(resultPetSet.getString("animal_name"));
		            	resultPet.setOwnerID(resultPetSet.getInt("animal_owner_id"));
		            }
		            
		            appointment.setPetName(resultPet.getAnimalName());
		            
		            String getClient = "SELECT * FROM `mork_petclinic`.`client` WHERE '"+resultPet.getOwnerID()+"' in (ID) ";
		            ResultSet resultClientSet = con.createStatement().executeQuery(getClient);
		            Client resultClient = new Client();
		            while(resultClientSet.next()) {
		            	resultClient.setLastName(resultClientSet.getString("lastName"));
		            	resultClient.setFirstName(resultClientSet.getString("firstName"));
		            }
		            
	                appointment.setClientName(resultClient.getLastName() + ", " + resultClient.getFirstName());
	                appointments.add(appointment);
	            }
	            
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }
        return appointments;
    }
}
