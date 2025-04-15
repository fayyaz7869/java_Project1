package com.info.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.info.model.Sports_Leauge;

public class Sports_league_dao {

   // private Connection conn;
    private  Connection conn;
    public Sports_league_dao(Connection conn) {
        this.conn = conn;
    }

    public void addLeague(Sports_Leauge league) throws SQLException {
       PreparedStatement stmt=conn.prepareStatement("Insert into leagues values(?,?,?,?,?)");
        
            stmt.setInt(1, league.getL_id());
            stmt.setString(2, league.getL_name());
            stmt.setString(3, league.getSport_type());
            stmt.setDate(4, league.getStart_date());
            stmt.setDate(5, league.getEnd_date());
            stmt.executeUpdate();
        
    }

    public List<Sports_Leauge> getAllLeagues() throws SQLException {
        List<Sports_Leauge> leagues = new ArrayList<Sports_Leauge>();
        String sql = "SELECT * FROM leagues";
        try (Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leagues.add(new Sports_Leauge(
                    rs.getInt("l_id"),
                    rs.getString("l_name"),
                    rs.getString("Sport_type"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date")
                ));
            }
        }
        return leagues;
    }

    public void updateLeague(Sports_Leauge league) throws SQLException {
        String sql = "UPDATE leagues SET l_name = ?, s_type = ?, start_date = ?, end_date = ? WHERE l_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, league.getL_name());
            stmt.setString(2, league.getSport_type());
            stmt.setDate(3, league.getStart_date());
            stmt.setDate(4, league.getEnd_date());
            stmt.setInt(5, league.getL_id());
            stmt.executeUpdate();
        }
    }

    public void deleteLeague(int leagueId) throws SQLException {
        String sql = "DELETE FROM leagues WHERE l_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, leagueId);
            stmt.executeUpdate();
        }
    }
}
