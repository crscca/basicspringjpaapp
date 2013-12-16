package com.samples.beans;

import java.util.ArrayList;
import java.util.List;

public class PageReturnData<E> {
	
	private long noOfTotalRows;
	private long noOfPages;
	private int currentPage;
	private List<E> data;
	private String first;
	private String last;
	private String prefixUrl;
	private List<String> previous= new ArrayList<String>();
	private List<String> next= new ArrayList<String>();
	
	public long getNoOfTotalRows() {
		return noOfTotalRows;
	}
	public void setNoOfTotalRows(long noOfTotalRows) {
		this.noOfTotalRows = noOfTotalRows;
	}
	public long getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(long noOfPages) {
		this.noOfPages = noOfPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	public List<String> getPrevious() {
		return previous;
	}
	public void setPrevious(List<String> previous) {
		this.previous = previous;
	}
	public List<String> getNext() {
		return next;
	}
	public void setNext(List<String> next) {
		this.next = next;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
		
		setFirst(prefixUrl+1);
		setLast(prefixUrl+getNoOfPages());
		previous.clear();
		
		for (int i = currentPage-1, built=0; i > 0 && built< 5; i--, built++) 
		{
			previous.add(prefixUrl+i);
		}
		
		next.clear();
		
		for (int i = currentPage+1, built=0; i < noOfPages  && built< 5; i++, built++) 
		{
			next.add(prefixUrl+i);
		}
		
		
	}

}
