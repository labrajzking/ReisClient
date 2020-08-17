package com.example.demo.POJOS;

import java.util.ArrayList;
import java.util.List;

public class Outcome {
private List<Results> resultsList1=new ArrayList<Results>();
private List<Results> resultsList2=new ArrayList<Results>();

public List<Results> getResultsList1() {
	return resultsList1;
}
public void setResultsList1(List<Results> resultsList1) {
	this.resultsList1 = resultsList1;
}
public List<Results> getResultsList2() {
	return resultsList2;
}
public void setResultsList2(List<Results> resultsList2) {
	this.resultsList2 = resultsList2;
}
}
