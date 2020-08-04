package com.example.demo.Dtos;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.ReferenceResults;
public class CriminalReferenceDto {
	private Integer person_id ;
	private List<ReferenceResults> clients=new ArrayList<ReferenceResults>();
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public List<ReferenceResults> getClients() {
		return clients;
	}
	public void setClients(List<ReferenceResults> clients) {
		this.clients = clients;
	}
	
}
