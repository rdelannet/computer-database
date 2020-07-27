package com.excilys.formation.pagination;

import org.springframework.stereotype.Component;

@Component
public class Page {
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	private  int nbPages = 10;
	private Long maxElem;
	private int offset;
	private Integer itemsBypage;
	private Integer currentPage;
	
	public Page() {
		
	}
	
	public int getNbPages() {
		return  this.nbPages;
	}
	public void setNbPages(int nbPage) {
		this.nbPages = nbPage;
	}
	
	public Long getMaxElem() {
		return this.maxElem;
	}
	public void setMaxElem(Long result) {
		this.maxElem = result;
	}
	
	public int getItemsByPage() {
		return this.itemsBypage;
	}
	public void setItemsByPage(int nb) {
		this.itemsBypage = nb;
	}
	public int getCurrentPage() {
		return this.currentPage; 
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
}
