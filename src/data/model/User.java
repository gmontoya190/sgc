package data.model;

public class User {
	
	private String name;
	private String email;
	private int age;
	private String sex;
	private String password;
	private String processLead;
	private String idProcess;
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public String getPassword() {
		return password;
	}
	public String getProcessLead() {
		return processLead;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setProcessLead(String processLead) {
		this.processLead = processLead;
	}
	public String getIdProcess() {
		return idProcess;
	}
	public void setIdProcess(String idProcess) {
		this.idProcess = idProcess;
	}
	
	
	

}
