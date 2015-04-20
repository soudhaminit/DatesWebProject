package com.servlets.forms.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.servlets.forms.beans.DateBean;
import com.servlets.forms.business.DateBusiness;

public class DateBeanTest {
		
		
	@Test	
	public void dateValidate(){
		DateBean bean=new DateBean("11 11 1904","12 03 1906");
		DateBusiness businessObj=new DateBusiness();
		
		boolean result = businessObj.validate(bean);
		assertEquals(true,result);
	}
	
	@Test	
	public void earlyDayCheck(){
		DateBean bean=new DateBean("15 11 1904","11 11 1904");
		DateBusiness businessObj=new DateBusiness();
		
		boolean result = businessObj.validate(bean);
		assertEquals(false,result);
	}
	
	@Test	
	public void earlyMonthCheck(){
		DateBean bean=new DateBean("01 12 1904","11 11 1904");
		DateBusiness businessObj=new DateBusiness();
		
		boolean result = businessObj.validate(bean);
		assertEquals(false,result);
	}
	
	@Test	
	public void earlyYearCheck(){
		DateBean bean=new DateBean("01 12 1906","11 11 1904");
		DateBusiness businessObj=new DateBusiness();
		
		boolean result = businessObj.validate(bean);
		assertEquals(false,result);
	}
	
	@Test
	public void calculateDays1(){
		DateBean bean=new DateBean("10 01 2000","15 01 2000");
		DateBusiness businessObj=new DateBusiness();
		
		int result = businessObj.calculateDays(bean);
		assertEquals(6,result);
	}
	
	@Test
	public void calculateDaysNonLeapYear(){
		DateBean bean=new DateBean("01 01 1981","31 12 1981");
		DateBusiness businessObj=new DateBusiness();
		
		int result = businessObj.calculateDays(bean);
		assertEquals(365,result);
	}
	
	@Test
	public void calculateDaysLeapYear(){
		DateBean bean=new DateBean("01 01 1900","31 12 1900");
		DateBusiness businessObj=new DateBusiness();
		
		int result = businessObj.calculateDays(bean);
		assertEquals(366,result);
	}
	
}
