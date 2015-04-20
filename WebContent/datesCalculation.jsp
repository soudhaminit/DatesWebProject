<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dates Calculation</title>
<link href="styles/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/jquery-1.11.2.min.js" ></script>
</head>
<body>
<div id=fixed>
<form action="/DatesCalWeb/DateController" method="post">
<h4>Please provide the Start Date and End Date in the range of 1900 - 2010 and strictly dd MM yyyy format only.</h4>
<label for="startdate"><span id="mandatory">*</span>Start Date :</label>
<input type="text" name="startdate" size="15" maxlength="10" value="${startdate }" id="startdate" placeholder="DD MM YYYY"/>
<label for="enddate"><span id="mandatory">*</span>End Date :</label>
<input type="text" name="enddate" size="15" maxlength="10" value="${enddate }" id="enddate" placeholder="DD MM YYYY"/>
<input type="submit" value="Dates Difference" id="submitbtn"/>
<div id="msg"><c:out value="${message}"/></div>
 
<div>Dates Difference is <span id="result"><c:out value="${days}"/> </span></div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){

	$("form").submit(function(e){
		
		var errmsg="";
		//limit the input range of dates from 1900 to 2010 in the format dd/mm/yyyy
	    var dateReg = /^(([0][1-9])|([1-2][0-9])|([3][0,1]{1}))\s(([0][1-9])|([1][0-2]{1}))\s(([1][9][0-9][0-9])|([2][0][0][0-9])|([2][0][1][0]))$/;
	    
		var startDate=$("#startdate").val();
		var endDate=$("#enddate").val();
		
		if(startDate==""){
			errmsg+="Please provide the start date<br/>";
		}else if(!dateReg.test(startDate)) {
	        errmsg+='Invalid start date<br/>';
	    }
		if(endDate==""){
			errmsg+="Please provide the end date<br/>";
		}else if(!dateReg.test(endDate)){
	    	errmsg+='Invalid end date';
	    }
		
		if(errmsg.length>0) {$("#msg").html(errmsg); }
		else{
			return;
			}
		e.preventDefault();
		
	});
	
	
});


</script>
</body>
</html>