package application;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetVetVisitList {
    public static ObservableList<PetVetVisit> getPetVetVisitList(Pet p) throws Exception{
        ObservableList<PetVetVisit> petVetVisits = FXCollections.observableArrayList();
        try{
            Connection con = Database.con;
            String sql = "SELECT * FROM mork_petclinic.vetvisit WHERE '"+p.getID()+"' in (pet_id) Order by ID";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                PetVetVisit petVetVisit = new PetVetVisit();
                petVetVisit.setID(rs.getInt("id"));
                petVetVisit.setDate(rs.getDate("date").toLocalDate());
                petVetVisit.setDescription(rs.getString("description"));
                petVetVisit.setVeterinarian(rs.getString("veterinarian"));
                petVetVisit.setDiagnosis(rs.getString("diagnosis"));
                petVetVisit.setTestPerformed(rs.getString("test_performed"));
                petVetVisit.setTestResults(rs.getString("test_results"));
                petVetVisit.setPrescribedActions(rs.getString("pres_actions"));
                petVetVisit.setPrescribedMeds(rs.getString("pres_meds"));
                petVetVisit.setAdditionalNotes(rs.getString("add_notes"));
                petVetVisit.setPetID(rs.getInt("pet_id"));
                petVetVisits.add(petVetVisit);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return petVetVisits;
    }
}
