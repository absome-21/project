package com.exam.tictactoe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.dataWrappers.PlayerDW;
import com.exam.dbconnection.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class TicTacToeController{
    private PlayerDW player1;
    private PlayerDW player2;
    private PlayerDW selectedPlayer;
	Connection conn;
    public void initializePlayer() {
    	player1 = new PlayerDW();
    	player1.setPlayerType(1);

    	player2 = new PlayerDW();
    	player2.setPlayerType(1);
    	selectedPlayer = player1;
    }

    
    public void savePlayer(String playerName, Integer playerTypeNum) throws SQLException, IOException, ServletException {
    	PreparedStatement pStmt = null;
//    	ResultSet rs = null;
    	try {
	    	conn = DBConnection.createNewDBconnection();
	    	String query = "Insert INTO PLAYER (playerName, playerTypeNum) values(?, ?)";
	    	System.out.println("query :" + query);
	    	pStmt = conn.prepareStatement(query);
	    	pStmt.setString(1,playerName);
	    	pStmt.setInt(2,playerTypeNum);
	    	pStmt.execute();
	    	pStmt.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		conn.close();
    	}
    }
    
	public boolean checkMoves(PlayerDW player) {
    	Integer[][] winMoves ={ {1,2,3}, {1,4,7},{3,6,9},{1,5,9},{3,5,7},{4,5,6},{2,5,8},{7,8,9}};
    	boolean playerWins = false;
    	for(int i = 0; i<winMoves.length; i++) {
    		for (int j = 0; j < 3; j++) {
        		System.out.println("winmove : " + player.getMoves());
        		playerWins = player.getMoves().contains(winMoves[i][j]);
        		if(!playerWins) {
        			break;
        		}
        		
			}
    		if(playerWins) {
    			break;
    		}
    	}
//    	player.set/Winner(playerWins);
    	System.out.println("wiiner : " + playerWins);
    	return playerWins;
    }
    

    public void addScore(PlayerDW player) throws SQLException{
    	PreparedStatement pStmt = null;
    	ResultSet rs = null;
    	try {
	    	conn = DBConnection.createNewDBconnection();
	    	String query = "SELECT * FROM PLAYER WHERE playerName = ?";
	    	System.out.println("query :" + query);
	    	pStmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    	pStmt.setString(1,player.getPlayerName());
	    	rs = pStmt.executeQuery();
	    	if(rs.next()) {
	    		rs.updateInt("score", rs.getInt("score") + 1);
	    		rs.updateRow();
	    	}
	    	rs.close();
	    	pStmt.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		conn.close();
    	}
    }
    public PlayerDW getPlayer(String playerName) throws SQLException, IOException, ServletException {
    	PlayerDW player = new PlayerDW();
    	PreparedStatement pStmt = null;
    	ResultSet rs = null;
    	try {
	    	conn = DBConnection.createNewDBconnection();
	    	String query = "SELECT * from PLAYER WHERE playerName like ?";
	    	System.out.println("query :" + query);
	    	pStmt = conn.prepareStatement(query);
	    	pStmt.setString(1, playerName);
	    	rs = pStmt.executeQuery();
	    	if(rs.next()) {
	    		System.out.println("dsada");
	    		player.setPlayerNum(rs.getInt("playerId"));
	    		player.setPlayerName(rs.getString("playerName"));
	    		player.setPlayerName(rs.getString("score"));
	    		player.setExist(true);
	    	} else {
	    		player.setExist(false);
	    	}
	    	rs.close();
	    	pStmt.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		conn.close();
    	}
		return player;
    }
    public List<PlayerDW> getPlayerList() throws SQLException, IOException, ServletException {
    	List<PlayerDW> playerList = new ArrayList<PlayerDW>();
    	PreparedStatement pStmt = null;
    	ResultSet rs = null;
    	try {
	    	conn = DBConnection.createNewDBconnection();
	    	String query = "SELECT * from PLAYER ORDER BY score DESC";
	    	System.out.println("query :" + query);
	    	pStmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	rs = pStmt.executeQuery();
	    	while(rs.next()) {
	    		PlayerDW player = new PlayerDW();
	    		player.setPlayerName(rs.getString("playerName"));
	    		player.setScore(rs.getInt("score"));
	    		playerList.add(player);
	    	}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
	    	rs.close();
	    	pStmt.close();
    		conn.close();
    	}
		return playerList;
    }
	public PlayerDW getPlayer1() {
		return player1;
	}
	public void setPlayer1(PlayerDW player1) {
		this.player1 = player1;
	}
	public PlayerDW getPlayer2() {
		return player2;
	}
	public void setPlayer2(PlayerDW player2) {
		this.player2 = player2;
	}

	public PlayerDW getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(PlayerDW selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}
}
