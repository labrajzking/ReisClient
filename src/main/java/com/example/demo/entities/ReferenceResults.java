package com.example.demo.entities;

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
private List<CriminalReference> matched_criminals;
public String getClient_code() {
	return client_code;
}
public void setClient_code(String client_code) {
	this.client_code = client_code;
}


}
