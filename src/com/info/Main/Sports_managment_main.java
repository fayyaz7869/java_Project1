package com.info.Main;


import java.sql.Connection;
import java.io.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.info.model.LeagueStanding;
import com.info.model.Sports_Leauge;
import com.info.model.Sports_Matches;
import com.info.model.Sports_Players;
import com.info.model.Sports_teams;
import com.info.service.LeagueStandingService;
import com.info.service.Sports_Matches_service;
import com.info.service.Sports_Players_service;
import com.info.service.Sports_Teams_service;
import com.info.service.Sports_leauge_service;

public class Sports_managment_main {
    private static final String url = "jdbc:mysql://localhost:3306/p";
    private static final String user = "root";
    private static final String password = "Fayyaz@78692";

    public static void main(String[] args) throws SQLException {
    	
    	
        Scanner scanner = new Scanner(System.in);
        

       

        int choice;

while (true) {
    System.out.println("1. Signup");
    System.out.println("2. Login");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
    choice = scanner.nextInt();
    scanner.nextLine(); 
    
    switch (choice) {
        case 1:
            signup(scanner);
            break;
        case 2:
            login(scanner);
            break;
        case 3:
            System.out.println("Exiting...");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
    }
}
    }

        
     
        
      

    private static void signup(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password1 = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password1);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Signup successful!");
            } else {
                System.out.println("Signup failed. Try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String password = null;
        try {
            Console console = System.console();
            if (console != null) {
                char[] chars = console.readPassword("Enter password: ");
                password = new String(chars);
            } else {
                // If console is null (like in IDEs), fall back to visible input
                System.out.print("Enter password (input will be visible): ");
                password = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error while reading password: " + e.getMessage());
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, Sports_managment_main.password)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login successful! Welcome, " + username);

                // Establish service connections
                Connection connection = DriverManager.getConnection(url, user, Sports_managment_main.password);
                Sports_leauge_service sports_league_service = new Sports_leauge_service(connection);
                Sports_Matches_service sports_Matches_service = new Sports_Matches_service(connection);
                Sports_Players_service sports_Players_service = new Sports_Players_service(connection);
                Sports_Teams_service sports_teams_service = new Sports_Teams_service(connection);
                LeagueStandingService leagueStandingService = new LeagueStandingService(connection);

                while (true) {
                    System.out.println("----------------------------------------------");
                    System.out.println("|         🏆 SPORTS MANAGEMENT SYSTEM        |");
                    System.out.println("----------------------------------------------");
                    System.out.println("| 1️⃣  Manage Leagues                         |");
                    System.out.println("| 2️⃣  Manage Teams                           |");
                    System.out.println("| 3️⃣  Manage Players                         |");
                    System.out.println("| 4️⃣  Manage Matches                         |");
                    System.out.println("| 5️⃣  View Standings                         |");
                    System.out.println("| 6️⃣  Exit                                   |");
                    System.out.println("----------------------------------------------");
                    System.out.print("Choose an option: ");

                    int choice1 = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    switch (choice1) {
                        case 1:
                            manageLeagues(scanner, sports_league_service);
                            break;
                        case 2:
                            manageTeams(scanner, sports_teams_service);
                            break;
                        case 3:
                            managePlayers(scanner, sports_Players_service);
                            break;
                        case 4:
                            manageMatches(scanner, sports_Matches_service);
                            break;
                        case 5:
                            viewStandings(scanner, leagueStandingService);
                            break;
                        case 6:
                            System.out.println("👋 Thank you for using the Sports Management System. Goodbye!");
                            return;
                        default:
                            System.out.println("❌ Invalid choice! Please enter a valid option.");
                    }
                }
            } else {
                System.out.println("❌ Invalid credentials. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("🔴 Database error: " + e.getMessage());
        }
    }

    private static void manageLeagues(Scanner scanner, Sports_leauge_service sports_league_service) throws SQLException {
        System.out.println("\n⚽ LEAGUE MANAGEMENT ⚽");
        System.out.println("------------------------------------------------");
        System.out.println("| 1. Add League    | 2. Show Leagues           |");
        System.out.println("| 3. Update League | 4. Remove League          |");
        System.out.println("| 5. Back                                      |");
        System.out.println("------------------------------------------------");
        System.out.print("Enter choice: ");
        int leagueChoice = scanner.nextInt();
        scanner.nextLine();

        switch (leagueChoice) {
            case 1:
                System.out.println("Enter League ID:");
                int l_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter League Name:");
                String l_name = scanner.nextLine();
                System.out.println("Enter Sport Type:");
                String s_type = scanner.nextLine();
                System.out.println("Enter Start Date (YYYY-MM-DD):");
                Date s_date = Date.valueOf(scanner.next());
                System.out.println("Enter End Date (YYYY-MM-DD):");
                Date e_date = Date.valueOf(scanner.next());

                sports_league_service.addinleague(new Sports_Leauge(l_id, l_name, s_type, s_date, e_date));
                System.out.println("✅ League added successfully!");
                break;

            case 2:
                List<Sports_Leauge> leagues = sports_league_service.showLeauges();
                if (leagues.isEmpty()) {
                    System.out.println("📜 No leagues available.");
                } else {
                    System.out.println("------------------------------------------------------------");
                    System.out.printf("| %-5s | %-20s | %-15s | %-12s | %-12s |%n", "ID", "Name", "Sport Type", "Start Date", "End Date");
                    System.out.println("------------------------------------------------------------");
                    for (Sports_Leauge league : leagues) {
                        System.out.printf("| %-5d | %-20s | %-15s | %-12s | %-12s |%n", 
                                          league.getL_id(), league.getL_name(), league.getSport_type(), 
                                          league.getStart_date(), league.getEnd_date());
                    }
                    System.out.println("------------------------------------------------------------");
                }
                break;

            case 3:
                System.out.println("Enter League ID to Update:");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter New League Name:");
                String newName = scanner.nextLine();
                System.out.println("Enter New Sport Type:");
                String newSportType = scanner.nextLine();
                System.out.println("Enter New Start Date (YYYY-MM-DD):");
                Date newStartDate = Date.valueOf(scanner.next());
                System.out.println("Enter New End Date (YYYY-MM-DD):");
                Date newEndDate = Date.valueOf(scanner.next());

                sports_league_service.Updateleauges(new Sports_Leauge(updateId, newName, newSportType, newStartDate, newEndDate));
                System.out.println("✅ League updated successfully!");
                break;

            case 4:
                System.out.println("Enter League ID to Remove:");
                int removeId = scanner.nextInt();
                scanner.nextLine();
                sports_league_service.removeleauge(removeId);
                System.out.println("✅ League removed successfully!");
                break;

            case 5:
                return;

            default:
                System.out.println("❌ Invalid choice! Please try again.");
        }
    }
    
    private static void manageTeams(Scanner scanner, Sports_Teams_service sports_teams_service) throws SQLException {
        System.out.println("🏅 TEAM MANAGEMENT");
        System.out.println("-----------------------------------------");
        System.out.println("| 1. Add Team    | 2. Show Teams        |");
        System.out.println("| 3. Update Team | 4. Remove Team       |");
        System.out.println("| 5. Back                               |");
        System.out.println("-----------------------------------------");
        System.out.print("Enter choice: ");
        int teamChoice = scanner.nextInt();
        scanner.nextLine();

        switch (teamChoice) {
            case 1:
                System.out.println("Enter Team ID:");
                int t_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Team Name:");
                String t_name = scanner.nextLine();
                System.out.println("Enter Home City:");
                String home_city = scanner.nextLine();
                System.out.println("Enter League ID:");
                int l_id = scanner.nextInt();
                scanner.nextLine();
                
                sports_teams_service.insertTeams(new Sports_teams(t_id, t_name, home_city, l_id));
                System.out.println("✅ Team added successfully!");
                break;

            case 2:
                List<Sports_teams> teams = sports_teams_service.showteams();
                if (teams.isEmpty()) {
                    System.out.println("📜 No teams available.");
                } else {
                    System.out.println("------------------------------------------------------------");
                    System.out.printf("| %-5s | %-20s | %-15s | %-5s |%n", "ID", "Name", "Home City", "League ID");
                    System.out.println("-------------------------------------------------------------");
                    for (Sports_teams team : teams) {
                        System.out.printf("| %-5d | %-20s | %-15s | %-5d |%n",
                                          team.getT_id(), team.getT_name(), team.getHome_city(), team.getL_id());
                    }
                    System.out.println("-------------------------------------------------------------");
                }
                break;

            case 3:
                System.out.println("🏅 TEAM UPDATION...");
                System.out.println("-----------------------------------------------------");
                System.out.println("| 1. update team name   | 2. update home city       |");
                System.out.println("| 3. Update league id                               |");
                System.out.println("-----------------------------------------------------");
                System.out.print("Enter choice: ");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch(updateChoice) {
                case 1:{
                	System.out.println("Enter team id to update team name:");
                	int id=scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter new team name:");
                	String name=scanner.nextLine();
                	
                	sports_teams_service.changeT_name(name, id);
                	break;
               	
                }
                case 2:{
                	System.out.println("Enter team id to update Home city:");
                	int id=scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter new Home city:");
                	String home_city1=scanner.nextLine();
                	sports_teams_service.changeT_home_city(home_city1, id);
                	break;               	
                	
                }
                
                case 3:{
                	System.out.println("Enter team id to upate league id");
                	int t_id1=scanner.nextInt();
                	System.out.println("Enter new league id");
                	int l_id1=scanner.nextInt();
                	
                	sports_teams_service.changeT_L_id(t_id1, l_id1);
                	break;
                }
                
                }
                break;

            case 4:
                System.out.println("Enter Team ID to Remove:");
                int removeTeamId = scanner.nextInt();
                scanner.nextLine();
                sports_teams_service.deleteTeam(removeTeamId);
                System.out.println("✅ Team removed successfully!");
                break;

            case 5:
                return;

            default:
                System.out.println("❌ Invalid choice! Please try again.");
        }
    }

    private static void managePlayers(Scanner scanner, Sports_Players_service sports_Players_service) throws SQLException {
        System.out.println("⚽ PLAYER MANAGEMENT");
        System.out.println("1. Add Player\n2. Show Players\n3. Update Player\n4. Remove Player\n5. Back");
        System.out.print("Enter choice: ");
        int playerChoice = scanner.nextInt();
        scanner.nextLine();

        switch (playerChoice) {
            case 1:
                System.out.println("Enter Player ID:");
                int p_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Player Name:");
                String p_name = scanner.nextLine();
                System.out.println("Enter Player Age:");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Player Position:");
                String position = scanner.nextLine();
                System.out.println("Enter Team ID:");
                int t_id = scanner.nextInt();
                scanner.nextLine();
                
                sports_Players_service.insertPlayers(new Sports_Players(p_id, p_name, age, position, t_id));
                System.out.println("✅ Player added successfully!");
                break;

            case 2:
                List<Sports_Players> players = sports_Players_service.showplayers();
                if (players.isEmpty()) {
                    System.out.println("📜 No players available.");
                } else {
                    for (Sports_Players player : players) {
                        System.out.println(player);
                    }
                }
                break;

            case 3:
               
                System.out.println("🏅 PLAYERS UPDATION...");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1.Update player name     |2.Update player age  |");
                System.out.println("| 3.Update player position |4.Update team id     |");
                System.out.println("--------------------------------------------------");
                System.out.print("Enter choice: ");
                int teamChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch(teamChoice) {
                case 1:{
                	System.out.println("Enter Player Id to update name: ");
                	int id=scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter new name: ");
                	String name=scanner.nextLine();
                	
                	sports_Players_service.changeP_Name(name, id);
                	   System.out.println("✅ Player updated successfully!");
                	break;
                	
                }
                case 2:{
                	System.out.println("Enter Player Id to update age: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter nem age: ");
                	int age1=scanner.nextInt();
                	sports_Players_service.changeP_age(age1, id);
             	   System.out.println("✅ Player updated successfully!");
             	   break;
                }
                case 3:{
                	System.out.println("Enter player id to update position");
                	int id=scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter new position of player: ");
                	String position1=scanner.nextLine();
                	scanner.nextLine();
                	sports_Players_service.changeP_position(position1, id);
             	   System.out.println("✅ Player updated successfully!");
             	   break;
                	
                }
                case 4:{
                	
                	System.out.println("Enter player id to update team id: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter new team id: ");
                	int t_id2=scanner.nextInt();
                	sports_Players_service.changeP_t_id(t_id2, id);
             	   System.out.println("✅ Player updated successfully!");
             	   break;
                }
                	
                }
            	
                break;

            case 4:
                System.out.println("Enter Player ID to Remove:");
                int removePlayerId = scanner.nextInt();
                scanner.nextLine();
                sports_Players_service.deleteplayer(removePlayerId);
                System.out.println("✅ Player removed successfully!");
                break;

            case 5:
                return;

            default:
                System.out.println("❌ Invalid choice! Please try again.");
        }
    }


    private static void manageMatches(Scanner scanner, Sports_Matches_service sports_Matches_service) throws SQLException {
        System.out.println("⚽ MATCH MANAGEMENT");
        System.out.println("1. Add Match\n2. Show Matches\n3. Update Match\n4. Remove Match\n5. Back");
        System.out.print("Enter choice: ");
        int matchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (matchChoice) {
            case 1:
                System.out.println("Enter Match ID:");
                int match_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter League ID:");
                int league_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Team 1 ID:");
                int team1_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Team 2 ID:");
                int team2_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Match Date (YYYY-MM-DD):");
                Date match_date = Date.valueOf(scanner.nextLine());
                System.out.println("Enter Location:");
                String location = scanner.nextLine();
                System.out.println("Enter Team 1 Score:");
                int score1=scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter team 2 Score: ");
                int score2=scanner.nextInt();
                
                System.out.println("Enter Winner Team ID:");
               int winer_id=scanner.nextInt();
            
                
                sports_Matches_service.insertMatches(new Sports_Matches(match_id, league_id, team1_id, team2_id, match_date, location, score1, score2, winer_id));
                System.out.println("✅ Match added successfully!");
                break;

            case 2:
                List<Sports_Matches> matches = sports_Matches_service.showmatches();
                if (matches.isEmpty()) {
                    System.out.println("📜 No matches available.");
                } else {
                    for (Sports_Matches match : matches) {
                        System.out.println(match);
                    }
                }
                break;

            case 3:
            	System.out.println("--------------------------------------------------");
                System.out.println("|            🏅 PLAYERS UPDATION...              |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1.Update league id       |2.Update team1 id    |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 3.Update team2 id        |4.Update match  date |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 5.Update match location  |6.Update team1 score |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 7.Update team2 score     |8.Update wiiner T_ID |");
                System.out.println("--------------------------------------------------");
                System.out.print("Enter choice: ");
                int teamChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch(teamChoice) {
                case 1:{
                	System.out.println("Enter match id to update league id");
                	int id=scanner.nextInt();
                	System.out.println("Enter new league id: ");
                	int league=scanner.nextInt();
                	
                	sports_Matches_service.changeM_l_id(league, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                	}
                case 2:{
                	System.out.println("Enter match id to update team1 id: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter new team1 id");
                	int team1=scanner.nextInt();
                	sports_Matches_service.changeM_team1_id(team1, id);               	
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                case 3:{
                	System.out.println("Enter match id to update team2 id: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter new team2 id: ");
                	int team2=scanner.nextInt();
                	sports_Matches_service.changeM_team2_id(team2, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                case 4:{
                	System.out.println("Enter match id to update Match date: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter new Match Date: ");
                	  System.out.println("Enter new Match Date (YYYY-MM-DD):");
                      Date new_date = Date.valueOf(scanner.nextLine());
                      sports_Matches_service.changeM_date(id, new_date);
                      System.out.println("✅ Match updated successfully!");
                      break;
                }
                case 5:{
                	System.out.println("Enter match id to update location: ");
                	int id=scanner.nextInt();
                	scanner.nextLine();
                	System.out.println("Enter new location :");
                	String locationString=scanner.nextLine();
                	sports_Matches_service.changeM_location(locationString, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                case 6:{
                	System.out.println("Enter match id to update team1_score :");
                	int id=scanner.nextInt();
                	System.out.println("Enter new score of team1:");
                	int scores1=scanner.nextInt();
                	sports_Matches_service.changeM_team1_score(scores1, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                case 7:{
                	System.out.println("Enter match id to update team2_score: ");
                	int id=scanner.nextInt();
                	System.out.println("Enter new score of team2: ");
                	int scores2=scanner.nextInt();
                	sports_Matches_service.changeM_team2_score(scores2, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                case 8:{
                	System.out.println("Enter match id to update winner team id: ");
                	int id= scanner.nextInt();
                	
                	System.out.println("enter new wiiner team id: ");
                	int winner=scanner.nextInt();
                	
                	sports_Matches_service.changeM_winner(winner, id);
                	System.out.println("✅ Match updated successfully!");
                	break;
                }
                
                }
              
                break;

            case 4:
                System.out.println("Enter Match ID to Remove:");
                int removeMatchId = scanner.nextInt();
                scanner.nextLine();
                sports_Matches_service.deleteMatches(removeMatchId);
                System.out.println("✅ Match removed successfully!");
                break;

            case 5:
                return;

            default:
                System.out.println("❌ Invalid choice! Please try again.");
        }
    }
    
    private static void viewStandings(Scanner scanner, LeagueStandingService leagueStandingService) throws SQLException {
        System.out.println("\n🏆 VIEW LEAGUE STANDINGS 🏆");
        System.out.println("---------------------------------------");
        System.out.println("1.Add in standing |2.Display standing |");
        System.out.println("---------------------------------------");
        int choic=scanner.nextInt();
        
        switch(choic) {
        
        case 1:{
        	System.out.println("Enter team ID ");
        	int id1=scanner.nextInt();
        	scanner.nextLine();
        	System.out.println("Enter league id");
        	int league_id=scanner.nextInt();
        	scanner.nextLine();
        	System.out.println("Enter team name: ");
        	String nameString=scanner.nextLine();
        	System.out.println("Enter points: ");
        	int points=scanner.nextInt();
        	leagueStandingService.insertStanding(new LeagueStanding(id1, league_id, nameString, points));
        	
            System.out.println("✅ Standing added successfully!");
            break;
        }
        case 2:{
        
        	List<LeagueStanding> standings = leagueStandingService.displayStandings();

        	if (standings.isEmpty()) {
        	    System.out.println("📜 No league standings available.");
        	} else {
        	    System.out.println("-----------------------------------------------------------------");
        	    System.out.printf("| %-10s | %-10s | %-25s | %-7s |%n", "TeamId", "League ID", "Team Name", "Points");
        	    System.out.println("-----------------------------------------------------------------");
        	    
        	    for (LeagueStanding standing : standings) {
        	        System.out.printf("| %-10d | %-10d | %-25s | %-7d |%n", 
        	                        standing.getTeamId(), standing.getLeagueId(),standing.getTeamName(), standing.getPoints());
        	    }
        	    
        	    System.out.println("-----------------------------------------------------------------");
        	}

            break;
        }
        
        
        }
   
    }
		
	
    }
    


