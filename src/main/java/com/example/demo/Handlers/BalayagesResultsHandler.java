package com.example.demo.Handlers;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.Dtos.BalayageResultsDto;
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
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public void GetBalayages () 
	{
			Client client = new Client();
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
         if (stateHandler.getStatus().equals("COMPLETED"))
			{
        	 crimrep.deleteAll();
		for (int i=0;i<response.getItems().size();i++)
		{
			
				client.setClient_code(response.getItems().get(i).getClient_code());
				client.setFirst_name(response.getItems().get(i).getFirst_name());
				client.setLast_name(response.getItems().get(i).getLast_name());
				client.setWhole_name(response.getItems().get(i).getWhole_name());
				Criminal criminal=new Criminal();
				for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
				{ 
					Double score=response.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
					criminal.getClients().add(client);
				client.getMatched_criminals().add(criminal);
			ArrayList<Client> clientlist =new ArrayList<Client>();
			clientlist.add(client);
			criminal.setClients(clientlist);
				crimrep.save(criminal);
				}
			}
			}
				else {
					crimrefrep.deleteAll();
					for (int i=0;i<response.getItems().size();i++)
					{
					ReferenceResults refres=new ReferenceResults();
					refres.setClient_code(response.getItems().get(i).getClient_code());
					CriminalReference crimref = new CriminalReference();
					for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
					{
						Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
						crimref.setPerson_id(person_id);
					refres.getMatched_criminals().add(crimref);
					ArrayList<ReferenceResults> clientlist=new ArrayList<ReferenceResults>();
					clientlist.add(refres);
				crimref.setClients(clientlist);
					crimrefrep.save(crimref);
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
       if (stateHandler.getStatus().equals("COMPLETED"))
		{
       for (int i=0;i<response1.getItems().size();i++)
		{
		
				client.setClient_code(response1.getItems().get(i).getClient_code());
				client.setFirst_name(response1.getItems().get(i).getFirst_name());
				client.setLast_name(response1.getItems().get(i).getLast_name());
				client.setWhole_name(response1.getItems().get(i).getWhole_name());
				Criminal criminal=new Criminal();
				for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
				{
					
					Double score=response1.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
				criminal.getClients().add(client);
				client.getMatched_criminals().add(criminal);
				ArrayList<Client> clientlist =new ArrayList<Client>();
				clientlist.add(client);
				criminal.setClients(clientlist);
				crimrep.save(criminal);
				}
			}
		}
    	   else
    		   {
    		   for (int i=0;i<response1.getItems().size();i++)
    			{
    		   ReferenceResults refres=new ReferenceResults();
			refres.setClient_code(response1.getItems().get(i).getClient_code());
			CriminalReference crimref = new CriminalReference();
			for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
			{
				Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
				crimref.setPerson_id(person_id);
				crimref.getClients().add(refres);
			refres.getMatched_criminals().add(crimref);
			ArrayList<ReferenceResults> clientlist=new ArrayList<ReferenceResults>();
			clientlist.add(refres);
		crimref.setClients(clientlist);
			crimrefrep.save(crimref);
			}		
		}
		 }
		start+=10;
		}	
		this.done=true;
	}
	}