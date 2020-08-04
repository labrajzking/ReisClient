package com.example.demo.services;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.CriminalRepository;
@Service
public class FinalResultsService implements FinalResultsServiceI{
@Autowired 
CriminalRepository crimrep;
	@Override
	public List<String> getFinalResults() {
		List<String> results=crimrep.FinalResults();
		JSONArray json =new JSONArray (results);
		System.out.println(json);
	return results;
	}
}
