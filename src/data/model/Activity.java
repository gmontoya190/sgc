package data.model;

import java.util.List;

public class Activity {
	
	private String idStrategy;
	private String idActivity;
	private List <Task> listTasks;
	public String getIdStrategy() {
		return idStrategy;
	}
	public void setIdStrategy(String idStrategy) {
		this.idStrategy = idStrategy;
	}
	public String getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(String idActivity) {
		this.idActivity = idActivity;
	}
	public List<Task> getListTasks() {
		return listTasks;
	}
	public void setListTasks(List<Task> listTasks) {
		this.listTasks = listTasks;
	}
	
	

}
