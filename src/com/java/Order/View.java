package com.java.Order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DTO.Order;
import com.java.DTO.OrderList;
import com.java.DTO.User;
import com.java.DTO.UserList;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String SessionID = session.getId();
		ArrayList<Order> list = getList(SessionID);
		ArrayList<String> dates = getDates(list);
		ArrayList<String> names = getNames(list);
		ArrayList<String> ids = getID(list);
		request.setAttribute("names", names);
		request.setAttribute("dates", dates);
		request.setAttribute("ids", ids);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ViewOrders.jsp");
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private ArrayList<Order> getList(String SessionID){
		ArrayList<Order> tList = OrderList.getList();
		ArrayList<Order> result = new ArrayList<Order>();
		User user = UserList.getBySessionID(SessionID);
		String ID = user.getID();
		for(Order o : tList) {
			if(o.getUserID().equals(ID)) {
				result.add(o);
			}
		}
		return result;
	}
	private ArrayList<String> getDates(ArrayList<Order> list){
		ArrayList<String> dates = new ArrayList<String>();
		for(Order o : list) {
			dates.add(o.getDateTime());
		}
		return dates;
	}
	
	private ArrayList<String> getNames(ArrayList<Order> list){
		ArrayList<String> names = new ArrayList<String>();
		for(Order o : list) {
			names.add(o.getCoName());
		}
		return names;
	}
	
	private ArrayList<String> getID(ArrayList<Order> list){
		ArrayList<String> ids = new ArrayList<String>();
		for(Order o : list) {
			ids.add(o.getOrderID());
		}
		return ids;
	}
}
