package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;
import com.excilys.formation.services.ComputerService;
import com.excilys.formation.services.ComputerServiceImp;
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
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private ComputerServiceImp computerService;
	@Autowired
	private Page pages;
	private String order = "computer.name";
	//private ConnectDB conn;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet()  {
        //this.conn = new ConnectDB();
       
        //this.computerService = new ComputerServiceImp();
        //this.pages = new Page();
    }
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDto = new ArrayList<ComputerDTO>();
		List<Computer> computers = new ArrayList<Computer>();
		String search = "";
		Integer nb = 10;
		
		String ascending = "ASC";
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
		if(request.getParameter("search") != null && !request.getParameter("search").equals("") ) {
			search = request.getParameter("search");
			computers = computerService.getComputersSearchByPage(pages,search);
			for(Computer computer : computers) {
				try {
					computersDto.add(ComputerDTOMapper.computerToDTO(computer));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		if(request.getParameter("order") != null && !request.getParameter("order").equals("") && request.getParameter("ascending") != null && !request.getParameter("ascending").equals("") ){
			order =request.getParameter("order");
			ascending = request.getParameter("ascending");
			request.setAttribute("ascending",ascending);
			request.setAttribute("order",order);
			computers = computerService.getComputersOrderByPage(pages,order,ascending);
			
			for (Computer computer : computers) {
				try {
					computersDto.add(ComputerDTOMapper.computerToDTO(computer));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		else {
			computers = computerService.getComputersByPage(pages);
			for (Computer computer : computers) {
				System.out.println(computer);
				try {
					computersDto.add(ComputerDTOMapper.computerToDTO(computer));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
		System.out.println(pages.getItemsByPage());
		System.out.println(order);
		request.setAttribute("page", pages);
		
		
		request.setAttribute("search", search);
		request.setAttribute("nbByPage", nb);
		
		request.setAttribute("nbPagesMax", computerService.getComputersNbPages(pages));
		request.setAttribute("nbComputers", computerService.getNbComputers());
		request.setAttribute("computers", computersDto);
		request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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