package com.info.model;

import java.sql.Date;

public class Sports_Matches {
	private int m_id;
	private int l_id;
	private int team1_id;
	private int team2_id;
	private Date m_date;
	private String location;
	private int team1_score;
	private int team2_score;
	private int winner_teaam_id;
	
	
	public Sports_Matches(int m_id, int l_id, int team1_id, int team2_id, Date m_date, String location,
			int team1_score, int team2_score, int winner_teaam_id) {
		super();
		this.m_id = m_id;
		this.l_id = l_id;
		this.team1_id = team1_id;
		this.team2_id = team2_id;
		this.m_date = m_date;
		this.location = location;
		this.team1_score = team1_score;
		this.team2_score = team2_score;
		this.winner_teaam_id = winner_teaam_id;
	}
	public int getWinner_teaam_id() {
		return winner_teaam_id;
	}
	public void setWinner_teaam_id(int winner_teaam_id) {
		this.winner_teaam_id = winner_teaam_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public int getTeam1_id() {
		return team1_id;
	}
	public void setTeam1_id(int team1_id) {
		this.team1_id = team1_id;
	}
	public int getTeam2_id() {
		return team2_id;
	}
	public void setTeam2_id(int team2_id) {
		this.team2_id = team2_id;
	}
	public Date getM_date() {
		return m_date;
	}
	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getTeam1_score() {
		return team1_score;
	}
	public void setTeam1_score(int team1_score) {
		this.team1_score = team1_score;
	}
	public int getTeam2_score() {
		return team2_score;
	}
	public void setTeam2_score(int team2_score) {
		this.team2_score = team2_score;
	}
	
	@Override
	public String toString() {
		return "Sports_Matches [m_id=" + m_id + ", l_id=" + l_id + ", team1_id=" + team1_id + ", team2_id=" + team2_id
				+ ", m_date=" + m_date + ", location=" + location + ", team1_score=" + team1_score + ", team2_score="
				+ team2_score + "]";
	}
	
	
	
	
}
