package com.example.demo.POJOS;

import java.util.ArrayList;
import java.util.List;

public class Outcome {
private List<Results> resultsList=new ArrayList<Results>();
private String outcome;
public List<Results> getResultsList() {
	return resultsList;
}
public void setResultsList(List<Results> resultsList) {
	this.resultsList = resultsList;
}
public String getOutcome() {
	return outcome;
}
public void setOutcome(String outcome) {
	this.outcome = outcome;
}

}
