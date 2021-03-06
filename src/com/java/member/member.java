package com.java.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DTO.User;

/**
 * Servlet implementation class member
 */
@WebServlet("/member")
public class member extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public member() {
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
		HttpSession session = null;
		String flag = (String) request.getParameter("flag");
		if (flag.equals("login")) {
			String Id = (String) request.getParameter("ID");
			String Pw = (String) request.getParameter("PW");
			session = request.getSession();
			System.out.println(session);
			Login login = new Login(Id, Pw, session);
			login.IsValid();
			login.login();
			boolean tFlag = login.getresult();
			System.out.println(tFlag);
			if (tFlag == true) {
				response.sendRedirect("MainPage.jsp");
			} else {
				String content = "false";
				request.setAttribute("isLogin", content);
				request.setAttribute("Description", login.getdescription());
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}
		} 
		else if (flag.equals("logout")) {
			session = request.getSession();
			System.out.println(session);
			Logout logout = new Logout();
			logout.logout(session);

			String content = "false";
			request.setAttribute("isLogin", content);
			request.setAttribute("Description", "???????? ??????????.");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("register")) {//?????????? ????????
			String content = "true";
			request.setAttribute("isRegistered", content);
			request.setAttribute("Description", "");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Register.jsp");//???????? ?????? ??????
			dispatcher.forward(request, response);
		}
		else if (flag.equals("registerOK")) {//???????????? ???? ???? ???? ?????? ??????
			String id = (String) request.getParameter("id");
			String pw = (String) request.getParameter("pw");
			String nickName = (String) request.getParameter("nickName");
			String birthDay = (String) request.getParameter("birthDay");
			String email = (String) request.getParameter("email");
			Register register = new Register(id, pw, nickName, birthDay, email);
			
			register.register();
			
			boolean isComplete = register.getresult();
			
			if(isComplete) {//???????? ?????? ?????? ???? ???? ?????? ????, ???????? ???? ?????? ?????? ?? ?? ??????. 
				String content = "false";
				request.setAttribute("isLogin", content);
				request.setAttribute("Description", "?????????? ??????????????.");
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");//?????? ?????? ??????
				dispatcher.forward(request, response);
			}
			else {
				String content = "false";
				request.setAttribute("isRegistered", content);
				request.setAttribute("Description", register.getdescription());
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Register.jsp");//???????? ?????? ??????
				dispatcher.forward(request, response);
			}
		}
		else if (flag.equals("update")) {// ???????? ???????? ???????????? ???? ?? ???????? ???? ?????? ??????
			session = request.getSession();
			Update update = new Update(session);
			User user = update.findUser(session);//???????? ??????
			
			if(user == null) {//?????? ?? ?????????? ???? ?????? ??????
				response.sendRedirect("Error.html");
				return;
			}
			
			String content = "true";
			request.setAttribute("isUpdated", content);
			request.setAttribute("Description", update.getdescription());
			request.setAttribute("id", user.getID());
			request.setAttribute("pw", user.getPW());
			request.setAttribute("nickName", user.getNickName());
			request.setAttribute("birth", user.getBirthday());
			request.setAttribute("email", user.getEmail());
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Update.jsp");//???????????? ?????? ??????
			dispatcher.forward(request, response);
		}  
		else if (flag.equals("updateOk")) {//???????????? ?????? ???????? ?????? ????????
			session = request.getSession();
			String check = (String) request.getParameter("check");//?????? pw
			String id = (String) request.getParameter("id");//???? ID
			String pw = (String) request.getParameter("pw");//???? PW
			String nickName = (String) request.getParameter("nickname");//???? ??????
			String birth = (String) request.getParameter("birth");//???? ????????
			String email = (String) request.getParameter("email");//???? ??????
			
			Update update = new Update(session);
			update.update(check, id, pw, nickName, birth, email);
			User user = update.findUser(session);//???????? ??????
			
			if(user == null) {//?????? ?? ?????????? ???? ?????? ??????
				response.sendRedirect("Error.html");
				return;
			}
			
			if(!update.getresult()) {//???? ???? ???? ???? ???? ?? ?????????? ????
				String content = "false";
				request.setAttribute("isUpdated", content);
				request.setAttribute("Description", update.getdescription());
				request.setAttribute("id", user.getID());
				request.setAttribute("pw", user.getPW());
				request.setAttribute("nickName", user.getNickName());
				request.setAttribute("birth", user.getBirthday());
				request.setAttribute("email", user.getEmail());
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Update.jsp");//???????????? ?????? ??????
				dispatcher.forward(request, response);
			}
			else {//???? ?? ???????????? ??????
				request.setCharacterEncoding("utf-8");
				request.setAttribute("user", user);
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/MemberInfo.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if (flag.equals("deleteYes")) {// ???? ?????????? ???? ??
			session = request.getSession();

			Delete del = new Delete();
			del.delete(session);// ???????? ???? ????
			
			String content = "false";
			request.setAttribute("isLogin", content);
			request.setAttribute("Description", del.getdescription());
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");// ???????? ?? ?????? ?????? ????
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("viewinfo") || flag.equals("deleteNo")) {// ???????? ???? ?? ???? ?????? ????.(???????????????? ???? ????.)
			session = request.getSession();
			ViewInfo info = new ViewInfo();
			User user = info.getUser(session);//???? id?? ?????? ??????, UserList?? refresh ???? ?? ???? id?? ?????????? ???? ???????? ???????? ????
			if (user == null) {
				response.sendRedirect("Error.html");
				return;
			}
			request.setCharacterEncoding("utf-8");
			request.setAttribute("user", user);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/MemberInfo.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("findIDPW")) {// ?????? ???????? id,pw???? ???? ??
			String content = "false";
			request.setAttribute("isFind", content);
			request.setAttribute("Description", "");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/FindIDPW.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("findID")) {// id?????? ????????
			FindIDPW finder = new FindIDPW();
			String email = (String) request.getParameter("email");
			String msg = finder.findID(email);

			String content = "true";
			request.setAttribute("isFind", content);
			request.setAttribute("Description", msg);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/FindIDPW.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("findPW")) {// pw?????? ????????
			FindIDPW finder = new FindIDPW();
			String birthDay = (String) request.getParameter("birthDay");
			String email = (String) request.getParameter("email");
			String msg = finder.findPW(birthDay, email);
			System.out.println(birthDay);
			String content = "true";
			request.setAttribute("isFind", content);
			request.setAttribute("Description", msg);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/FindIDPW.jsp");
			dispatcher.forward(request, response);
		}
		doGet(request, response);
	}
}

class Edit {
	// DB???? ???????? ???????? ???????? ????????.
}