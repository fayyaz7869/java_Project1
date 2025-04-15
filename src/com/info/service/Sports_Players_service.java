package com.info.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.info.Dao.Sports_players_dao;
import com.info.model.Sports_Players;

public class Sports_Players_service {
	
	Connection connection;
	Sports_players_dao sports_players_dao=new Sports_players_dao(connection);
	
	public Sports_Players_service(Connection connection)throws SQLException{
		this.connection=connection;
		this.sports_players_dao=new Sports_players_dao(connection);
	}
	
	
	
	public void insertPlayers(Sports_Players players)throws SQLException{
		sports_players_dao.addplayers(players);
	}
	
	public List<Sports_Players> showplayers() throws SQLException{
	    return	sports_players_dao.displayPlayers();
	}
	
	public void changeP_Name(String name , int id) throws SQLException{
		sports_players_dao.updatep_name(name, id);
	}
	
	public void changeP_age(int age,int id)throws SQLException{
		sports_players_dao.updatep_age(age, id);
	}
	
	
	public void changeP_position(String position,int id)throws SQLException{
		sports_players_dao.updatep_Position(position, id);
	}
	
	public void changeP_t_id(int t_id,int id)throws SQLException{
		sports_players_dao.updatept_id(t_id, id);
	}
	
	
	public void deleteplayer(int id)throws SQLException{
		sports_players_dao.deletePlayer(id);	
		}
	
}
