<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <!--Import materialize.css-->
 <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
.column {
  float: left;
  width: 33.33%;
  padding: 10px;
  height: 300px; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
</head>
    <body>  
     <br>
	<h2 align="center" style="font-size: 42px">PLAY TIC TAC TOE</h2>
	<br>
	
	 <h3 style="font-size: 30px!important; margin:10px;" align="center">${result}</h3>
	<div class="row" style="padding-top: 20px; height:350px;">
		 <div class="column">  </div>
		 <div  class="column" align="center" style="overflow-y:auto; max-height: 350px;">
		 	<table>
		 		<thead>
			 		<tr>
			 			<td>Player Name</td>
			 			<td>Score</td>
			 		</tr>
		 		</thead>
		 		<c:forEach var = "plyr" items="${playerList}">
			 		<tr>
			 			<td>${plyr.getPlayerName()}</td>
			 			<td>${plyr.getScore()}</td>
			 		</tr>
		 		</c:forEach>
		 	</table>
		 </div>
	</div>
	<div align="center">
	
	 	<form action="play" method="post">
			<button style="background-color: #0277bd ; width: 200px; height: 40px; color: white; font-size: 24px;" type="submit" value="Save">PLAY AGAIN</button>
		</form>
	</div>
</body>
</html>