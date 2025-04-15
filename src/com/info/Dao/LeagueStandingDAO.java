package com.info.Dao;
import com.info.model.LeagueStanding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueStandingDAO {
    private Connection conn;

    public LeagueStandingDAO(Connection conn) {
        this.conn = conn;
    }
    public void addinstanding(LeagueStanding leagueStanding) throws SQLException{
    	PreparedStatement preparedStatement=conn.prepareStatement("Insert into league_standings values(?,?,?,?)");
    	preparedStatement.setInt(1, leagueStanding.getTeamId());
    	preparedStatement.setInt(2, leagueStanding.getLeagueId());
    	preparedStatement.setString(3, leagueStanding.getTeamName());
    	preparedStatement.setInt(4, leagueStanding.getPoints());
    	preparedStatement.executeUpdate();
    }
    public List<LeagueStanding> getStandingsByLeague() throws SQLException {
        List<LeagueStanding> standings = new ArrayList<>();
     PreparedStatement preparedStatement=conn.prepareStatement("select * from league_standings order by points desc ");
    ResultSet resultSet=preparedStatement.executeQuery();
    while(resultSet.next()) {
    	standings.add(new LeagueStanding(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4)));
        
        }
        return standings;
    }

   
}
