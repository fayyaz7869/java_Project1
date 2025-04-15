package com.info.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.info.model.Sports_Matches;

public class Sports_Matches_dao {
Connection  connection;
public Sports_Matches_dao(Connection connection) {
	this.connection=connection;
}

public void addMatches(Sports_Matches matches) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Insert into matches values(?,?,?,?,?,?,?,?,?)");
	preparedStatement.setInt(1, matches.getM_id());
	preparedStatement.setInt(2, matches.getL_id());
	preparedStatement.setInt(3, matches.getTeam1_id());
	preparedStatement.setInt(4, matches.getTeam2_id());
	preparedStatement.setDate(5, matches.getM_date());
	preparedStatement.setString(6, matches.getLocation());
	preparedStatement.setInt(7,matches.getTeam1_score() );
	preparedStatement.setInt(8, matches.getTeam2_score());
	preparedStatement.setInt(9, matches.getWinner_teaam_id());
	preparedStatement.executeUpdate();
	
}
public List <Sports_Matches> displayall() throws SQLException{
	List <Sports_Matches> sports_Matches=new ArrayList<Sports_Matches>();
	PreparedStatement preparedStatement=connection.prepareStatement("Select * from matches");
	ResultSet resultSet=preparedStatement.executeQuery();
	while(resultSet.next()) {
		sports_Matches.add(new Sports_Matches(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),resultSet.getInt(9)));
	}
	return sports_Matches;
}

public void updateM_L_Id(int new_id,int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set l_id=? where m_id=?");
	preparedStatement.setInt(1, new_id);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_team1_Id(int id,int new_id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set team1_id=? where m_id=?");
	preparedStatement.setInt(1, new_id);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_team2_Id(int id,int new_id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set team2_id=? where m_id=?");
	preparedStatement.setInt(1, new_id);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_date(Date date,int id) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set m_date=? where m_id=?");
	preparedStatement.setDate(1, date);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}


public void updateM_Location(int id,String new_loc) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set location=? where m_id=?");
	preparedStatement.setString(1, new_loc);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_team1_score(int id,int new_score) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set team1_score=? where m_id=?");
	preparedStatement.setInt(1, new_score);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_team2_score(int id,int new_score) throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("UPDATE matches set team2_score=? where m_id=?");
	preparedStatement.setInt(1, new_score);
	preparedStatement.setInt(2, id);
	preparedStatement.executeUpdate();
	
}

public void updateM_winner(int id,int new_id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Update matches  set winner_team_id=? where m_id=?");
	preparedStatement.setInt(1, new_id);
preparedStatement.setInt(2, id);
preparedStatement.executeUpdate();

}

public void deletematch(int id)throws SQLException{
	PreparedStatement preparedStatement=connection.prepareStatement("Delete from matches where m_id=?");
	preparedStatement.setInt(1, id);
	preparedStatement.executeUpdate();
}


}
