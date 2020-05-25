package com.zr.staff.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.staff.pojo.Department;
import com.zr.staff.service.IDepartmentService;
import com.zr.staff.service.impl.DepartmentService;

/**
 * Servlet implementation class DepartmentServlet
 */
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
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

		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		
		if("departmentList".equals(cmd)) {
			departmentList(request,response);
		}
		else if("addDepartment".equals(cmd)) {
			String name=request.getParameter("name");
			String remark=request.getParameter("remark");
			addDepartment(name,remark);
			departmentList(request,response);
		}
		else if("updateDepartment".equals(cmd)) {
			String id1=request.getParameter("id");
			String name=request.getParameter("name");
			String remark=request.getParameter("remark");
			int id=Integer.parseInt(id1);
			updateDepartment(id,name,remark);
			departmentList(request,response);
		}
		else if("searchDepartment".equals(cmd)) {
			String name=request.getParameter("name");
			searchDepartment(request,response,name);
		}
		else if("deleteDepartment".equals(cmd)) {
			String ids=request.getParameter("ids");
			String[] split=ids.split(",");
			for(int i=0;i<split.length;i++) {
				deleteDepartment(request,response,split[i]);
			}
			request.getRequestDispatcher("/DepartmentServlet?cmd=departmentList").forward(request, response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response, String id) {
		// TODO Auto-generated method stub
		IDepartmentService service=new DepartmentService();
		service.deleteDepartment(id);
	}

	private void searchDepartment(HttpServletRequest request, HttpServletResponse response, String name) {
		// TODO Auto-generated method stub
		IDepartmentService service=new DepartmentService();
		List<Department> departmentList=service.searchDepartment(name);
		request.setAttribute("departmentList", departmentList);
		try {
			request.getRequestDispatcher("/dept/dept.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateDepartment(int id, String name, String remark) {
		// TODO Auto-generated method stub
		IDepartmentService service=new DepartmentService();
		service.updateDepartment(id,name,remark);
	}

	private void addDepartment(String name, String remark) {
		// TODO Auto-generated method stub
		IDepartmentService service=new DepartmentService();
		service.addDepartment(name,remark);
	}

	private void departmentList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IDepartmentService service = new DepartmentService();
		List<Department> departmentList=service.findAll();
		request.setAttribute("departmentList", departmentList);
		
		try {
			request.getRequestDispatcher("/dept/dept.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
