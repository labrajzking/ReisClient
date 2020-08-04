package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class CriminalReference {
@Id
private Integer person_id;
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name="referencelist")
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
