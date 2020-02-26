    <!DOCTYPE html>  
    <html>  
    <head>  
    <meta charset="ISO-8859-1">  
    <title>Insert title here</title>  
    </head>  
    <body>  
     <br>
	<h2 align="center" style="font-size: 42px">PLAY TIC TAC TOE</h2>
	<br>
	<div align="center" >
	    <form action="insert" method="post">  
	    <input type="hidden" name="playerType" value="${player.getPlayerType()}"/>
	    <h3 style="font-size: 30px!important; margin:10px;">Player ${player.getPlayerType()}</h3>
	    <table>  
	    <tr>
	    	<td>Player Name </td>
    		<td>
    			<input type="text" name="playerName" style="height: 30px; width: 200px; font-size: 20px; font-family:sans-serif;"/>
    		</td>
			<td colspan="2" align="center">
			<button style="background-color: #0277bd; width: 70px; height: 40px; color: white;" type="submit" value="Save">Save</button>
			</td>
		</tr>  
		<tr>
			<td colspan="3" align="center" style="height: 20px; display: ${exist}"><label style="color: red;">Player Exist!</label></td>
		</tr>
	    </table>  
	    </form>  
     </div>
      
    </body>  
    </html>  