package com.servlets.forms.beans;
/**
 * Ver    	Date     		Author    		  Description
 * 1.0		16/4/2015	    Soudhamini        DateBean class is used to store the startdate and enddate from jsp
 * */
public class DateBean {
 private String startdate="";
 private String enddate="";
 private String message="";
 private int days;
 
 public void setMessage(String message) {
	this.message = message;
}

public void setDays(int days) {
	this.days = days;
}

public int getDays() {
	return days;
}

public String getMessage() {
	return message;
}

public DateBean(){}
 
 public DateBean(String startdate,String enddate){
	 this.startdate=startdate;
	 this.enddate=enddate;
 }
 
 public String getStartdate() {
	return startdate;
}

public void setStartdate(String startdate) {
	this.startdate = startdate;
}

public String getEnddate() {
	return enddate;
}

public void setEnddate(String enddate) {
	this.enddate = enddate;
}



 
}
