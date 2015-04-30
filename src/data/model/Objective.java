package data.model;

import java.util.List;

public class Objective {
	
	private String idObjective;
	private List <Strategy> listStrategies;
	private String owner;
	private String description;
	
	public String getIdObjective() {
		return idObjective;
	}
	public void setIdObjective(String idObjective) {
		this.idObjective = idObjective;
	}
	public List<Strategy> getListStrategies() {
		return listStrategies;
	}
	public void setListStrategies(List<Strategy> listStrategies) {
		this.listStrategies = listStrategies;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
