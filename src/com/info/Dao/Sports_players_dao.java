package com.info.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.info.model.Sports_Players;

public class Sports_players_dao {
Connection connection;

public Sports_players_dao(Connection connection)throws SQLException{
	this.connection=connection;
}
public void addplayers(Sports_Players players) throws SQLException {
	PreparedStatement preparedStatement=connection.prepareStatement("Insert into players values(?,?,?,?,?)");
	preparedStatement.setInt(1, players.getP_id());
	preparedStatement.setString(2, players.getP_name());
	preparedStatement.setInt(3, players.getAge());
	preparedStatement.setString(4, players.getPosition());
	preparedStatement.setInt(5, players.getT_id());
	preparedStatement.executeUpdate();
}

public List<Sports_Players> displayPlayers()throws SQLException{
	List<Sports_Players> player=new ArrayList<Sports_Players>();
	PreparedStatement preparedStatement=connection.prepareStatement("Select * from players");
	ResultSet resultSet=preparedStatement.executeQuery();
	while(resultSet.next()) {
		player.add(new Sports_Players(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5)));
	}
	return player;
}


public void updatep_name(String name,int id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("update Players set p_name=? where p_id=?");
	preparedStatement.setString(1, name);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
}

public void updatep_age(int age,int id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("update players set p_age=? where p_id=?");
	preparedStatement.setInt(1, age);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
}

public void updatep_Position(String p,int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update players set position=? where p_id=?");
	preparedStatement.setString(1, p);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
}

public void updatept_id(int id,int id1) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update players set t_id=? where p_id=?");
	preparedStatement.setInt(1, id);
	preparedStatement.setInt(2, id1);
	preparedStatement.executeUpdate();
	
}

public void deletePlayer(int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("delete from players where p_id=?");
	preparedStatement.setInt(1, id);
	preparedStatement.executeUpdate();
}
	
}
