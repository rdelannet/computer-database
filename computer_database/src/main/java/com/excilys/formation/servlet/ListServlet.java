package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;
/**
 * Servlet implementation class ListServlet
 */
@WebServlet(urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int page;
	public int taillePage;
	public int maxPage;
	//private ComputerDTOMapper computerM ;
	private ComputerDTO computerD ;
	private ComputerDAO computerDao;
	private CompanyDAO companyDao;
	private Page pages;
	private ConnectDB conn;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() throws SQLException {
        super();
        this.conn = new ConnectDB();
        this.computerDao = new ComputerDAO(conn.getConnection());
        this.companyDao= new CompanyDAO(conn.getConnection());
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDto = new ArrayList<ComputerDTO>();
		List<Computer> computers = new ArrayList<Computer>();
		if(request.getParameter("page") != null) {
			String page = request.getParameter("page");
			pages.setNbPages(Integer.parseInt(page));
		}
		
		computers = computerDao.findAll();
		for (Computer computer : computers) {
			computersDto.add(ComputerDTOMapper.computerToDTO(computer));
		}
		
		request.setAttribute("nbComputers", computerDao.findMaxElement());
		request.setAttribute("computers", computersDto);
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