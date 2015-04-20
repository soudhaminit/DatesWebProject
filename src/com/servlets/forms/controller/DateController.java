package com.servlets.forms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.servlets.forms.beans.DateBean;
import com.servlets.forms.business.DateBusiness;

/**
 * Servlet implementation class DatesController
 */
@WebServlet("/DateController")
public class DateController extends HttpServlet {
	static Logger log = Logger.getLogger(DateController.class.getName());
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startDate=request.getParameter("startdate");
		String endDate=request.getParameter("enddate");
		
		request.setAttribute("startdate", startDate);
		request.setAttribute("enddate", endDate);
		DateBean bean=new DateBean(startDate,endDate);
		
		DateBusiness busObj=new DateBusiness();
		
		if(!busObj.validate(bean)){
			log.info("In Controller message : "+bean.getMessage());
			request.setAttribute("message", bean.getMessage());
			request.setAttribute("days","");
		}else{
			int days=busObj.calculateDays(bean);
			log.info("calculatedays : "+days);
			request.setAttribute("message", "");
			request.setAttribute("days",String.valueOf(days));
		}
		
		request.getRequestDispatcher("/datesCalculation.jsp").forward(request, response);
	}

}
