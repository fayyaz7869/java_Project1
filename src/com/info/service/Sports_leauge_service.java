package com.info.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.info.Dao.Sports_league_dao;
import com.info.model.Sports_Leauge;

public class Sports_leauge_service {
Connection connection;
Sports_league_dao sports_league_dao = new Sports_league_dao(connection);
 
public Sports_leauge_service(Connection connection)throws SQLException{
	this.connection=connection;
	 this.sports_league_dao = new Sports_league_dao(connection); 
}

public void addinleague(Sports_Leauge leauge)throws SQLException{
	sports_league_dao.addLeague(leauge);
}


public List<Sports_Leauge > showLeauges()throws SQLException{
	return sports_league_dao.getAllLeagues();
}

public void Updateleauges(Sports_Leauge leauge) throws SQLException{
	sports_league_dao.updateLeague(leauge);
}

public void removeleauge(int id) throws SQLException{
	sports_league_dao.deleteLeague(id);
}

}
