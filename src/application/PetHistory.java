package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;


public class PetHistory {
	private int ID;
	private int answered;
	private String breederName;
	private String formerVetClinic;
	private String formerVet;
	private LocalDate date;
	private String neutered;
	private int pet_id;
	
	public PetHistory() {
		this.ID = 0;
		this.answered = 0;
		this.breederName = "";
		this.formerVetClinic = "";
		this.formerVet = "";
		this.date = null;
		this.neutered = "";
		this.pet_id = 0;
	}
	
	public PetHistory(int ID, int answered, String breederName, String formerVetClinic, String formerVet, LocalDate date, String neutered, int pet_id) {
		this.ID = ID;
		this.answered = answered;
		this.breederName = breederName;
		this.formerVetClinic = formerVetClinic;
		this.formerVet = formerVet;
		this.date = date;
		this.neutered = neutered;
		this.pet_id = pet_id;
	}
	
	public int getID() {
		return ID;
	}
	public int getAnswered() {
		return answered;
	}
	public String getBreederName() {
		return breederName;
	}
	public String getFormerVetClinic() {
		return formerVetClinic;
	}
	public String getFormerVet() {
		return formerVet;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getNeutered() {
		return neutered;
	}
	public int getPetID() {
		return pet_id;
	}
	//setters
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setAnswered(int answered) {
		this.answered = answered;
	}
	public void setBreederName(String breederName) {
		this.breederName = breederName;
	}
	public void setFormerVetClinic(String formerVetClinic) {
		this.formerVetClinic = formerVetClinic;
	}
	public void setFormerVet(String formerVet) {
		this.formerVet = formerVet;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setNeutered(String neutered) {
		this.neutered = neutered;
	}
	public void setPetID(int pet_id) {
		this.pet_id = pet_id;
	}
	
	public static PetHistory getPetHistory(Pet p){
            try {
            	Connection con = Database.con;
                String sql = "SELECT * FROM mork_petclinic.history WHERE '"+p.getID()+"' in (pet_id) Order By ID";
                ResultSet rs = con.createStatement().executeQuery(sql);
                PetHistory petHistory = new PetHistory();
                int check = 0;
                while(rs.next()){
                	check++;
                    petHistory.setID(rs.getInt("id"));
                    petHistory.setAnswered(rs.getInt("answered"));
                    petHistory.setBreederName(rs.getString("breederName"));
                    petHistory.setFormerVetClinic(rs.getString("formerVetClinic"));
                    petHistory.setFormerVet(rs.getString("formerVet"));
                    Date date = rs.getDate("date");
                    if(date != null) {
                    	petHistory.setDate(date.toLocalDate());
                    }else petHistory.setDate(null);
                    petHistory.setNeutered(rs.getString("neutered"));
                    petHistory.setPetID(rs.getInt("pet_id"));   
                }
                if(check == 0) {
                	System.out.println("Empty result set");
                	return null;
                }
                System.out.println(check);
                return petHistory;
            }catch(Exception error) {
            	System.out.println(error);
            	return null;
            }
    }
}
