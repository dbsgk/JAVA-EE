<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>	
	table{
		margin:10px auto;
		border:1px solid #ddd;
	}
	td{
		padding:5px;
		text-align:center;
	}
	input{
		text-align:center;
		height:22px;
		}
	.fieldname{
		width:20px;
	}
	.btn{
		height:25px;
		font-size:12px;
	}

</style>
</head>
<body>
	<form name="calcForm" action="sum.jsp">
	<table>
		<tr>
			<td class="fieldname">X</td>
			<td><input type="text" name="x"></td>
		</tr>
		<tr>
			<td class="fieldname">Y</td>
			<td><input type="text" name="y"></td>
		</tr>
		<tr>
			<td colspan="2" class="buttonField"><input type="submit" class="btn" value="합 구하기"> <input type="reset" class="btn"></td>
		</tr>
	</table>
	</form>
</body>
</html>