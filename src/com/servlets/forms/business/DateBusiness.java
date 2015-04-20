package com.servlets.forms.business;

import org.apache.log4j.Logger;

import com.servlets.forms.beans.DateBean;

public class DateBusiness {
	
	static Logger log = Logger.getLogger(DateBusiness.class.getName());
	private String message="";
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean validate(DateBean bean){
		checkDateValidation(bean.getStartdate(),"Start Date");
		checkDateValidation(bean.getEnddate(),"End Date");
		earliestDateCheck(bean);
		bean.setMessage(message);
		log.info("in the validate method message "+message +" length "+message.length());
		if(message.length()>0) return false;
		return true;
	}


	private void checkDateValidation(String date,String dateStr){
		
		String []dtArray=date.split(" ");
		
		String day=dtArray[0];
		String mon=dtArray[1];
		String yr=dtArray[2];
		
		String []monArray={"31","28","31","30","31","30","31","31","30","31","30","31"};
		
		boolean leapYrFlag=false;
		leapYrFlag=checkLeapYr(yr);
		if(leapYrFlag){
			monArray[1]="29";
		}
		
		/**DayDate--31 days for mon -->jan,mar,may,jul,aug,oct,dec. 
		 * No need to validate this as its covered in pattern validation */
		
		/**mon feb -->29 days for leap yr, 28 days for non leap yr and cannot be 30,31*/
		if(mon.equals("02")){
			if(Integer.parseInt(day)>Integer.parseInt(monArray[1])){
				if(leapYrFlag) message+="\n Pl correct the Day of "+dateStr +" with respect to the month. Day cannot be 30 or 31. \n";
				else message+="\n Pl correct the Day of "+dateStr +" with respect to the month. Day cannot be 29 or 30 or 31. \n";
			}
		}
		
		/**DayDate--30 days for mon -->apr,jun,sep,nov and cannot be 31*/
		if(mon.equals("04") || mon.equals("06") || mon.equals("09") || mon.equals("11")){
			if(day.equals("31")){
				message+="\n Pl correct the Day of "+ dateStr +" with respect to the month. Day cannot be 31. \n";
			}
		}
		
	}

	private void earliestDateCheck(DateBean bean){
		
		String []stdtArray=bean.getStartdate().split(" ");
		
		String stday=stdtArray[0];
		String stmon=stdtArray[1];
		String styr=stdtArray[2];
		
		String []enddtArray=bean.getEnddate().split(" ");
		
		String endday=enddtArray[0];
		String endmon=enddtArray[1];
		String endyr=enddtArray[2];
		
		/**start date has to be earliest date and end date has to be latest date */
		if(Integer.parseInt(styr)>Integer.parseInt(endyr)){
			message+="\n End Year cannot be earlier than the Start Year. \n";
		}
		if(styr.equals(endyr) && Integer.parseInt(endmon)<Integer.parseInt(stmon)){
			message+="\n End Month cannot be earlier than the Start Month. \n";
		} 
		if(stmon.equals(endmon) && Integer.parseInt(stday)>Integer.parseInt(endday)){
			message+="\n End Day cannot be earlier than the Start Day. \n";
		}
	}

	
	private boolean checkLeapYr(String yr){
		 if(Integer.parseInt(yr)%4==0){
			 return true;
		 }
		return false;
	} 


	public int calculateDays(DateBean bean){
		int days=0;
		String []stdtArray=bean.getStartdate().split(" ");
		
		String stday=stdtArray[0];
		String stmon=stdtArray[1];
		String styr=stdtArray[2];
		
		int stday_int=Integer.parseInt(stdtArray[0]);
		int stmon_int=Integer.parseInt(stdtArray[1]);
		int styr_int=Integer.parseInt(stdtArray[2]);
		
		String []enddtArray=bean.getEnddate().split(" ");
		
		String endday=enddtArray[0];
		String endmon=enddtArray[1];
		String endyr=enddtArray[2];
		
		int endday_int=Integer.parseInt(enddtArray[0]);
		int endmon_int=Integer.parseInt(enddtArray[1]);
		int endyr_int=Integer.parseInt(enddtArray[2]);
		
		String []stmonArray={"31","28","31","30","31","30","31","31","30","31","30","31"};
		String []endmonArray={"31","28","31","30","31","30","31","31","30","31","30","31"};
		
		if(checkLeapYr(styr)){
			stmonArray[1]="29";
		}
		if(checkLeapYr(endyr)){
			endmonArray[1]="29";
		}
		
		
		
		//same yr
		if(styr.equals(endyr)){
			if(stmon.equals(endmon)){
				days=endday_int-(stday_int-1);
			}else{
				for(int i=stmon_int;i<=endmon_int;i++){
					if(i==stmon_int){
					  days+=Integer.parseInt(stmonArray[stmon_int-1])-(stday_int-1);
					}else if(i==endmon_int){
					  days+=endday_int;
					}else{
					  days+=Integer.parseInt(stmonArray[i-1]);	
					}
					
				}
			}
		}else{
			for(int i=styr_int;i<=endyr_int;i++){
				if(i==styr_int){
					for(int j=stmon_int;j<=12;j++){
						if(j==stmon_int){
						  days+=Integer.parseInt(stmonArray[stmon_int-1])-(stday_int-1);
						}else{
						  days+=Integer.parseInt(stmonArray[j-1]);	
						}
					}//end of inner for loop
						
				}else if(i==endyr_int){
					for(int j=1;j<=endmon_int;j++){
						if(j==endmon_int){
						  days+=endday_int;
						}else{
						  days+=Integer.parseInt(endmonArray[j-1]);	
						}
					}//end of inner for loop
				}else{
						if(checkLeapYr(String.valueOf(i))) days+=366;
						else days+=365;

				}
			}//end of outer for loop
			
		}
		
		return days;
	} 
	 


}
