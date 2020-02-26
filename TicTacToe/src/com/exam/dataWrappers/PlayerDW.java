package com.exam.dataWrappers;

import java.util.ArrayList;
import java.util.List;

public class PlayerDW {
	private static final long serialVersionUID = 1L;
	private Integer playerNum;
	private String playerName = "";
	private Integer playerType;
	private List<Integer> moves =  new ArrayList<>();
	private boolean winner;
	private boolean loser;
	private boolean invalidMove;
	private Integer score;
	private boolean exist;
	
	public Integer getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPlayerType() {
		return playerType;
	}
	public void setPlayerType(Integer playerType) {
		this.playerType = playerType;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public boolean isLoser() {
		return loser;
	}
	public void setLoser(boolean loser) {
		this.loser = loser;
	}
	public List<Integer> getMoves() {
		return moves;
	}
	public void setMoves(List<Integer> moves) {
		this.moves = moves;
	}
	public boolean isInvalidMove() {
		return invalidMove;
	}
	public void setInvalidMove(boolean invalidMove) {
		this.invalidMove = invalidMove;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
