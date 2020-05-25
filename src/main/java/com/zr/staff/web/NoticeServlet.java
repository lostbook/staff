package com.zr.staff.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.staff.pojo.Notice;
import com.zr.staff.service.INoticeService;
import com.zr.staff.service.impl.NoticeService;

/**
 * Servlet implementation class NoticeServlet
 */
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cmd=request.getParameter("cmd");
		System.out.println(cmd);
		if("noticeList".equals(cmd)) {
			noticeList(request,response);
		}
		else if("addNotice".equals(cmd)) {
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			addNotice(title,content);
			noticeList(request,response);
		}
		else if("deleteNotice".equals(cmd)) {
			String ids=request.getParameter("ids");
			String[] split=ids.split(",");
			for(int i=0;i<split.length;i++) {
				deleteNotice(split[i]);
			}
			noticeList(request,response);
		}
		else if("updateNotice".equals(cmd)) {
			String id1=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int id=Integer.parseInt(id1);
			updateNotice(id,title,content);
			noticeList(request,response);
		}
		else if("searchNotice".equals(cmd)) {
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			searchNotice(request,response,title,content);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void searchNotice(HttpServletRequest request, HttpServletResponse response, String title, String content) {
		// TODO Auto-generated method stub
		INoticeService service=new NoticeService();
		List<Notice> noticeList=service.findNotice(title,content);
		try {
			request.setAttribute("noticeList", noticeList);
			request.getRequestDispatcher("/notice/notice.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void updateNotice(int id, String title, String content) {
		// TODO Auto-generated method stub
		INoticeService service=new NoticeService();
		service.updateNotice(id,title,content);
	}

	private void deleteNotice(String id1) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		INoticeService service=new NoticeService();
		service.deleteNotice(id);
	}

	private void addNotice(String title, String content) {
		// TODO Auto-generated method stub
		INoticeService service=new NoticeService();
		service.addNotice(title,content);
	}

	private void noticeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		INoticeService service=new NoticeService();
		List<Notice> noticeList=service.findAll();
		request.setAttribute("noticeList", noticeList);
		try {
			request.getRequestDispatcher("/notice/notice.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
