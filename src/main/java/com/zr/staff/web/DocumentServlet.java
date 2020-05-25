package com.zr.staff.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.staff.pojo.Document;
import com.zr.staff.service.IDocumentService;
import com.zr.staff.service.impl.DocumentService;

/**
 * Servlet implementation class DocumentServlet
 */
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentServlet() {
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
		if("documentList".equals(cmd)) {
			documentList(request,response);
		}
		else if("searchDocument".equals(cmd)) {
			String title=request.getParameter("title");
			searchDocument(request,response,title);
		}
		else if("addDocument".equals(cmd)) {
			String title=request.getParameter("title");
			String remark=request.getParameter("remark");
			String filename=request.getParameter("file");
			System.out.println(title);
			System.out.println(remark);
			System.out.println(filename);
			addDocument(title,remark,filename);
			documentList(request,response);
		}
		else if("deleteDocument".equals(cmd)) {
			String ids=request.getParameter("ids");
			String[] split=ids.split(",");
			for(int i=0;i<split.length;i++) {
				deleteDocument(split[i]);
			}
			documentList(request,response);
		}
		else if("updateDocument".equals(cmd)) {
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String remark=request.getParameter("remark");
			String filename=request.getParameter("file");
			updateDocument(id,title,remark,filename);
			documentList(request,response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void updateDocument(String id1, String title, String remark, String filename) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		IDocumentService service=new DocumentService();
		service.updateDocument(id,title,remark,filename);
	}

	private void deleteDocument(String id1) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		IDocumentService service=new DocumentService();
		service.deleteDocument(id);
	}

	private void addDocument(String title, String remark, String filename) {
		// TODO Auto-generated method stub
		IDocumentService service=new DocumentService();
		service.addDocument(title,remark,filename);
	}

	private void searchDocument(HttpServletRequest request, HttpServletResponse response, String title) {
		// TODO Auto-generated method stub
		IDocumentService service=new DocumentService();
		List<Document> documentList=service.searchDocument(title);
		request.setAttribute("documentList", documentList);
		try {
			request.getRequestDispatcher("/document/document.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void documentList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IDocumentService service=new DocumentService();
		List<Document> documentList=service.findAll();
		request.setAttribute("documentList", documentList);
		try {
			request.getRequestDispatcher("/document/document.jsp").forward(request, response);
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
