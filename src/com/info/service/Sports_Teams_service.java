package com.info.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.info.Dao.Sports_teams_dao;
import com.info.model.Sports_teams;

public class Sports_Teams_service {
	
	Connection connection;
	Sports_teams_dao sports_teams_dao=new Sports_teams_dao(connection);
	public Sports_Teams_service(Connection connection)throws SQLException{
		this.connection=connection;
		this.sports_teams_dao=new Sports_teams_dao(connection);
	}
	
	public void insertTeams(Sports_teams teams)throws SQLException{
		sports_teams_dao.addteam(teams);
	}
	
	public List<Sports_teams> showteams()throws SQLException{
		return sports_teams_dao.displayteams();
	}
	
	public void changeT_name(String name,int id)throws SQLException{
		sports_teams_dao.updatet_name(name, id);
	}
	public void changeT_home_city(String city,int id) throws SQLException{
		sports_teams_dao.updatethome_city(city, id);
	}
	
	public void changeT_L_id(int new_id,int id)throws SQLException{
		sports_teams_dao.updatetl_id(new_id, id);
	}
	
	public void deleteTeam(int id)throws SQLException{
		sports_teams_dao.deleteteams(id);
	}
}