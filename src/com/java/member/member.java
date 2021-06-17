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
			request.setAttribute("Description", "로그아웃 되었습니다.");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("register")) {//회원가입을 선택하면
			String content = "true";
			request.setAttribute("isRegistered", content);
			request.setAttribute("Description", "");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Register.jsp");//회원가입 창으로 넘어감
			dispatcher.forward(request, response);
		}
		else if (flag.equals("registerOK")) {//회원가입에서 정보 기입 완료 버튼을 누르면
			String id = (String) request.getParameter("id");
			String pw = (String) request.getParameter("pw");
			String nickName = (String) request.getParameter("nickName");
			String birthDay = (String) request.getParameter("birthDay");
			String email = (String) request.getParameter("email");
			Register register = new Register(id, pw, nickName, birthDay, email);
			
			register.register();
			
			boolean isComplete = register.getresult();
			
			if(isComplete) {//성공하면 로그인 창으로 가서 성공 메시지 띄움, 실패하면 실패 메시지 띄우고 이 창 그대로. 
				String content = "false";
				request.setAttribute("isLogin", content);
				request.setAttribute("Description", "회원가입이 완료되었습니다.");
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");//로그인 창으로 넘어감
				dispatcher.forward(request, response);
			}
			else {
				String content = "false";
				request.setAttribute("isRegistered", content);
				request.setAttribute("Description", register.getdescription());
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Register.jsp");//회원가입 창으로 돌아감
				dispatcher.forward(request, response);
			}
		}
		else if (flag.equals("update")) {// 회원정보 조회에서 회원정보수정 선택 시 회원정보 수정 창으로 넘어감
			session = request.getSession();
			Update update = new Update(session);
			User user = update.findUser(session);//회원정보 가져옴
			
			if(user == null) {//정보를 못 받아왔으면 에러 창으로 넘어감
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
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Update.jsp");//회원정보수정 창으로 넘어감
			dispatcher.forward(request, response);
		}  
		else if (flag.equals("updateOk")) {//회원정보수정 창에서 회원정보 수정을 완료하면
			session = request.getSession();
			String check = (String) request.getParameter("check");//확인용 pw
			String id = (String) request.getParameter("id");//바꿀 ID
			String pw = (String) request.getParameter("pw");//바꿀 PW
			String nickName = (String) request.getParameter("nickname");//바꿀 닉네임
			String birth = (String) request.getParameter("birth");//바꿀 생년월일
			String email = (String) request.getParameter("email");//바꿀 이메일
			
			Update update = new Update(session);
			update.update(check, id, pw, nickName, birth, email);
			User user = update.findUser(session);//회원정보 가져옴
			
			if(user == null) {//정보를 못 받아왔으면 에러 창으로 넘어감
				response.sendRedirect("Error.html");
				return;
			}
			
			if(!update.getresult()) {//중복 감지 또는 인증 실패 시 경고메시지 출력
				String content = "false";
				request.setAttribute("isUpdated", content);
				request.setAttribute("Description", update.getdescription());
				request.setAttribute("id", user.getID());
				request.setAttribute("pw", user.getPW());
				request.setAttribute("nickName", user.getNickName());
				request.setAttribute("birth", user.getBirthday());
				request.setAttribute("email", user.getEmail());
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Update.jsp");//회원정보수정 창으로 넘어감
				dispatcher.forward(request, response);
			}
			else {//성공 시 회원정보조회 창으로
				request.setCharacterEncoding("utf-8");
				request.setAttribute("user", user);
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/MemberInfo.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if (flag.equals("deleteYes")) {// 진짜 탈퇴한다고 선택 시
			session = request.getSession();

			Delete del = new Delete();
			del.delete(session);// 회원정보 삭제 실행
			
			String content = "false";
			request.setAttribute("isLogin", content);
			request.setAttribute("Description", del.getdescription());
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Login.jsp");// 회원탈퇴 후 로그인 창으로 복귀
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("viewinfo") || flag.equals("deleteNo")) {// 회원탈퇴 취소 시 조회 창으로 복귀.(회원정보조회에서 탈퇴 가능.)
			session = request.getSession();
			ViewInfo info = new ViewInfo();
			User user = info.getUser(session);//세션 id로 찾아야 하는데, UserList의 refresh 호출 시 세션 id가 초기화되어 에러 페이지로 넘어가게 된다
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
		else if (flag.equals("findIDPW")) {// 로그인 화면에서 id,pw찾기 선택 시
			String content = "false";
			request.setAttribute("isFind", content);
			request.setAttribute("Description", "");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/FindIDPW.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("findID")) {// id찾기를 선택하면
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
		else if (flag.equals("findPW")) {// pw찾기를 선택하면
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
	// DB에서 회원정보 수정하고 리스트는 리프레쉬.
}