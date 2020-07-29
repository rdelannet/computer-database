package com.excilys.formation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.CompanyDTOMapper;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.services.CompanyService;
import com.excilys.formation.services.ComputerService;

@Controller
@RequestMapping("/AddServlet")
public class AddController {
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public String ShowAdd(
			Model model) {
		List<CompanyDTO> companiesDto = new ArrayList<CompanyDTO>();
		List<Company> companies = new ArrayList<Company>();
		companies = companyService.getAllCompany();
		for(Company company : companies) {
			companiesDto.add(CompanyDTOMapper.companytoCompanyDto(company));
			
		}
		
		model.addAttribute("company", companiesDto);
		
		
		return "addComputer";
	}
	@PostMapping
	public String AddComputer(@RequestParam(name="computerName") String computerName,
			@RequestParam(required=false, name="introduced") String introduced,
			@RequestParam(required=false, name="discontinued") String discontinued,
			@RequestParam(required=false, name="companyId") String companyId,
			Model model) {
		ComputerDTO computer = new ComputerDTO();
		Computer comp = new Computer();
		computer.setCompanyDTO(new CompanyDTO(Integer.parseInt(companyId)));
		computer.setName(computerName);
		if(!("".equals(introduced))) {
			computer.setIntroduced(introduced);
		}
		if(!("".equals(discontinued))) {
			computer.setDiscontinued(discontinued);
		}
		comp = ComputerDTOMapper.dtoToComputerC(computer);
		computerService.createComputer(comp);
		
		return "addComputer";
	}
}
