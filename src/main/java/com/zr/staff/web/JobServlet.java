package com.zr.staff.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.staff.pojo.Job;
import com.zr.staff.service.IJobService;
import com.zr.staff.service.impl.JobService;

/**
 * Servlet implementation class JobServlet
 */
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");
		String cmd=request.getParameter("cmd");
		System.out.println(cmd);
		if("jobList".equals(cmd)) {
			jobList(request,response);
		}
		else if("addJob".equals(cmd)) {
			String name=request.getParameter("name");
			String remark=request.getParameter("remark");
			addJob(name,remark);
			jobList(request,response);
		}
		else if("updateJob".equals(cmd)) {
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String remark=request.getParameter("remark");
			updateJob(id,name,remark);
			jobList(request,response);
		}
		else if("deleteJob".equals(cmd)) {
			String ids=request.getParameter("ids");
			String[] split=ids.split(",");
			for(int i=0;i<split.length;i++) {
				deleteJob(split[i]);
			}
			jobList(request,response);
		}
		else if("searchJob".equals(cmd)) {
			String name=request.getParameter("name");
			searchJob(request,response,name);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void searchJob(HttpServletRequest request, HttpServletResponse response, String name) {
		// TODO Auto-generated method stub
		IJobService service=new JobService();
		List<Job> jobList=service.searchJob(name);
		request.setAttribute("jobList", jobList);
		try {
			request.getRequestDispatcher("/job/job.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteJob(String id) {
		// TODO Auto-generated method stub
		IJobService service=new JobService();
		service.deleteJob(id);
	}

	private void updateJob(String id, String name, String remark) {
		// TODO Auto-generated method stub
		IJobService service=new JobService();
		service.updateJob(id,name,remark);
	}

	private void addJob(String name, String remark) {
		// TODO Auto-generated method stub
		IJobService service=new JobService();
		service.addJob(name,remark);
	}

	private void jobList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IJobService service=new JobService();
		List<Job> jobList=service.findAll();
		request.setAttribute("jobList", jobList);
		
		try {
			request.getRequestDispatcher("/job/job.jsp").forward(request, response);
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
