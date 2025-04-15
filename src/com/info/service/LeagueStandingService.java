package com.info.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.info.Dao.LeagueStandingDAO;
import com.info.model.LeagueStanding;

public class LeagueStandingService {
	Connection connection;
    private LeagueStandingDAO leagueStandingDAO=new LeagueStandingDAO(connection);

    public LeagueStandingService(Connection connection) {
        this.connection = connection;
        this.leagueStandingDAO=new LeagueStandingDAO(connection);
    }

    public void insertStanding(LeagueStanding leagueStanding)throws SQLException{
    	leagueStandingDAO.addinstanding(leagueStanding);
    }
    
    public List<LeagueStanding> displayStandings() throws SQLException {
        return leagueStandingDAO.getStandingsByLeague();
        }
    }

   


