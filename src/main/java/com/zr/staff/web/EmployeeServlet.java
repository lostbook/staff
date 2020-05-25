package com.zr.staff.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.staff.pojo.Department;
import com.zr.staff.pojo.Employee;
import com.zr.staff.pojo.Job;
import com.zr.staff.service.IEmployeeService;
import com.zr.staff.service.impl.EmployeeService;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
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
		if("employeeList".equals(cmd)) {
			employeeList(request,response);
		}
		else if("addEmployee".equals(cmd)) {
			String name=request.getParameter("name");
			String card_id=request.getParameter("cardId");
			String sex1=request.getParameter("sex");
			String job_id1=request.getParameter("job_id");
			String education=request.getParameter("education");
			String email=request.getParameter("email");
			String tel=request.getParameter("tel");
			String phone=request.getParameter("phone");
			String party=request.getParameter("party");
			String qq_num=request.getParameter("qq_num");
			String address=request.getParameter("address");
			String post_code=request.getParameter("post_code");
			String birthday1=request.getParameter("birthday");
			String race=request.getParameter("race");
			String speciality=request.getParameter("speciality");
			String hobby=request.getParameter("hobby");
			String remark=request.getParameter("remark");
			String dept_id1=request.getParameter("dept_id");
			int sex=Integer.parseInt(sex1);
			int job_id=Integer.parseInt(job_id1);
			Date birthday = null;
			if(birthday1=="") {
				birthday1="1900-01-01";
			}
			birthday=Date.valueOf(birthday1);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
//			Date birthday = null;
//			try {
//				birthday = simpleDateFormat.parse(birthday1);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			int dept_id=Integer.parseInt(dept_id1);
			addEmployee(name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id);
			employeeList(request,response);
		}
		else if("deleteEmployee".equals(cmd)) {
			String ids=request.getParameter("ids");
			String[] split=ids.split(",");
			for(int i=0;i<split.length;i++) {
				deleteEmployee(split[i]);
			}
			employeeList(request,response);
		}
		else if("searchEmployee".equals(cmd)) {
			String job_id1=request.getParameter("job_id");
			String name=request.getParameter("name");
			String card_id=request.getParameter("card_id");
			String sex1=request.getParameter("sex");
			String phone=request.getParameter("phone");
			String dept_id1=request.getParameter("dept_id");
			int job_id=Integer.parseInt(job_id1);
			int sex=Integer.parseInt(sex1);
			int dept_id=Integer.parseInt(dept_id1);
			System.out.println(job_id);
			System.out.println(sex);
			System.out.println(dept_id);
			System.out.println(card_id);
			searchEmployee(request,response,job_id,name,card_id,sex,phone,dept_id);
		}
		else if("updateEmployee".equals(cmd)) {
			String id1=request.getParameter("id");
			int id=Integer.parseInt(id1);
			String name=request.getParameter("name");
			String card_id=request.getParameter("card_id");
			String sex1=request.getParameter("sex");
			String job_id1=request.getParameter("job_id");
			String education=request.getParameter("education");
			String email=request.getParameter("email");
			String tel=request.getParameter("tel");
			String phone=request.getParameter("phone");
			String party=request.getParameter("party");
			String qq_num=request.getParameter("qq_num");
			String address=request.getParameter("address");
			String post_code=request.getParameter("post_code");
			String birthday1=request.getParameter("birthday");
			String race=request.getParameter("race");
			String speciality=request.getParameter("speciality");
			String hobby=request.getParameter("hobby");
			String remark=request.getParameter("remark");
			String dept_id1=request.getParameter("dept_id");
			int sex=Integer.parseInt(sex1);
			int job_id=Integer.parseInt(job_id1);
			Date birthday = null;
			if(birthday1=="") {
				birthday1="1900-01-01";
			}
			birthday=Date.valueOf(birthday1);
			int dept_id=Integer.parseInt(dept_id1);
			updateEmployee(id,name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id);
			employeeList(request,response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void updateEmployee(int id, String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		IEmployeeService service=new EmployeeService();
		service.updateEmployee(id,name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id);
	}

	private void searchEmployee(HttpServletRequest request, HttpServletResponse response, int job_id, String name,
			String card_id, int sex, String phone, int dept_id) {
		// TODO Auto-generated method stub
		IEmployeeService service=new EmployeeService();
		List<Employee> employeeList=employeeList=service.searchEmployee(job_id,name,card_id,sex,phone,dept_id);
		request.setAttribute("employeeList", employeeList);
		try {
			request.getRequestDispatcher("/employee/employee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteEmployee(String id1) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(id1);
		IEmployeeService service=new EmployeeService();
		service.deleteEmployee(id);
	}

	private void addEmployee(String name, String card_id, int sex, int job_id, String education, String email,
			String tel, String phone, String party, String qq_num, String address, String post_code, Date birthday,
			String race, String speciality, String hobby, String remark, int dept_id) {
		// TODO Auto-generated method stub
		IEmployeeService service=new EmployeeService();
		service.addEmployee(name,card_id,sex,job_id,education,email,tel,phone,party,qq_num,address,post_code,birthday,race,speciality,hobby,remark,dept_id);
	}

	private void employeeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IEmployeeService service=new EmployeeService();
		List<Employee> employeeList=service.findAll();
		List<Job> jobList=service.findJob();
		List<Department> departmentList=service.findDepartment();
		request.setAttribute("employeeList", employeeList);
		try {
			HttpSession session=request.getSession();
			session.setAttribute("jobList", jobList);
			session.setAttribute("departmentList", departmentList);
			session.setAttribute("employeeList", employeeList);
			request.getRequestDispatcher("employee/employee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
