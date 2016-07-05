package pers.mine.vo;

import java.io.Serializable;



public class Member  implements Serializable{
	private String id;
	private String name;
	private String PW;
	private String sex;
	private int level;
	private String tel;
	private Cart cart;
	public Member(){
		cart=new Cart();
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
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	/**
	 * @param id
	 * @param pW
	 */
	public Member(String id, String pW) {
		super();
		this.id = id;
		PW = pW;
		cart=new Cart();
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", PW=" + PW + ", sex="
				+ sex + ", level=" + level + ", tel=" + tel + ", cart=" + cart.getNumSum()
				+ "]";
	}
	public Member(String name,String id,  String pW) {
		super();
		this.id = id;
		this.name = name;
		PW = pW;
		cart=new Cart();
	}
	public Member(String id) {
		// TODO 自动生成的构造函数存根
		super();
		this.id = id;
		cart=new Cart();
	}
	
	
	
}
