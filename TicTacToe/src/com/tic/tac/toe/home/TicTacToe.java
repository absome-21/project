package com.tic.tac.toe.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.exam.dataWrappers.PlayerDW;
import com.exam.dbconnection.DBConnection;
import com.exam.tictactoe.TicTacToeController;

/**
 * Servlet implementation class TicTacToe
 */
@WebServlet(description = "Tic Tact Toe", urlPatterns = { "/" , "/Tic.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="pankaj")})

public class TicTacToe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER =Logger.getLogger(TicTacToeController.class.getName());

    private static PlayerDW player1;
    private static PlayerDW player2;
    private PlayerDW selectedPlayer = player1;
	private Map<String, String> board = new HashMap<>();
    
	Connection conn;
	
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicTacToe() {
        super();
        board  = new HashMap<>();
    	player1 = new PlayerDW();
    	player1.setPlayerType(1);

    	player2 = new PlayerDW();
    	player2.setPlayerType(2);
    	selectedPlayer = player1;

    	board.put("1", "");
    	board.put("2", "");
    	board.put("3", "");
    	board.put("4", "");
    	board.put("5", "");
    	board.put("6", "");
    	board.put("7", "");
    	board.put("8", "");
    	board.put("9", "");
        // TODO Auto-generated constructor stub
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
    	doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String action = request.getServletPath();
    	LOGGER.log(Level.SEVERE, "dasd ", action);
    	System.out.println("dsadsaa");
    	try {
    		switch(action) {
			case "/start":
	    		request.setAttribute("exist", "none");
		    	selectedPlayer  = new PlayerDW();
				setSelectedPlayer(player1);
    			showNewForm(request,response);
    			break;
			case "/play":
		    	board.put("1", "");
		    	board.put("2", "");
		    	board.put("3", "");
		    	board.put("4", "");
		    	board.put("5", "");
		    	board.put("6", "");
		    	board.put("7", "");
		    	board.put("8", "");
		    	board.put("9", "");
		    	player1.getMoves().clear();
		    	player2.getMoves().clear();
		    	selectedPlayer  = new PlayerDW();
				setSelectedPlayer(player1);
	    		request.setAttribute("invalid", "none");
    			showBoard(request,response);
    			break;
			case "/new":
	    		request.setAttribute("exist", "none");
    			showNewForm(request,response);
    			break;
			case "/insert":
    			savePlayer(request,response);
    			break;
			case "/move":
				playerMove(request,response);
				break;
			case "/winner":
				showWinner(request,response, selectedPlayer);
				break;
    		default:
    			RequestDispatcher dispatcher = request.getRequestDispatcher("views/main.jsp");
    			dispatcher.forward(request, response);
    			break;
    		}
    	} catch(SQLException e) {
    		LOGGER.log(Level.SEVERE, "Sql Error", e);
    	}
    }
    
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("views/player.jsp");
    	request.setAttribute("player", selectedPlayer);
    	dispatcher.forward(request,response);
    }
    private void showBoard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("views/board.jsp");
    	request.setAttribute("player", getSelectedPlayer());
    	request.setAttribute("board", board);
    	dispatcher.forward(request,response);
    }
    
    private void savePlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	String playerName = request.getParameter("playerName");
    	Integer playerType = Integer.parseInt(request.getParameter("playerType"));
    	
    	TicTacToeController ticContrl = new TicTacToeController();
    	PlayerDW player = new PlayerDW();
    	player = ticContrl.getPlayer(playerName);
		request.setAttribute("exist", "none");
    	if(!player.isExist()) {
        	ticContrl.savePlayer(playerName, playerType);
	    	if(playerType == 1) {
	    		player1.setPlayerName(playerName);
	    	} else {
	    		player2.setPlayerName(playerName);
	    	}
	    	selectedPlayer = new PlayerDW();
	    	if(playerType == 1) {
		    	setSelectedPlayer(player2);
	    		showNewForm(request, response);
	    	} else {
	    		request.setAttribute("invalid", "none");

		    	setSelectedPlayer(player1);
	    		showBoard(request, response);
	    	}
    	} else {
    		selectedPlayer.setExist(player.isExist());
    		request.setAttribute("exist", "inline");
    		showNewForm(request, response);
    	}
    }
    
    private void playerMove(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	if(request.getParameter("playerMove") != null && !request.getParameter("playerMove").isEmpty()) {
	    	Integer move = Integer.parseInt(request.getParameter("playerMove"));
	    	System.out.println("p1 move : " + player1.getMoves());
	    	System.out.println("p2 move : " + player2.getMoves());
	    	System.out.println("select move : " + selectedPlayer.getMoves());
	    	if(!player1.getMoves().contains(move) && !player2.getMoves().contains(move)) {
	    	
	    		request.setAttribute("invalid", "none");

		    	System.out.println("select move : " + selectedPlayer.getPlayerType());

	        	TicTacToeController ticContrl = new TicTacToeController();
	        	boolean playerWins = false;
	    		if(selectedPlayer.getPlayerType() == 1) {
	    			player1.getMoves().add(move);
	    			playerWins = ticContrl.checkMoves(player1);		        	
	    		} else {
	    			player2.getMoves().add(move);
	    			playerWins = ticContrl.checkMoves(player2);		        	
	    		}
	        	
	        	if(playerWins) {
	    			selectedPlayer.setWinner(true);
	    			showWinner(request, response, selectedPlayer);
	        	} else {
	        		System.out.println("player typr :" + selectedPlayer.getPlayerType());
	        		Integer moveLimit = player1.getMoves().size() + player2.getMoves().size();
		    		if(moveLimit==9) {
		    			selectedPlayer.setWinner(false);
		    			showWinner(request, response, selectedPlayer);
		    		} else {
		    	    	if(selectedPlayer.getPlayerType() == 1) {
//		    	    		setPlayer1(selectedPlayer);
//		    	    		selectedPlayer = new PlayerDW();
//		    	    		selectedPlayer.getMoves().clear();
		    	    		setSelectedPlayer(player2);
		    	    		getSelectedPlayer().setMoves(player2.getMoves());
		    	        	board.put(move.toString(), "X");
		    	    	} else {
//		    	    		setPlayer2(selectedPlayer);
//		    	    		selectedPlayer = new PlayerDW();
//		    	    		selectedPlayer.getMoves().clear();
		    	    		setSelectedPlayer(player1);
		    	    		getSelectedPlayer().setMoves(player1.getMoves());
		    	        	board.put(move.toString(), "O");
		    	    	}
		    			showBoard(request, response);
		    		}
	        	}
	    	} else {
	    		System.out.println(" invalid ");
	    		request.setAttribute("invalid", "inline");
				showBoard(request, response);
	    	}
    	}
    }
    private void showWinner(HttpServletRequest request, HttpServletResponse response, PlayerDW winner) throws IOException, ServletException, SQLException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("views/winner.jsp");
    	request.setAttribute("player", winner);
    	if(winner.isWinner()) {
    		request.setAttribute("result", winner.getPlayerName()+" Wins!");
    	} else {
    		request.setAttribute("result", "DRAW!");
    	}

    	TicTacToeController ticContrl = new TicTacToeController(); 
    	ticContrl.addScore(winner);
    	
    	List<PlayerDW> playerList = new ArrayList<>();
    	playerList = ticContrl.getPlayerList();

    	request.setAttribute("playerList", playerList);
    	dispatcher.forward(request,response);
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
