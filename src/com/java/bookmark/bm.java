package com.java.bookmark;

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

import com.java.DTO.Bookmark;
import com.java.DTO.BookmarkList;
import com.java.DTO.User;
import com.java.DTO.UserList;
import com.java.Order.Conclude;

/**
 * Servlet implementation class member
 */
@WebServlet("/bm")
public class bm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bm() {
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
		HttpSession session = null;
		String flag = (String) request.getParameter("flag");
		String Code;
		 if (flag.equals("inquireBookmark")) {
			session = request.getSession();
			inquireBookmark inquiry = new inquireBookmark();
			ArrayList<String> bookmark = inquiry.getBookmark(session);
			request.setCharacterEncoding("utf-8");
			request.setAttribute("bookmark", bookmark);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/inquireBookmark.jsp");
			dispatcher.forward(request, response);
		} else if(flag.equals("insertBookmark")) {
			session = request.getSession();
			Code = (String) request.getParameter("Code");
			BookmarkList.insertBookmark(Code, session);
		} else if(flag.equals("deleteBookmark")) {
			session = request.getSession();
			Code = (String) request.getParameter("Code");
			BookmarkList.deleteBookmark(Code, session);
		}
		doGet(request, response);
	}
}

class inquireBookmark {
	public ArrayList<String> getBookmark(HttpSession session) {
		Conclude c = new Conclude();
		ArrayList<String> list = new ArrayList<String>();
		if (!list.isEmpty()) {
			list.clear();
		}
		ArrayList<Bookmark> lTemp = com.java.DTO.BookmarkList.getList();
		ArrayList<User> temp = com.java.DTO.UserList.getList();
		String CoName;
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getSessionID().equals(session.getId())) {
				for (int j = 0; j < lTemp.size(); j++) {
					if (temp.get(i).getID().equals(lTemp.get(j).getUserID())) {
						CoName = lTemp.get(j).getCoName();
						list.add(CoName);
					}
				}
				lTemp.clear();
				return list;
			}
		}
		return null;
	}
}