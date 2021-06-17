package com.java.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.DTO.Company;
import com.java.DTO.CompanyList;
import com.java.DTO.DayStockInfo;
import com.java.DTO.DayStockInfoJSP;
import com.java.DTO.DayStockInfoList;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Insert() {
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
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");

		if (flag.equals("invest")) {
			session = request.getSession();
			String coName = request.getParameter("CoName");
			System.out.println(coName);
			Invest invest = new Invest(coName, session);
			request.setAttribute("invest", invest);
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/SellNBuy.jsp");
			dispatcher.forward(request, response);
		} 
		else if (flag.equals("register")) {
			session = request.getSession();
			String userID = request.getParameter("userID");
			String type = request.getParameter("opt");
			String coName = request.getParameter("coName");
			int val = Integer.parseInt(request.getParameter("val"));
			int price = Integer.parseInt(request.getParameter("price"));

			RegisterOrder register = new RegisterOrder(session, userID, type, val, coName, price);
			register.register();
		}
		else if(flag.equals("goToSearch")) {
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Search.jsp");
			dispatcher.forward(request, response);
		}

		doGet(request, response);
	}
}

class getLiveInfo {
	private DayStockInfoJSP info = null;
	private ArrayList<DayStockInfo> list = new ArrayList<DayStockInfo>();

	public DayStockInfoJSP getInfo() {
		return info;
	}

	public ArrayList<DayStockInfo> getList() {
		return list;
	}

	public void makelist(String Name) {
		String Code = getCode(Name);
		ArrayList<DayStockInfo> temp = DayStockInfoList.getList();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getCode().equals(Code)) {
				list.add(temp.get(i));
			}
		}
	}

	public void crawl(String Name) {
		Calendar cal = Calendar.getInstance();
		int h = cal.get(Calendar.HOUR);
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		int ms = cal.get(Calendar.MILLISECOND);
		System.out.println(h + " : " + m + " : " + s + " : " + ms);
		String Code = getCode(Name);
		System.out.println("123123" + Code + "123123" + Name);
		String url = "https://finance.naver.com/item/main.nhn?code=" + Code;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements element = doc.select("p.no_today");
		System.out.println("============================================================");
		// Iterator을 사용하여 하나씩 값 가져오기
		String LivePrice = element.select("span").get(0).text();
		System.out.println(LivePrice); // 실시간 주가
		System.out.println("============================================================");
		element = doc.select("p.no_exday");
		Iterator<Element> ei = element.select("p.no_exday > em.no_up > span").iterator();
		if (ei.hasNext() == false) {
			ei = element.select("p.no_exday > em.no_down > span").iterator();
			if (ei.hasNext() == false) {
				ei = element.select("p.no_exday > em.X > span").iterator();
			}
		}
		String UnD = null;
		String amount = null;
		String percent = null;
		boolean isNumBefore = false;
		boolean isAmount = true;
		while (ei.hasNext()) {
			String character = ei.next().text();
			character = character.replace(",", "");
			if (character.equals("상승")) {
				UnD = "▲";
				continue;
			}
			if (character.equals("하락")) {
				UnD = "▲";
				continue;
			}
			if (character.equals("보합")) {
				UnD = "-";
				amount = "0";
				percent = "0.00%";
				break;
			}
			if ((isNum(character)) && (isNumBefore == false) && (isAmount == true)) {
				isAmount = false;
				isNumBefore = true;
				amount = character;
				continue;
			}
			if ((character.equals("+")) || (character.equals("-"))) {
				isNumBefore = false;
				percent = character + " ";
			}
			if ((isNum(character)) && (isNumBefore == false) && (isAmount == false)) {
				isAmount = true;
				isNumBefore = true;
				percent += character + "%";
				continue;
			}
		}
		element = doc.select("table.no_info>tbody>tr>td>em>span");
		ei = element.iterator();
		ArrayList<String> temp = new ArrayList<String>();
		while (ei.hasNext()) {
			String character = ei.next().text();
			if (character.length() > 1) {
				temp.add(character);
				for (int j = 0; j < character.length(); j++) {
					ei.next();
				}
				if (temp.size() == 6) {
					String low = "";
					while (true) {
						character = ei.next().text();
						if (character.length() == 1) {
							low += character;
						} else {
							temp.add(low);
							temp.add(character);
							ei.next();
							break;
						}
					}
				}
			}
		}
		System.out.println(UnD + "\t" + amount + "\t" + percent);
		for (String e : temp) {
			System.out.println(e);
		}

		this.info = new DayStockInfoJSP(Name, Code, UnD, LivePrice, amount, percent, temp.get(0), temp.get(1),
				temp.get(2), temp.get(3), temp.get(4), temp.get(5), temp.get(6), temp.get(7));
		cal = Calendar.getInstance();
		h = cal.get(Calendar.HOUR);
		m = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		ms = cal.get(Calendar.MILLISECOND);
		System.out.println(h + " : " + m + " : " + s + " : " + ms);
	}

	private boolean isNum(String num) {
		try {
			// System.out.println(num);
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private String getCode(String Name) {
		ArrayList<Company> list = CompanyList.getList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(Name)) {
				return list.get(i).getCode();
			}
		}
		return null;
	}
}