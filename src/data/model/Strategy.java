package data.model;

import java.util.List;

public class Strategy {
	
	private String idStrategy;
	private String idObjective;
	private String description;
	private List <Activity> listActivties;
	
	public String getIdStrategy() {
		return idStrategy;
	}
	public void setIdStrategy(String idStrategy) {
		this.idStrategy = idStrategy;
	}
	public String getIdObjective() {
		return idObjective;
	}
	public void setIdObjective(String idObjective) {
		this.idObjective = idObjective;
	}
	public List<Activity> getListActivties() {
		return listActivties;
	}
	public void setListActivties(List<Activity> listActivties) {
		this.listActivties = listActivties;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
