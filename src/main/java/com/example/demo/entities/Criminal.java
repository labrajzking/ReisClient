package com.example.demo.entities;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Criminal {
@Id
private Integer person_id;
private Double score;
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name="finallist")
private List<Client> clients;
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


}
