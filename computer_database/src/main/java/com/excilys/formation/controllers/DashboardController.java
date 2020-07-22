package com.excilys.formation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mappers.ComputerDTOMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;
import com.excilys.formation.services.ComputerService;

@Controller
@RequestMapping( { "/","/ListServlet"})
public class DashboardController {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private Page pages;
	
	@GetMapping
	public String ListComputer(@RequestParam(required=false, name="nbByPage") String nbByPage,
			@RequestParam(required=false, name="page") String pageWish,
			@RequestParam(required=false, name="order") String order,
			@RequestParam(required=false, name="ascending") String ascending,
			@RequestParam(required=false, name="search") String search,
			Model model) {
		
		List<ComputerDTO> computersDto = new ArrayList<ComputerDTO>();
		List<Computer> computers = new ArrayList<Computer>();
		String searchP = "";
		Integer nb = 10;
		String orderP = "computer.name";
		String ascendingP = "ASC";
		if(nbByPage != null) {
			String nbByPageP = nbByPage;
			pages.setItemsByPage(Integer.parseInt(nbByPageP));
			nb = pages.getItemsByPage();
		}
		else {
			pages.setItemsByPage(nb);
		}
		if(pageWish != null) {
			pages.setCurrentPage(Integer.parseInt(pageWish));
		}
		else {
			pages.setCurrentPage(1);
		}
		if(search != null && !("".equals(search))) {
			searchP = search;
			computers = computerService.getComputersSearchByPage(pages,searchP);
			computersDto = computers.stream().map(computer->ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
			System.out.println(computersDto);
		}
		else {
			computers = computerService.getComputersByPage(pages);
			computersDto = computers.stream().map(computer->ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
		}
		if(order != null && !("".equals(order)) && ascending != null && !("".equals(ascending))) {
			orderP = order;
			ascendingP = ascending;
			model.addAttribute("ascending", ascendingP);
			model.addAttribute("order", orderP);
			computers = computerService.getComputersOrderByPage(pages,orderP,ascendingP);
			computersDto = computers.stream().map(computer->ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
			
		}
		
		System.out.println("coucou je suis dans le dash");
		model.addAttribute("page",pages);		
		model.addAttribute("search",searchP);
		model.addAttribute("nbByPage", nb);
		model.addAttribute("nbPagesMax", computerService.getComputersNbPages(pages));
		model.addAttribute("nbComputers", computerService.getNbComputers());
		model.addAttribute("computers", computersDto);
		return "dashboard";
	}
	
	@PostMapping
	public String deleteComputer(@RequestParam(required=false, name="selection") String selection) {
		if(selection != null && !("".equals("selection"))) {
			String listIds = selection;
			List<Integer> ids = Stream.of(listIds.split(","))
					.map(Integer::parseInt)
					.collect(Collectors.toList());
			ids.parallelStream().forEach(id->computerService.deleteComputer(id));
			
		}
		return "redirect:/ListServlet";
	}

}
