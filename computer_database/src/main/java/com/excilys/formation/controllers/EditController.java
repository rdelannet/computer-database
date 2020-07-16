package com.excilys.formation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.CompanyDTOMapper;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@Controller
@RequestMapping("/EditServlet")
public class EditController {
	
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ComputerDAO computerDao;
	
	@GetMapping
	public String showEditComp(@RequestParam(required=false,name="id") String id, Model model) {
		List<CompanyDTO> companiesDto = new ArrayList<CompanyDTO>();
		List<Company> companies = new ArrayList<Company>();
		companies = companyDao.findAll();
		companiesDto = companies.stream().map(company->CompanyDTOMapper.companytoCompanyDto(company)).collect(Collectors.toList());
		model.addAttribute("company", companiesDto);
		Integer idP = Integer.parseInt(id);
		Computer computer = computerDao.find(idP);
		ComputerDTO computerDto;
		
		computerDto = ComputerDTOMapper.computerToDTO(computer);
		model.addAttribute("computer", computerDto);
		
		return "editComputer";
	
	}
	@PostMapping
	public String editComputer(@RequestParam(name="computerName") String computerName,
			@RequestParam(required=false, name="introduced") String introduced,
			@RequestParam(required=false, name="discontinued") String discontinued,
			@RequestParam(required=false, name="companyId") String companyId,
			@RequestParam(name="id") String id,
			Model model) {
		ComputerDTO computer = new ComputerDTO();
		Computer comp = new Computer();
		computer.setId(id);
		computer.setName(computerName);
		try {
			computer.setCompanyDTO(new CompanyDTO(Integer.parseInt(companyId)));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		if(!("".equals(introduced))) {
			computer.setIntroduced(introduced);
		}
		if(!("".equals(discontinued))) {
			computer.setDiscontinued(discontinued);
		}
		comp = ComputerDTOMapper.dtoToComputer(computer);
		computerDao.update(comp);
		return "editComputer";
		
	}

}
