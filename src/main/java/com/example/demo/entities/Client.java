package com.example.demo.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="Clients")
public class Client {
@Id
private String client_code;
private String first_name;
private String last_name;
private String whole_name;
@ManyToMany(mappedBy="clients",cascade = CascadeType.PERSIST)
private List<Criminal> matched_criminals=new ArrayList<Criminal>();
public String getFirst_name() {
	return first_name;
}
public List<Criminal> getMatched_criminals() {
	return matched_criminals;
}
public void setMatched_criminals(List<Criminal> matched_criminals) {
	this.matched_criminals = matched_criminals;
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
