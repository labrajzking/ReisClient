package com.example.demo.services;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.POJOS.Outcome;
import com.example.demo.POJOS.Results;
import com.example.demo.repositories.CriminalRepository;
import com.opencsv.CSVWriter;
@Service
public class FinalResultsService implements FinalResultsServiceI{
@Autowired 
CriminalRepository crimrep;
private String client_code;
private Integer person_id;
@Override
	public List<Results> getFinalResults1() {
		List<List<Object>> results=crimrep.FinalResults1();
		JSONArray json =new JSONArray (results);
		System.out.println(json);
		HashMap<Integer,Results> resultsHash=new HashMap<Integer,Results>();
		for (int i=0;i<results.size();i++)
		{
			Results res=new Results();
			res.setClient_code((String) results.get(i).get(0));
			res.setPerson_id((Integer)results.get(i).get(1));
			res.setScore((Double)results.get(i).get(3));
			res.setWhole_name((String) results.get(i).get(2));
			res.setPerson_whole_name((String)results.get(i).get(4));
			this.client_code=(String) results.get(i).get(0);
			this.person_id=(Integer) results.get(i).get(1);
			int hash=hashCode();
			if (!resultsHash.containsKey(hash))
			{
				
			resultsHash.put(hash,res);
			}
		}
		
		List<Results> resultsList =new ArrayList<Results>(resultsHash.values()); 
	return resultsList;
	}
@Override
public List<Results> getFinalResults2() {
	List<List<Object>> results=crimrep.FinalResults2();
	JSONArray json =new JSONArray (results);
	System.out.println(json);
	HashMap<Integer,Results> resultsHash=new HashMap<Integer,Results>();
	for (int i=0;i<results.size();i++)
	{
		Results res=new Results();
		res.setClient_code((String) results.get(i).get(0));
		res.setPerson_id((Integer)results.get(i).get(1));
		res.setScore((Double)results.get(i).get(3));
		res.setPerson_whole_name((String)results.get(i).get(4));
		res.setWhole_name((String) results.get(i).get(2));
		this.client_code=(String) results.get(i).get(0);
		this.person_id=(Integer) results.get(i).get(1);
		int hash=hashCode();
		if (!resultsHash.containsKey(hash))
		{
		resultsHash.put(hash,res);
		}
	}

    
	List<Results> resultsList =new ArrayList<Results>(resultsHash.values()); 
return resultsList;	
}
private String[] convertToStringArray (Results res)
{
	
		String[] list=new String[4];
		list[0]=res.getClient_code();
		list[3]=(res.getPerson_whole_name());
		list[1]=(res.getWhole_name());
		list[2]=res.getPerson_id().toString();
	return list;
}
@Override
public Outcome returnOutcome() throws IOException {
	 CSVWriter writer = new CSVWriter(new FileWriter("finalresults.csv"));
	 String [] header1={"Records found in finallist but not in referencelist"};
	 writer.writeNext(header1);
	 for (Results res : getFinalResults1())
	 {
		 writer.writeNext(convertToStringArray(res));
	 }
	 writer.flush();
	String [] header2={"Records found in referencelist but not in finallist"};
	 writer.writeNext(header2);
	 for (Results res : getFinalResults2())
	 {
		 writer.writeNext(convertToStringArray(res));
	 }
	 writer.flush();
	 writer.close();
	Outcome Outcome=new Outcome();
		Outcome.setResultsList1(getFinalResults1());
		Outcome.setResultsList2(getFinalResults2());
	return Outcome;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((client_code == null) ? 0 : client_code.hashCode());
	result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	FinalResultsService other = (FinalResultsService) obj;
	if (client_code == null) {
		if (other.client_code != null)
			return false;
	} else if (!client_code.equals(other.client_code))
		return false;
	if (person_id == null) {
		if (other.person_id != null)
			return false;
	} else if (!person_id.equals(other.person_id))
		return false;
	return true;
}	 
}