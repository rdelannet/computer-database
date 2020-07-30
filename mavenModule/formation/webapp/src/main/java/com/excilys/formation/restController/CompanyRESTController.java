package com.excilys.formation.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mappers.CompanyMapper;
import com.excilys.formation.mappers.ComputerMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.services.CompanyService;
import com.excilys.formation.services.ComputerService;

@RestController
@RequestMapping("company")
public class CompanyRESTController {
	
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = {"", "/"})
    public List<CompanyDTO> getListCompanies() {
        List<Company> allCompanies = companyService.getAllCompany();
        return allCompanies.stream().map(c -> CompanyMapper.toCompanyDTO(c)).collect(Collectors.toList());
    }
    
    @GetMapping(value = {"/{id}"})
    public CompanyDTO getCompany(@PathVariable int id) {
    	return CompanyMapper.toCompanyDTO(companyService.getCompany(id));
    }
    
    @DeleteMapping(value = {"/{id}"})
    public void delete(@PathVariable int id) {
    	companyService.deleteCompany(id);
    }
    
	
}
