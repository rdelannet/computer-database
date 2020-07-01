package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
import com.excilys.formation.mappers.CompanyDTOMapper;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet(urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyDAO companyDao;
	private ComputerDAO computerDao;
	private ConnectDB conn;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() throws SQLException {
        super();
        this.conn = new ConnectDB();
        this.companyDao= new CompanyDAO(conn.getConnection());
        this.computerDao= new ComputerDAO(conn.getConnection());
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanyDTO> companiesDto = new ArrayList<CompanyDTO>();
		List<Company> companies = new ArrayList<Company>();
		
		companies = companyDao.findAll();
		for(Company company : companies) {
			companiesDto.add(CompanyDTOMapper.companytoCompanyDto(company));
			
		}
		request.setAttribute("company", companiesDto);
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(request,response);
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis la");
		ComputerDTO computer = new ComputerDTO();
		Computer comp = new Computer();
		ComputerDAO c = computerDao;
		
		
		computer.setCompanyDTO(new CompanyDTO(Integer.parseInt(request.getParameter("companyId"))));
		
		computer.setName(request.getParameter("computerName"));
		
		if(request.getParameter("introduced") != "") {
			computer.setIntroduced(request.getParameter("introduced"));
			
		}
		if(request.getParameter("discontinued") != "") {
			computer.setDiscontinued(request.getParameter("discontinued"));
		}
		
		
		
		
		System.out.println(request.getParameter("name"));
		comp = ComputerDTOMapper.dtoToComputer(computer);
		System.out.println(comp);
		c.create(comp);
		doGet(request, response);
		
		
		
		
		
	}

}
