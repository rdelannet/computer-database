package com.excilys.formation.pagination;

public class Page {
	
	private  int nbPages = 0;
	private int maxElem;
	
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
	
}
