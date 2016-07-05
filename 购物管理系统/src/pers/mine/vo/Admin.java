package pers.mine.vo;

import java.io.Serializable;



public class Admin implements Serializable{
	
	private String name;
	private String id;
	private String PW;
	public Admin(){}
	/**
	 * @param id
	 * @param pW
	 */
	public Admin(String id, String pW) {
		super();
		this.id = id;
		PW = pW;
	}
	public Admin(String name, String id, String pW) {
		super();
		this.name = name;
		this.id = id;
		PW = pW;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	@Override
	public String toString() {
		return "Admin [name=" + name + ", id=" + id + ", PW=" + PW + "]";
	}
	
	
	
}
