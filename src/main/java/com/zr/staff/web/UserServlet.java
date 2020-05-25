package com.zr.staff.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.staff.pojo.User;
import com.zr.staff.service.IUserService;
import com.zr.staff.service.impl.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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

		// 接收请求参数
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		
		
		if ("login".equals(cmd)) {
			login(request, response);
		} 
		else if("userList".equals(cmd)) {
			userList(request, response);
		}
		else if("deleteUsers".equals(cmd)) {
			String ids=request.getParameter("ids");
			System.out.println("要删除的编号是："+ids);
			String[] split=ids.split(",");
			System.out.println(split.length);
			for(int i=0;i<split.length;i++) {
				deleteUser(request,response,split[i]);
			}
			// 转发到user列表页面
			request.getRequestDispatcher("/UserServlet?cmd=userList").forward(request, response);
		}
		else if("searchUser".equals(cmd)) {
			String username=request.getParameter("username");
			String status1=request.getParameter("status");
			int status=Integer.parseInt(status1);
			searchUser(request,response,status,username);
		}
		else if("editUser".equals(cmd)) {
			String username=request.getParameter("username");
			String loginname=request.getParameter("loginname");
			String status1=request.getParameter("status");
			String id1=request.getParameter("id");
			int status=Integer.parseInt(status1);
			int id=Integer.parseInt(id1);
			System.out.println(username);
			System.out.println(loginname);
			System.out.println(status);
			System.out.println(id);
			editUser(request,response,username,loginname,status,id);
			userList(request,response);
		}
		else if("addUser".equals(cmd)) {
			String username=request.getParameter("username");
			String loginname=request.getParameter("loginname");
			String password=request.getParameter("password");
			String status1=request.getParameter("status");
			int status=Integer.parseInt(status1);
			System.out.println(username);
			System.out.println(loginname);
			System.out.println(password);
			System.out.println(status);
			addUser(username,loginname,password,status);
			userList(request,response);
		}
		else if("updatePassword".equals(cmd)) {
			String newPassword=request.getParameter("newPassword");
			String oldPassword=request.getParameter("oldPassword");
			HttpSession session=request.getSession();
			String loginname=(String) session.getAttribute("loginname");
			System.out.println("登录名："+loginname);
			System.out.println("新密码："+newPassword);
			System.out.println("旧密码："+oldPassword);
			updatePassword(request,response,loginname,newPassword,oldPassword);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void updatePassword(HttpServletRequest request, HttpServletResponse response, String loginname, String newPassword, String oldPassword) {
		// TODO Auto-generated method stub
		IUserService service=new UserService();
		String flag=service.updatePassword(loginname,newPassword,oldPassword);
		System.out.println(flag);
//		if("false".equals(flag)) {
//		//String msg="当前密码输入错误，请重新输入！";
//			//System.out.println(msg);
//			//response.getWriter().write("<script language=javascript>alert('"+msg+"');window.location='/staff/loginForm.jsp'</script>");
//		}
//		else {
//			try {
//				PrintWriter out = response.getWriter();
//				out.println(
//						"<script type='text/javascripte'>window.location.replace(\"../loginForm.jsp\");</script>");
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
	}

	private void addUser(String username, String loginname, String password, int status) {
		// TODO Auto-generated method stub
		IUserService service=new UserService();
		service.addUser(username,loginname,password,status);
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response, String username, String loginname,
			int status, int id) {
		// TODO Auto-generated method stub
		IUserService service=new UserService();
		service.editUser(username,loginname,status,id);
	}

	private void searchUser(HttpServletRequest request, HttpServletResponse response, int status,String username) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(status);
		System.out.println(username);
		IUserService service = new UserService();
		
		//User user1=service.searchUser(status, username);

		List<User> user = service.searchUser(status, username);

		request.setAttribute("userList", user);

		request.getRequestDispatcher("/user/user.jsp").forward(request, response);

		return;
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response, String id1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 调用service中的方法删除user
		IUserService service = new UserService();

		int id=Integer.parseInt(id1);
		service.deleteById(id);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

	/**
	 * 这是专门用于处理登录功能的方法
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		/**
		 * 思路: 1, 获取到用户输入的昵称和密码<br>
		 * 2, 然后去数据库中查找是否正好有对应昵称和密码的用户存在 3, 如果存在, 则允许登录 4, 如果不存在, 则告诉用户[账号或密码有误,
		 * 请经检查后登录, 或者是点击[注册]]
		 */

		// 1, 获取到用户输入的昵称和密码<br>
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");

		// 2, 然后去数据库中查找是否正好有对应昵称和密码的用户存在
		IUserService service = new UserService();

		User user = service.findUserByLoginnameAndPassword(loginname, password);

		if (user != null) {

			// 登录成功, 就把登录的用户存储到session中
			req.getSession().setAttribute("loginname", loginname);
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("user", user);

//			resp.getWriter().write("登录成功, 1s后回到主页");
//
//			resp.setHeader("refresh", "1;url=/staff/main.jsp");
			
			req.getRequestDispatcher("main.jsp").forward(req, resp);

			return;
		} else {
			//resp.getWriter().write("账号或密码有误, 请检查后登录");
//			resp.getWriter().write("账号或密码有误, 请检查后登录, 3s后返回登录页");
//
//			resp.setHeader("refresh", "3;url=/staff/loginForm.jsp");
			String msg="账号或密码错误, 请检查后登录！";
			resp.getWriter().write("<script language=javascript>alert('"+msg+"');window.location='/staff/loginForm.jsp'</script>");
		}

	}
	
	private void userList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		IUserService service = new UserService();

		List<User> userList = service.findAll();

		req.setAttribute("userList", userList);

		req.getRequestDispatcher("/user/user.jsp").forward(req, resp);

		return;
	}

}
