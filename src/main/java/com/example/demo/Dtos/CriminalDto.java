package com.example.demo.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Client;

public class CriminalDto {
	private Integer person_id;
	private Double score;
	private List<Client> clients=new ArrayList<Client>();
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
