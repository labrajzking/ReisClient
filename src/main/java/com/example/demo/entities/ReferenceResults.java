package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ReferenceResults {
@Id
private String client_code;
@ManyToMany(mappedBy="clients",cascade = CascadeType.PERSIST)
private List<CriminalReference> matched_criminals=new ArrayList <CriminalReference>();
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
