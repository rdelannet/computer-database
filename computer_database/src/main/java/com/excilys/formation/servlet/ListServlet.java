package com.excilys.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
/**
 * Servlet implementation class ListServlet
 */
@WebServlet(urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int page;
	public int taillePage;
	public int maxPage;
	private ComputerDTO computer = new ComputerDTO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("computers", computer.getComputerMaxElement());
		request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}