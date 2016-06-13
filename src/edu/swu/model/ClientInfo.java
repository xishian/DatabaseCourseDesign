package edu.swu.model;

public class ClientInfo {
	private String id;
	private String name;
	private String sex;
	private String depart; //单位
	private String site; //单位地址
	private String tel;
	private String mail;
	
	
	public ClientInfo() {
		super();
	}
	
	
	public ClientInfo(String id) {
		super();
		this.id = id;
	}


	public ClientInfo(String id, String name, String sex, String depart, String site, String tel, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.depart = depart;
		this.site = site;
		this.tel = tel;
		this.mail = mail;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	

}
