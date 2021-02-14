package application;

import java.time.LocalDate;

public class PetVetVisit {
    private int ID;
    private LocalDate date;
    private String description;
    private String veterinarian;
    private String diagnosis;
    private String testperformed;
    private String testresults;
    private String prescribedactions;
    private String prescribedMeds;
    private String additionalnotes;
    private int pet_id;

    public PetVetVisit(){
        this.ID = 0;
        this.date = null;
        this.description = "";
        this.veterinarian = "";
        this.diagnosis = "";
        this.testperformed = "";
        this.testresults = "";
        this.prescribedactions = "";
        this.prescribedMeds = "";
        this.additionalnotes = "";
        this.pet_id = 0;
    }

    public PetVetVisit(int ID, LocalDate date, String description, String veterinarian, String diagnosis, String testperformed, String testresults, String prescribedactions, String prescribedMeds, String additionalnotes, int pet_id){
        this.ID = ID;
        this.date = date;
        this.description = description;
        this.veterinarian = veterinarian;
        this.diagnosis = diagnosis;
        this.testperformed = testperformed;
        this.testresults = testresults;
        this.prescribedactions = prescribedactions;
        this.prescribedMeds = prescribedMeds;
        this.additionalnotes = additionalnotes;
        this.pet_id = pet_id;
    }

    public int getID(){
        return ID;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getDescription(){
        return description;
    }
    public String getVeterinarian(){
        return veterinarian;
    }
    public String getDiagnosis(){
        return diagnosis;
    }
    public String getTestPerformed(){
        return testperformed;
    }
    public String getTestResults(){
        return testresults;
    }
    public String getPrescribedMeds(){
        return prescribedMeds;
    }
    public String getPrescribedActions(){
        return prescribedactions;
    }
    public String getAdditionalNotes(){
        return additionalnotes;
    }
    public int getPetID(){
        return pet_id;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setVeterinarian(String veterinarian){
        this.veterinarian = veterinarian;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    public void setTestPerformed(String testperformed){
        this.testperformed = testperformed;
    }
    public void setTestResults(String testresults){
        this.testresults = testresults;
    }
    public void setPrescribedMeds(String prescribedMeds){
        this.prescribedMeds = prescribedMeds;
    }
    public void setPrescribedActions(String prescribedactions){
        this.prescribedactions = prescribedactions;
    }
    public void setAdditionalNotes(String additionalnotes){
        this.additionalnotes = additionalnotes;
    }
    public void setPetID(int pet_id){
        this.pet_id = pet_id;
    }
}
