package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.Guestbookvo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게스트북 올리고
		GuestbookDao guestbookDao = new GuestbookDao();
		//리스트에 담고
		List<Guestbookvo> guestbookList = guestbookDao.getList();
		//액션 해서 받아올거고
		String act = request.getParameter("action");
		
		//다오의 겟 리스트를 GBList에 넣을테니 여기서 꺼내 쓰거라
		request.setAttribute("GBList", guestbookList);
		
		
		//액션이 add, addList, delete, deleteForm일떄로 나눌거고 
		if("add".equals(act)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
			//파라미터값 가져오기
			String name = request.getParameter("uname");
			String password = request.getParameter("upswd");
			String text = request.getParameter("textarea");
			//즈장	
			Guestbookvo guestbookvo = new Guestbookvo(name, password, text);
			
			guestbookDao.Insert(guestbookvo);
			
			response.sendRedirect("./addList.jsp");
			
		}else if("addList".equals(act)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(act)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
			request.setCharacterEncoding("UTF-8");

			String pswd = request.getParameter("password");
			int i = Integer.parseInt(request.getParameter("id"));


			Guestbookvo guestbookvo = new Guestbookvo();

			guestbookvo.setNo(i);
			guestbookvo.setPassword(pswd);

			
			response.sendRedirect("./addList.jsp");
			
			
		}else if("deleteForm".equals(act)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
			
			String str = request.getParameter("id");
			int i = Integer.parseInt(str);
			
			
			response.sendRedirect("./delete.jsp");
			
		}else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
