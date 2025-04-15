package com.info.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.info.Dao.Sports_Matches_dao;
import com.info.model.Sports_Matches;

public class Sports_Matches_service  {
	
Connection connection;
Sports_Matches_dao sports_Matches_dao = new Sports_Matches_dao(connection);


	public Sports_Matches_service(Connection connection)throws SQLException{
		this.connection=connection;
		this.sports_Matches_dao=new Sports_Matches_dao(connection);
	}

	public void insertMatches(Sports_Matches matches)throws SQLException{
		sports_Matches_dao.addMatches(matches);
	}
	
	
	public List<Sports_Matches> showmatches() throws SQLException{
		return sports_Matches_dao.displayall();
	}
	
	public void  changeM_l_id(int new_id ,int id)throws SQLException{
		 sports_Matches_dao.updateM_L_Id(new_id, id);
	}
	
	public void changeM_team1_id(int new_id ,int id)throws SQLException{
		sports_Matches_dao.updateM_team1_Id(id, new_id);
	}
	

	
	public void changeM_team2_id(int new_id ,int id)throws SQLException{
		sports_Matches_dao.updateM_team1_Id(id, new_id);
	}
	
	
	public void changeM_date(int id,Date date)throws SQLException{
		sports_Matches_dao.updateM_date(date, id);
	}
	

	
	public void changeM_location(String new_location ,int id)throws SQLException{
		sports_Matches_dao.updateM_Location(id, new_location);
	}
	

	
	public void changeM_team1_score(int new_score ,int id)throws SQLException{
		sports_Matches_dao.updateM_team1_score(id, new_score);
	}

	
	public void changeM_team2_score(int new_score ,int id)throws SQLException{
		sports_Matches_dao.updateM_team2_score(id, new_score);
	}
	
	

	
	public void changeM_winner(int new_id ,int id)throws SQLException{
		sports_Matches_dao.updateM_winner(id, new_id);
	}
	
	
	public void deleteMatches(int id)throws SQLException{
		sports_Matches_dao.deletematch(id);
	}
	
	
}
