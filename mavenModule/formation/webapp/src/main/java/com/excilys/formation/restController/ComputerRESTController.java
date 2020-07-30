package com.excilys.formation.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.mappers.ComputerMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;
import com.excilys.formation.services.ComputerService;

@RestController
@RequestMapping("computer")
public class ComputerRESTController {
	
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<ComputerDTO> getListComputer(){
		List<Computer> allComputers = computerService.getAllComputer();
		return allComputers.stream().map(c-> ComputerMapper.toComputerDTO(c)).collect(Collectors.toList());
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ComputerDTO getComputers(@PathVariable int id) {
		return ComputerMapper.toComputerDTO(computerService.getComputer(id));
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void create(ComputerDTO computer) {
		computerService.createComputer(ComputerDTOMapper.dtoToComputer(computer));
	}
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void update(ComputerDTO computer) {
		computerService.updateComputer(ComputerDTOMapper.dtoToComputer(computer));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(ComputerDTO computer) {
		computerService.deleteComputer(Integer.valueOf(computer.getId()));
		return ResponseEntity.ok("Ok");
	}
	
	@RequestMapping(value = "/search/{search}", method = RequestMethod.GET,consumes = "application/json")
	public List<Computer> searchComputer(@PathVariable(required = false) String search, @RequestBody Page page){
		return computerService.getComputersSearchByPage(page, search);
		
	}
	@RequestMapping(value = "/order/{orderBy}", method = RequestMethod.GET)
	public List<Computer> OrderComputer(@RequestBody Page page,@PathVariable String order,@RequestBody  String ascending){
		return computerService.getComputersOrderByPage(page, order, ascending);
		
	}
	
	
		
	

}
