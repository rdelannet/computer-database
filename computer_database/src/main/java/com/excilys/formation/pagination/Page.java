package com.excilys.formation.pagination;

public class Page {
	
	private  int nbPages = 10;
	private int maxElem;
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
	
	public int getMaxElem() {
		return this.maxElem;
	}
	public void setMaxElem(int result) {
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
