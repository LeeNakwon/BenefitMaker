package com.java.DisplayCompanyInfo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class DisplayCompanyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCompanyInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
        String flag = request.getParameter("flag");
        String code = request.getParameter("code");
        
   	 	//WebCrawler
		String URL = "https://finance.naver.com/item/main.nhn?code=" + code; 
		Document doc = Jsoup.connect(URL).get(); 
		Elements elements;
		if(flag.equals("DisplayCompanyInfo1")) {
			String coInfo[] = new String[9];
			
			elements = doc.select("div.rate_info");
			String high = elements.select("em.no_cha").get(0).text();
			high = high.substring(high.length()/2);
			String low = elements.select("em.no_cha").get(1).text();
			
			elements = doc.select("div.tab_con1");
			String high52 = elements.select("table.rwidth").select("em").get(2).text();
			String low52 = elements.select("table.rwidth").select("em").get(3).text();
			String foreigner = elements.select("tr.strong").select("em").get(1).text();
			String total = elements.select("#_market_sum").text();
			String eps = elements.select("table.per_table").select("em#_per").text() + "배 | ";
			eps += elements.select("table.per_table").select("em#_eps").text() + "원";
			String bps = elements.select("table.per_table").select("em#_pbr").text() + "배 | ";
			bps += elements.select("table.per_table").select("em").get(7).text() + "원";
			
			coInfo[0] = high;
			coInfo[1] = low;
			coInfo[2] = high52;
			coInfo[3] = low52;
			coInfo[4] = foreigner;
			coInfo[5] = total;
			coInfo[6] = eps;
			coInfo[7] = bps;
			coInfo[8] = code;
			
			request.setAttribute("coInfo", coInfo);
	        
	        ServletContext context = getServletContext();
	        RequestDispatcher dispatcher = context.getRequestDispatcher("/DisplayCompanyInfo1.jsp");
	        dispatcher.forward(request, response);
			doGet(request, response);
		}
		else if(flag.equals("DisplayCompanyInfo2") || flag.equals("DisplayCompanyInfo3")) {
			String coInfo[][] = new String[11][4];
			String isCode[] = new String[4];
			isCode[0] = code;
			
			elements = doc.select("table.tb_type1.tb_num.tb_type1_ifrs");
			
			String quarter[] = new String[4];
			quarter[0] = elements.select("th").get(9).text();
			quarter[1] = elements.select("th").get(10).text();
			quarter[2] = elements.select("th").get(11).text();
			quarter[3] = elements.select("th").get(12).text();
			
			String sales[] = new String[4];
			sales[0] = elements.select("tbody").select("td").get(6).text();
			sales[1] = elements.select("tbody").select("td").get(7).text();
			sales[2] = elements.select("tbody").select("td").get(8).text();
			sales[3] = elements.select("tbody").select("td").get(9).text();
			
			String bp[] = new String[4];
			bp[0] = elements.select("tbody").select("td").get(16).text();
			bp[1] = elements.select("tbody").select("td").get(17).text();
			bp[2] = elements.select("tbody").select("td").get(18).text();
			bp[3] = elements.select("tbody").select("td").get(19).text();
			
			String npdt[] = new String[4];
			npdt[0] = elements.select("tbody").select("td").get(26).text();
			npdt[1] = elements.select("tbody").select("td").get(27).text();
			npdt[2] = elements.select("tbody").select("td").get(28).text();
			npdt[3] = elements.select("tbody").select("td").get(29).text();
			
			String bpRatio[] = new String[4];
			bpRatio[0] = elements.select("tbody").select("td").get(36).text();
			bpRatio[1] = elements.select("tbody").select("td").get(37).text();
			bpRatio[2] = elements.select("tbody").select("td").get(38).text();
			bpRatio[3] = elements.select("tbody").select("td").get(39).text();
			
			String niRatio[] = new String[4];
			niRatio[0] = elements.select("tbody").select("td").get(46).text();
			niRatio[1] = elements.select("tbody").select("td").get(47).text();
			niRatio[2] = elements.select("tbody").select("td").get(48).text();
			niRatio[3] = elements.select("tbody").select("td").get(49).text();
			
			String roe[] = new String[4];
			roe[0] = elements.select("tbody").select("td").get(56).text();
			roe[1] = elements.select("tbody").select("td").get(57).text();
			roe[2] = elements.select("tbody").select("td").get(58).text();
			roe[3] = elements.select("tbody").select("td").get(59).text();
			
			String debtRatio[] = new String[4];
			debtRatio[0] = elements.select("tbody").select("td").get(66).text();
			debtRatio[1] = elements.select("tbody").select("td").get(67).text();
			debtRatio[2] = elements.select("tbody").select("td").get(68).text();
			debtRatio[3] = elements.select("tbody").select("td").get(69).text();
			
			String quickRatio[] = new String[4];
			quickRatio[0] = elements.select("tbody").select("td").get(76).text();
			quickRatio[1] = elements.select("tbody").select("td").get(77).text();
			quickRatio[2] = elements.select("tbody").select("td").get(78).text();
			quickRatio[3] = elements.select("tbody").select("td").get(79).text();
			
			String reserveRatio[] = new String[4];
			reserveRatio[0] = elements.select("tbody").select("td").get(86).text();
			reserveRatio[1] = elements.select("tbody").select("td").get(87).text();
			reserveRatio[2] = elements.select("tbody").select("td").get(88).text();
			reserveRatio[3] = elements.select("tbody").select("td").get(89).text();
			
			coInfo[0] = quarter;
			coInfo[1] = sales;
			coInfo[2] = bp;
			coInfo[3] = npdt;
			coInfo[4] = bpRatio;
			coInfo[5] = niRatio;
			coInfo[6] = roe;
			coInfo[7] = debtRatio;
			coInfo[8] = quickRatio;
			coInfo[9] = reserveRatio;
			coInfo[10] = isCode;
			
			for(int i = 0;i<10;i++){
				if(coInfo[i][3].length() < 1){
					coInfo[i][3] = "-";
				}
			}
			
			request.setAttribute("coInfo", coInfo);
	        
	        ServletContext context = getServletContext();
	        
	        
	        if(flag.equals("DisplayCompanyInfo2")) {
	        	RequestDispatcher dispatcher= context.getRequestDispatcher("/DisplayCompanyInfo2.jsp");
	        	dispatcher.forward(request, response);
				doGet(request, response);
	        }
	        else if(flag.equals("DisplayCompanyInfo3")){
	        	RequestDispatcher dispatcher= context.getRequestDispatcher("/DisplayCompanyInfo3.jsp");
	        	dispatcher.forward(request, response);
				doGet(request, response);
	        }
		}	
	}
}
