# project

web app server - apache tomcat 7.0


#DB

create database tictactoe;

use tictactoe;
	
CREATE TABLE IF NOT EXISTS player(
    playerId INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    playerName VARCHAR(255) NOT NULL,
    playerTypeNum INT,
    score INT
)  ENGINE=INNODB;
