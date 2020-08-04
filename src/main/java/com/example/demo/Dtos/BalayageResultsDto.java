package com.example.demo.Dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class BalayageResultsDto {
@JsonProperty
private Long size;
@JsonProperty
private List<ItemDto> items;
public Long getSize() {
	return size;
}
public void setSize(Long size) {
	this.size = size;
}
public List<ItemDto> getItems() {
	return items;
}
public void setItems(List<ItemDto> items) {
	this.items = items;
	
}
}
