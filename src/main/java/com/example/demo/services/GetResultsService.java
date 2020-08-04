package com.example.demo.services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Handlers.BalayagesResultsHandler;
import com.example.demo.repositories.ClientRepository;
@Service
public class GetResultsService implements GetResultsServiceI {

	@Autowired 
	ClientRepository resrep;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BalayagesResultsHandler balayagesresultshandler;
  @Override
	public void GetResults() 
	{
		balayagesresultshandler.GetBalayages();
	}

}