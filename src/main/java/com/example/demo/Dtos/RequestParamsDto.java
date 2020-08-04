package com.example.demo.Dtos;

import org.json.JSONObject;

//@JsonIgnoreProperties(value = {"filterModel"})
public class RequestParamsDto {
private	Integer endRow ;
private	Integer startRow;
private String [] sortModel=new String [] {};
private JSONObject filterModel=new JSONObject ();
private String [] search_sources=new String[]{"BATCH"};
public Integer getEndRow() {
	return endRow;
}
public void setEndRow(Integer endRow) {
	this.endRow = endRow;
}
public Integer getStartRow() {
	return startRow;
}
public void setStartRow(Integer startRow) {
	this.startRow = startRow;
}
public String[] getSortModel() {
	return sortModel;
}
public void setSortModel(String[] sortModel) {
	this.sortModel = sortModel;
}
public Object getFilterModel() {
	return filterModel;
}
public void setFilterModel(JSONObject filterModel) {
	this.filterModel = filterModel;
}
public String[] getSearch_sources() {
	return search_sources;
}
public void setSearch_sources(String[] search_sources) {
	this.search_sources = search_sources;
}

}
