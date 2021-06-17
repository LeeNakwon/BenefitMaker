package com.java.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.DTO.Order;
import com.java.DTO.OrderList;

/**
 * Servlet implementation class ViewDetail
 */
@WebServlet("/ViewDetail")
public class ViewDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String OrderID = (String) request.getParameter("OrderID");
		ArrayList<Order> list = OrderList.getList();
		Order result = getOrder(list, OrderID);
		request.setAttribute("result", result);
		boolean isDeletable = isDeletable(result);
		request.setAttribute("isDeletable", isDeletable);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Detail.jsp");
		dispatcher.forward(request, response);
	}

	private Order getOrder(ArrayList<Order> list, String OrderId) {
		Order order = null;
		for (Order o : list) {
			if (o.getOrderID().equals(OrderId)) {
				return o;
			}
		}
		return order;
	}
	
	private boolean isDeletable(Order order) {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int h = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
//		if((h > 6) && (min > 00)) {
//			return false;
//		}
		String date = Integer.toString(y) + "-" + Integer.toString(m) + "-" + Integer.toString(d);
		int conclude = order.getIsConcluded();
		if(conclude == 1) {
			return false;
		}
		if(order.getDateTime().startsWith(date)) {
			return true;
		}
		return false;
	}
}
