package com.example.demo.Handlers;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.Dtos.BalayageResultsDto;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.CriminalRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Criminal;
import reactor.core.publisher.Mono;
@Component
public class BalayagesResultsHandler  {
	@Autowired
	ClientRepository clientrep;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	TokenHandler tokenHandler;
	@Autowired 
	BalayagesStateHandler stateHandler;
	@Autowired 
	CriminalRepository crimrep;
	@Value("${ResultsServer}")
	private String adress;
	@Value("${SearchUrl}")
	private String SearchUrl;
	private Boolean done=false;
	private Boolean completed=true;
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	@Scheduled (fixedRate=1000*600,initialDelay=1000*15)
	public void GetBalayages () 
	{
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
         ArrayList<Client> clientlist=new ArrayList<Client>();
         //if (stateHandler.getStatus().equals("COMPLETED"))
         if (completed)
			{
		for (int i=0;i<response.getItems().size();i++)
		{
			Client client =clientrep.findByCode(response.getItems().get(i).getClient_code());
			if (client==null)
			{
				client=new Client();
				client.setClient_code(response.getItems().get(i).getClient_code());
				client.setFirst_name(response.getItems().get(i).getFirst_name());
				client.setLast_name(response.getItems().get(i).getLast_name());
				client.setWhole_name(response.getItems().get(i).getWhole_name());
			}
				for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
				{ 
					Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
					 Criminal criminal=crimrep.findByCode(person_id);
					 if (criminal==null)
					 {
						 criminal=new Criminal();
					Double score=response.getItems().get(i).getSearchItems().get(j).getScore();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
					 }
					criminal.getClients().add(client);
				client.getMatched_criminals().add(criminal);
				}
				clientlist.add(client);
			}
		clientrep.saveAll(clientlist);
		clientlist.clear();
			}
				else {
					
					for (int i=0;i<response.getItems().size();i++)
					{
					Client client =clientrep.findByCode(response.getItems().get(i).getClient_code());
					if (client==null)
					{
					client=new Client();
					client.setClient_code(response.getItems().get(i).getClient_code());
					client.setFirst_name(response.getItems().get(i).getFirst_name());
					client.setLast_name(response.getItems().get(i).getLast_name());
					client.setWhole_name(response.getItems().get(i).getWhole_name());
					}
					for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
					{
						Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
					 Criminal criminal=crimrep.findByCode(person_id);
					 if (criminal==null)
					 {
						 criminal=new Criminal();
						 criminal.setPerson_id(person_id);
						 criminal.setScore(response.getItems().get(i).getSearchItems().get(j).getScore());
					 }
					client.getMatched_reference_criminals().add(criminal);
				criminal.getClientsreference().add(client);
					}	
					clientlist.add(client);		
		}
					
					clientrep.saveAll(clientlist);
					clientlist.clear();
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
     
    //if (stateHandler.getStatus().equals("COMPLETED"))
       if (completed)
		{
       for (int i=0;i<response1.getItems().size();i++)
		{
    	   Client client =clientrep.findByCode(response1.getItems().get(i).getClient_code());
			if (client==null)
			{ 
				client=new Client();
				client.setClient_code(response1.getItems().get(i).getClient_code());
				client.setFirst_name(response1.getItems().get(i).getFirst_name());
				client.setLast_name(response1.getItems().get(i).getLast_name());
				client.setWhole_name(response1.getItems().get(i).getWhole_name());
			}
				for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
				{ 
					Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
					 Criminal criminal=crimrep.findByCode(person_id);
					 if (criminal==null)
					 {
						 criminal=new Criminal();
					Double score=response1.getItems().get(i).getSearchItems().get(j).getScore();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
					 }
					criminal.getClients().add(client);
				client.getMatched_criminals().add(criminal);
				}
				clientlist.add(client);
			}
   	if (clientlist.size()==100)
	{
		clientrep.saveAll(clientlist);
		clientlist.clear();
		System.out.println("SAVED 100");
		
	}
		}
    	   else
    		   {
    		   for (int i=0;i<response1.getItems().size();i++)
				{
    			   Client client =clientrep.findByCode(response1.getItems().get(i).getClient_code());
					if (client==null)
					{
					client=new Client();
					client.setClient_code(response1.getItems().get(i).getClient_code());
					client.setFirst_name(response1.getItems().get(i).getFirst_name());
					client.setLast_name(response1.getItems().get(i).getLast_name());
					client.setWhole_name(response1.getItems().get(i).getWhole_name());
					}
					for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
					{
						Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
					 Criminal criminal=crimrep.findByCode(person_id);
					 if (criminal==null)
					 {
						 criminal=new Criminal();
						 criminal.setPerson_id(person_id);
						 criminal.setScore(response1.getItems().get(i).getSearchItems().get(j).getScore());
					 }
					client.getMatched_reference_criminals().add(criminal);
				criminal.getClientsreference().add(client);
					}	
					clientlist.add(client);
	           }
    		 	if (clientlist.size()==100)
    			{
    				clientrep.saveAll(clientlist);
    				clientlist.clear();
    				System.out.println("SAVED 100");
    				
    			}
		 }
		start+=10;
		}	
		this.done=true;
	}
	}