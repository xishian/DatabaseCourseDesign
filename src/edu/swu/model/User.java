package edu.swu.model;

public class User {
	private String id; //staff id
	private String name; //用id和名字共同标识一个员工，因为id具有唯一性，而名字更直观
	private String pass;
	private String level; //访问级别
	
	public User() {
		super();
	}
	
	public User(String name,String pass) {
			super();
			this.name = name;
			this.pass = pass;
	}
	
	public User(String id) {
		super();
		this.id = id;
	}



	public User(String id, String name, String pass, String level) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.level = level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	

}
