package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        this.pages = new Page();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDto = new ArrayList<ComputerDTO>();
		List<Computer> computers = new ArrayList<Computer>();
		String search = "";
		Integer nb = 10;
		if(request.getParameter("nbByPage") != null) {
			String nbByPage = request.getParameter("nbByPage");
			pages.setItemsByPage(Integer.parseInt(nbByPage));
			nb = pages.getItemsByPage();
		}
		
		else {
			pages.setItemsByPage(10);
		}
		if(request.getParameter("page") != null) {
			String pageWish = request.getParameter("page");
			pages.setCurrentPage(Integer.parseInt(pageWish));
		}
		else {
			pages.setCurrentPage(1);
		}
		if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {
			search = request.getParameter("search");
			computers = computerDao.getComputersSearchByPage(pages,search);
			for(Computer computer : computers) {
				try {
					computersDto.add(ComputerDTOMapper.computerToDTO(computer));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		else {
			computers = computerDao.getComputersByPage(pages);
			for (Computer computer : computers) {
				try {
					computersDto.add(ComputerDTOMapper.computerToDTO(computer));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
		//System.out.println(pages.getItemsByPage());
		request.setAttribute("page", pages);
		request.setAttribute("search", search);
		request.setAttribute("nbByPage", nb);
		request.setAttribute("nbPagesMax", computerDao.getComputersNbPages(pages));
		request.setAttribute("nbComputers", computerDao.findMaxElement());
		request.setAttribute("computers", computersDto);
		request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDtoP = new ArrayList<ComputerDTO>();
		List<Computer> computers = new ArrayList<Computer>();
		if(request.getParameter("selection") != null && !request.getParameter("selection").equals("")) {
			String listIds = request.getParameter("selection");
			System.out.println(listIds);
			List<Integer> ids = Stream.of(listIds.split(","))
					.map(Integer::parseInt)
					.collect(Collectors.toList());
			for(Integer id: ids) {
				computerDao.delete(computerDao.find(id));
			}
		}
		
		doGet(request, response);
	}
}