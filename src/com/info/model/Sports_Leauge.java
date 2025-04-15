package com.info.model;

import java.sql.Date;

public class Sports_Leauge {
	private int l_id;
	private String l_name;
	private String Sport_type;
	private Date start_date;
	private Date end_date;
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getSport_type() {
		return Sport_type;
	}
	public void setSport_type(String Sport_type) {
	  this.Sport_type = Sport_type;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Sports_Leauge(int l_id, String l_name, String Sport_type, Date start_date, Date end_date) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
		this.Sport_type = Sport_type;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	@Override
	public String toString() {
		return "Sports_Leauge [l_id=" + l_id + ", l_name=" + l_name + ", s_type=" + Sport_type + ", start_date="
				+ start_date + ", end_date=" + end_date + "]";
	}
	
	
}
