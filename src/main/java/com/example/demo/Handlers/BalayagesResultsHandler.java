package com.example.demo.Handlers;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.Dtos.BalayageResultsDto;
import com.example.demo.Dtos.ClientDto;
import com.example.demo.Dtos.CriminalDto;
import com.example.demo.Dtos.ReferenceResultsDto;
import com.example.demo.repositories.ClientRefRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.CriminalRefRepository;
import com.example.demo.repositories.CriminalRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Criminal;
import com.example.demo.entities.CriminalReference;
import com.example.demo.entities.ReferenceResults;

import reactor.core.publisher.Mono;

@Component
public class BalayagesResultsHandler  {
	@Autowired
	CriminalRefRepository crimrefrep;
	@Autowired 
	ClientRepository resrep;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	TokenHandler tokenHandler;
	@Autowired 
	BalayagesStateHandler stateHandler;
	@Autowired
	ClientRefRepository refrep;
	@Autowired 
	CriminalRepository crimrep;
	@Value("${ResultsServer}")
	private String adress;
	@Value("${SearchUrl}")
	private String SearchUrl;
	private Client convertToEntity (ClientDto clientdto)
	{ 
		
		Client client=modelMapper.map(clientdto,Client.class);
		return client;
	}
	private Criminal convertToCEntity (CriminalDto criminaldto)
	{
		Criminal criminal=modelMapper.map(criminaldto,Criminal.class);
		return criminal;
	}
	public void GetBalayages () 
	{
			ClientDto clientdto = new ClientDto();
			int start=0;
			int end=10;
			String body = "{\"startRow\":"+start+",\"endRow\":"+end+",\"sortModel\":[],\"filterModel\":{},\"search_sources\":[\"BATCH\"]}";
		    System.out.println(body);
			WebClient.RequestHeadersSpec<?> request = WebClient
				  .create(adress+SearchUrl)
			  .post()
			  .header("x-auth-token",tokenHandler.getJwt())
			  .header("Content-Type","application/json")
		.body(BodyInserters.fromPublisher(Mono.just(body),String.class));
         BalayageResultsDto  response = request
				  .retrieve()
				  .bodyToMono(BalayageResultsDto.class)
				  .block();
		for (int i=0;i<response.getItems().size();i++)
		{
			if (stateHandler.getStatus().equals("COMPLETED"))
			{
				clientdto.setClient_code(response.getItems().get(i).getClient_code());
				clientdto.setFirst_name(response.getItems().get(i).getFirst_name());
				clientdto.setLast_name(response.getItems().get(i).getLast_name());
				clientdto.setWhole_name(response.getItems().get(i).getWhole_name());
				Client client=	convertToEntity(clientdto);
				ArrayList<Criminal> criminallist=new ArrayList<Criminal>();
				for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
				{
					CriminalDto criminaldto=new CriminalDto();
					Double score=response.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
					criminaldto.setPerson_id(person_id);
					criminaldto.setScore(score);
				criminaldto.getClients().add(client);
				Criminal criminal =convertToCEntity(criminaldto);
				criminallist.add(criminal);
				clientdto.getMatched_criminals().add(criminal);
				if (j==response.getItems().get(i).getSearchItems().size()-1)
				{	
			crimrep.saveAll(criminallist);	
				}
				}
			}
				else {
					ReferenceResultsDto refresdto=new ReferenceResultsDto();
					refresdto.setClient_code(response.getItems().get(i).getClient_code());
					ReferenceResults refres=modelMapper.map(refresdto,ReferenceResults.class);
					ArrayList<CriminalReference> criminallist=new ArrayList<CriminalReference>();
					for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
					{
						CriminalReference crimref = new CriminalReference();
						Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
						crimref.setPerson_id(person_id);
					crimref.getClients().add(refres);
					criminallist.add(crimref);
					refresdto.getMatched_criminals().add(crimref);
					if (j==response.getItems().get(i).getSearchItems().size()-1)
					{
						System.out.println(criminallist);
				crimrefrep.saveAll(criminallist);
					}
					}		
		}
		}
		 Long size=(Long) response.getSize();
		 String body1 ;
		for (start=10;start<size;)
		{
			end+=10;
	 body1="{\"startRow\":"+start+",\"endRow\":"+end+",\"sortModel\":[],\"filterModel\":{},\"search_sources\":[\"BATCH\"]}";
		System.out.println(body1);
		WebClient.RequestHeadersSpec<?> request1 = WebClient
				  .create(adress+SearchUrl)
			  .post()
			  .header("x-auth-token",tokenHandler.getJwt())
			  .header("Content-Type","application/json")
		.body(BodyInserters.fromPublisher(Mono.just(body1),String.class));
       BalayageResultsDto  response1 = request1
				  .retrieve()
				  .bodyToMono(BalayageResultsDto.class)
				  .block();
       for (int i=0;i<response1.getItems().size();i++)
		{
		
    	   if (stateHandler.getStatus().equals("COMPLETED"))
			{
				clientdto.setClient_code(response1.getItems().get(i).getClient_code());
				clientdto.setFirst_name(response1.getItems().get(i).getFirst_name());
				clientdto.setLast_name(response1.getItems().get(i).getLast_name());
				clientdto.setWhole_name(response1.getItems().get(i).getWhole_name());
				Client client=	convertToEntity(clientdto);
				ArrayList<Criminal> criminallist=new ArrayList<Criminal>();
				for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
				{
					CriminalDto criminaldto=new CriminalDto();
					Double score=response1.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
					criminaldto.setPerson_id(person_id);
					criminaldto.setScore(score);
				criminaldto.getClients().add(client);
				Criminal criminal =convertToCEntity(criminaldto);
				criminallist.add(criminal);
				clientdto.getMatched_criminals().add(criminal);
				if (j==response1.getItems().get(i).getSearchItems().size()-1)
				{		
			crimrep.saveAll(criminallist);
				}
				}
			}
    	   else
    		   {
    		   ReferenceResultsDto refresdto=new ReferenceResultsDto();
    		  
			refresdto.setClient_code(response1.getItems().get(i).getClient_code());
			ReferenceResults refres=modelMapper.map(refresdto,ReferenceResults.class);
			ArrayList<CriminalReference> criminallist=new ArrayList<CriminalReference>();
			for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
			{
				CriminalReference crimref = new CriminalReference();
				Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
				crimref.setPerson_id(person_id);
			crimref.getClients().add(refres);
			criminallist.add(crimref);
			refresdto.getMatched_criminals().add(crimref);
			if (j==response1.getItems().get(i).getSearchItems().size()-1)
			{
				System.out.println(criminallist);
		crimrefrep.saveAll(criminallist);
			}
			}		
		}
		 }
		start+=10;
		}	
	}
	
	}

	
	

