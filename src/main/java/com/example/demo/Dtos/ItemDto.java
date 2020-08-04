package com.example.demo.Dtos;

import java.util.List;

public class ItemDto {
	private String client_code;
	private String first_name;
	private String last_name;
	private String whole_name;
	private List<CriminalDto> SearchItems;
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getWhole_name() {
		return whole_name;
	}
	public void setWhole_name(String whole_name) {
		this.whole_name = whole_name;
	}
	public List<CriminalDto> getSearchItems() {
		return SearchItems;
	}
	public void setSearchItems(List<CriminalDto> items) {
		this.SearchItems = items;
	}
	
}
