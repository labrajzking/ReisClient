package com.example.demo.Dtos;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.entities.CriminalReference;

public class ReferenceResultsDto {
	private String client_code;
	private List<CriminalReference> matched_criminals=new ArrayList<CriminalReference>();
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	public List<CriminalReference> getMatched_criminals() {
		return matched_criminals;
	}
	public void setMatched_criminals(List<CriminalReference> matched_criminals) {
		this.matched_criminals = matched_criminals;
	}
	
}
