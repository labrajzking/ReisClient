package com.example.demo.Handlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.Dtos.BalayageResultsDto;
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
	private Integer person_id;
	private String client_code;
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
		for (int i=0;i<response.getItems().size();i++)
		{
			if (stateHandler.getStatus().equals("COMPLETED"))
			{
				client.setClient_code(response.getItems().get(i).getClient_code());
				client.setFirst_name(response.getItems().get(i).getFirst_name());
				client.setLast_name(response.getItems().get(i).getLast_name());
				client.setWhole_name(response.getItems().get(i).getWhole_name());
				client_code=response.getItems().get(i).getClient_code();
				//ArrayList<Criminal> criminallist=new ArrayList<Criminal>();
				HashMap <Integer,Criminal> criminalhash=new HashMap<Integer,Criminal>();
				Criminal criminal=new Criminal();
				for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
				{
					Double score=response.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
					this.person_id=person_id;
					int key= hashCode();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
				if (!criminalhash.containsKey(key))
				{
					criminal.getClients().add(client);
				criminalhash.put(key, criminal);
				client.getMatched_criminals().add(criminal);
				}
				if (j==response.getItems().get(i).getSearchItems().size()-1)
				{	
					List <Criminal> criminallist =new ArrayList<Criminal>(criminalhash.values());
			crimrep.saveAll(criminallist);	
				}
				}
			}
				else {
					ReferenceResults refres=new ReferenceResults();
					refres.setClient_code(response.getItems().get(i).getClient_code());
					client_code=response.getItems().get(i).getClient_code();
					//ArrayList<CriminalReference> criminallist=new ArrayList<CriminalReference>();
					HashMap <Integer,CriminalReference> criminalhash=new HashMap<Integer,CriminalReference>();
					CriminalReference crimref = new CriminalReference();
					for (int j=0;j<response.getItems().get(i).getSearchItems().size();j++)
					{
						Integer person_id=response.getItems().get(i).getSearchItems().get(j).getPerson_id();
						crimref.setPerson_id(person_id);
						this.person_id=person_id;
						int key= hashCode();
					if (!criminalhash.containsKey(key))
					{	
					crimref.getClients().add(refres);
					criminalhash.put(key,crimref);
					refres.getMatched_criminals().add(crimref);
					}
					
					if (j==response.getItems().get(i).getSearchItems().size()-1)
					{
						List <CriminalReference> criminallist =new ArrayList<CriminalReference>(criminalhash.values());
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
				client.setClient_code(response1.getItems().get(i).getClient_code());
				client.setFirst_name(response1.getItems().get(i).getFirst_name());
				client.setLast_name(response1.getItems().get(i).getLast_name());
				client.setWhole_name(response1.getItems().get(i).getWhole_name());
				client_code=response1.getItems().get(i).getClient_code();
				//ArrayList<Criminal> criminallist=new ArrayList<Criminal>();
				HashMap <Integer,Criminal> criminalhash=new HashMap<Integer,Criminal>();
				Criminal criminal=new Criminal();
				for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
				{
					Double score=response1.getItems().get(i).getSearchItems().get(j).getScore();
					Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
					this.person_id=person_id;
					int key= hashCode();
					criminal.setPerson_id(person_id);
					criminal.setScore(score);
				if (!criminalhash.containsKey(key))
				{
					criminal.getClients().add(client);
				criminalhash.put(key, criminal);
				client.getMatched_criminals().add(criminal);
				}
				if (j==response1.getItems().get(i).getSearchItems().size()-1)
				{	
					List <Criminal> criminallist =new ArrayList<Criminal>(criminalhash.values());
			crimrep.saveAll(criminallist);
				}
				}
			}
    	   else
    		   {
    		   ReferenceResults refres=new ReferenceResults();
    		  
			refres.setClient_code(response1.getItems().get(i).getClient_code());
			client_code=response1.getItems().get(i).getClient_code();
			//ArrayList<CriminalReference> criminallist=new ArrayList<CriminalReference>();
			HashMap <Integer,CriminalReference> criminalhash=new HashMap<Integer,CriminalReference>();
			CriminalReference crimref = new CriminalReference();
			for (int j=0;j<response1.getItems().get(i).getSearchItems().size();j++)
			{
				Integer person_id=response1.getItems().get(i).getSearchItems().get(j).getPerson_id();
				crimref.setPerson_id(person_id);
				this.person_id=person_id;
				int key= hashCode();
			if (!criminalhash.containsKey(key))
			{
				crimref.getClients().add(refres);
			criminalhash.put(key, crimref);
			refres.getMatched_criminals().add(crimref);
			}
			if (j==response1.getItems().get(i).getSearchItems().size()-1)
			{
				List <CriminalReference> criminallist =new ArrayList<CriminalReference>(criminalhash.values());
		crimrefrep.saveAll(criminallist);
			}
			}		
		}
		 }
		start+=10;
		}	
		this.done=true;
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
		BalayagesResultsHandler other = (BalayagesResultsHandler) obj;
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