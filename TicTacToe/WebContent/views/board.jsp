<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
	<%@include file="../css/style.css"%>
</style>
</head>
<body>

	<br>
	<h2 align="center" style="font-size: 42px">PLAY TIC TAC TOE</h2>
	<br>
	<div align="center">
	    <h3 style="font-size: 30px!important; margin:10px;">${player.getPlayerName()}</h3>
	 </div>
	 <div align="center">
	 	<form action="move" method="post">
		 	<table>  
		    	<tr>
		    		<td  style="height:20px;" align="center">Move</td>
		    		<td  style="height:20px;">
		    			<input type="text" id="playerMove" name="playerMove" onkeyup="onInputMove(); this.select();" class="move-num"/>
		    		</td>
					<td align="center"  style="height:20px;">
						<button style="background-color: #0277bd; width: 70px; height: 40px; color: white;" type="submit" value="Save">Ok</button>
					</td>
				</tr>
				<tr><td style="height:20px;"></td><td colspan="2" style="height:20px; display: ${invalid}; text-align:center!important;" align="center" ><label style="color: red; text-align: center; " >Invalid Move!</label></td></tr>  
		    </table> 
	 	</form>
	 </div>
	<div align="center">
		<table>
			<tr>
				<td>
					<div>
						<input type="button" class="board-box " style="width: 100px; height: 100px; font-size: 70px;" name="moveOne" value="${board['1']}"/> 
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box vert" style="width: 100px; height: 100px" name="moveTwo" value="${board['2']}"/>
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box " style="width: 100px; height: 100px" name="moveThree" value="${board['3']}"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<input type="button" class="board-box hori" style="width: 100px; height: 100px" name="moveFour" value="${board['4']}"/>
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box vert hori" style="width: 100px; height: 100px" name="moveFive" value="${board['5']}"/>
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box hori" style="width: 100px; height: 100px" name="moveSix" value="${board['6']}"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<input type="button" class="board-box " style="width: 100px; height: 100px" name="moveSeven" value="${board['7']}"/>
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box vert" style="width: 100px; height: 100px" name="moveEight" value="${board['8']}"/>
					</div>
				</td>
				<td>
					<div>
						<input type="button" class="board-box " style="width: 100px; height: 100px" name="moveNine" value="${board['9']}"/>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
function onInputMove() {
	
	var move = document.getElementById("playerMove").value;
	var regex=/^[0-9]+$/;
	if(move.match(regex)) {
		if(move>9) {
			document.getElementById("playerMove").value = 9;
		} else if(move<1) {
			document.getElementById("playerMove").value = 1;
		}
	} else {
		document.getElementById("playerMove").value = 1;
	}
}
</script>
</html>