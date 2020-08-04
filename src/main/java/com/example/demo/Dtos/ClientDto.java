package com.example.demo.Dtos;
import java.util.ArrayList;

import com.example.demo.entities.Criminal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
public class ClientDto {
	@JsonInclude(Include.ALWAYS)
	private String client_code="";
	@JsonInclude(Include.ALWAYS)
	private String first_name="";
	@JsonInclude(Include.ALWAYS)
	private String last_name="";
	@JsonInclude(Include.ALWAYS)
	private String whole_name="";
	@JsonInclude(Include.ALWAYS)
	private ArrayList<Criminal> matched_criminals=new ArrayList<Criminal>();
	public ArrayList<Criminal> getMatched_criminals() {
		return matched_criminals;
	}
	public void setMatched_criminals(ArrayList<Criminal> matched_criminals) {
		this.matched_criminals = matched_criminals;
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
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

}
