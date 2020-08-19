package com.example.demo.Dtos;
import java.util.List;
import com.example.demo.POJOS.PersonsName;
public class CriminalDto {
	private Integer person_id;
	private Double score;
	private List<PersonsName> name;
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
	public List<PersonsName> getName() {
		return name;
	}
	public void setName(List<PersonsName> name) {
		this.name = name;
	}

}
