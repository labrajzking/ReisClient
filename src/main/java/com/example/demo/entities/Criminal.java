package com.example.demo.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="Persons")
public class Criminal {
@Id
private Integer person_id;
private Double score;
private String person_whole_name;
@ManyToMany(mappedBy="matched_criminals",fetch = FetchType.EAGER)
private List<Client> clients=new ArrayList <Client>();
@ManyToMany(mappedBy="matched_reference_criminals",fetch = FetchType.EAGER)
private List<Client> clientsreference =new ArrayList<Client>();
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
	this.clients= clients;
}
public List<Client> getClientsreference() {
	return clientsreference;
}
public void setClientsreference(List<Client> clientsreference) {
	this.clientsreference = clientsreference;
}
public String getPerson_whole_name() {
	return person_whole_name;
}
public void setPerson_whole_name(String person_whole_name) {
	this.person_whole_name = person_whole_name;
}

}
