package com.info.model;

public class LeagueStanding {
    private int teamId;
    private int leagueId;
    private String teamName;
    private int points;
;

    public LeagueStanding(int teamId, int leagueId, String teamName,  int points) {
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.teamName = teamName;
             this.points = points;
        }
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "LeagueStanding [teamId=" + teamId + ", leagueId=" + leagueId + ", teamName=" + teamName + ", points="
				+ points + "]";
	}


    

    // Getters and Setters
}
