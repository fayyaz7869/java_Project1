package com.info.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.info.model.Sports_teams;

public class Sports_teams_dao {
Connection connection;
	public Sports_teams_dao(Connection connection) throws SQLException {
		this.connection=connection;
	}

	public void addteam(Sports_teams teams)throws SQLException{
		PreparedStatement preparedStatement=connection.prepareStatement("Insert into teams values(?,?,?,?)");
		preparedStatement.setInt(1, teams.getT_id());
		preparedStatement.setString(2, teams.getT_name());
		preparedStatement.setString(3, teams.getHome_city());
		preparedStatement.setInt(4, teams.getL_id());
		preparedStatement.executeUpdate();
	}
	
public List<Sports_teams> displayteams()throws SQLException{
	List<Sports_teams> team=new ArrayList<Sports_teams>();
	PreparedStatement preparedStatement=connection.prepareStatement("Select * from teams");
	ResultSet resultSet=preparedStatement.executeQuery();
	while(resultSet.next()) {
		team.add(new Sports_teams(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
		
		
	}
	return team;
}

public void updatet_name(String name,int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update teams set t_name=? where t_id=?");
	preparedStatement.setString(1, name);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}
public void updatethome_city(String city,int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update teams set home_city=? where t_id=?");
	preparedStatement.setString(1, city);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
}

public void updatetl_id(int l_id,int id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update teams set l_id=? where t_id=?");
	preparedStatement.setInt(1, l_id);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
}

public void deleteteams(int id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Delete from teams where t_id=?");
	preparedStatement.setInt(1, id);
	preparedStatement.executeUpdate();
	
}
	
	
}
