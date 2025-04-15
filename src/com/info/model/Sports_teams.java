package com.info.model;

public class Sports_teams {
	private int t_id;
	private String t_name;
	private String home_city;
	private int l_id;
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getHome_city() {
		return home_city;
	}
	public void setHome_city(String home_city) {
		this.home_city = home_city;
	}
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public Sports_teams(int t_id, String t_name, String home_city, int l_id) {
		super();
		this.t_id = t_id;
		this.t_name = t_name;
		this.home_city = home_city;
		this.l_id = l_id;
	}
	@Override
	public String toString() {
		return "Sports_teams [t_id=" + t_id + ", t_name=" + t_name + ", home_city=" + home_city + ", l_id=" + l_id
				+ "]";
	}

	
	
}
