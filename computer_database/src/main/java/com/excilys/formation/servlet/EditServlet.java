package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.CompanyDTOMapper;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@WebServlet(urlPatterns = "/k")
public class EditServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ComputerDAO computerDao;
	
	
	public EditServlet() throws SQLException {
        
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
		List<CompanyDTO> companiesDto = new ArrayList<CompanyDTO>();
		List<Company> companies = new ArrayList<Company>();
		
		companies = companyDao.findAll();
		for(Company company : companies) {
			companiesDto.add(CompanyDTOMapper.companytoCompanyDto(company));
			
		}
		request.setAttribute("company", companiesDto);
		System.out.println(request.getParameter("id"));
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println(request.getParameter("id"));
		Computer computer = computerDao.find(id);
		ComputerDTO computerDto;
		
			computerDto = ComputerDTOMapper.computerToDTO(computer);
			request.setAttribute("computer", computerDto);
			request.getRequestDispatcher("WEB-INF/views/editComputer.jsp").forward(request,response);
		

		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ComputerDTO computer = new ComputerDTO();
		Computer comp = new Computer();
		
		computer.setId(request.getParameter("id"));
		computer.setName(request.getParameter("computerName"));
		try {
			computer.setCompanyDTO(new CompanyDTO(Integer.parseInt(request.getParameter("companyId"))));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		if(!("".equals(request.getParameter("introduced")))) {
			computer.setIntroduced(request.getParameter("introduced"));
		}
		if(!("".equals(request.getParameter("discontinued")))) {
			computer.setDiscontinued(request.getParameter("discontinued"));
		}
		
		
		
		
		comp = ComputerDTOMapper.dtoToComputer(computer);
		
		computerDao.update(comp);
		doGet(request, response);
	}

}
