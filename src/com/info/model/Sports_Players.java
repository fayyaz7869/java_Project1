package com.info.model;

public class Sports_Players {
	private int p_id;
	private String p_name;
	private int age;
	private String position;
	private int t_id;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public Sports_Players(int p_id, String p_name, int age, String position, int t_id) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.age = age;
		this.position = position;
		this.t_id = t_id;
	}
	@Override
	public String toString() {
		return "Sports_Players [p_id=" + p_id + ", p_name=" + p_name + ", age=" + age + ", position=" + position
				+ ", t_id=" + t_id + "]";
	}
	
	
	
}
