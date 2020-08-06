package com.example.demo.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.POJOS.Outcome;
import com.example.demo.POJOS.Results;
import com.example.demo.repositories.CriminalRepository;
@Service
public class FinalResultsService implements FinalResultsServiceI{
@Autowired 
CriminalRepository crimrep;
private String client_code;
private Integer person_id;
@Override
	public List<Results> getFinalResults1() {
		List<List<Object>> results=crimrep.FinalResults1();
		HashMap<Integer,Results> resultsHash=new HashMap<Integer,Results>();
		for (int i=0;i<results.size();i++)
		{
			Results res=new Results();
			res.setClient_code((String) results.get(i).get(1));
			res.setPerson_id((Integer)results.get(i).get(0));
			this.client_code=(String) results.get(i).get(1);
			this.person_id=(Integer) results.get(i).get(0);
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
	HashMap<Integer,Results> resultsHash=new HashMap<Integer,Results>();
	for (int i=0;i<results.size();i++)
	{
		Results res=new Results();
		res.setClient_code((String) results.get(i).get(1));
		res.setPerson_id((Integer)results.get(i).get(0));
		this.client_code=(String) results.get(i).get(1);
		this.person_id=(Integer) results.get(i).get(0);
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
public Outcome returnOutcome() {
	Outcome Outcome=new Outcome();
	if ((getFinalResults1().size())==(getFinalResults2().size()))
	{
		Outcome.setResultsList(getFinalResults1());
		Outcome.setOutcome("The Results Are even");
	}
	else if ((getFinalResults1().size())>(getFinalResults2().size()))
	{
		Outcome.setResultsList(getFinalResults1());
		Outcome.setOutcome("The Algorithme  Performance  Has Increased");
	}
	else if ((getFinalResults1().size())<(getFinalResults2().size()))
	{
		Outcome.setResultsList(getFinalResults2());
		Outcome.setOutcome("The Algorithme Performance  Has Decreased");
	}
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
