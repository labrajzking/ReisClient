package com.example.demo.Handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class BalayagesActivatorHandler{
	public Boolean Launched=false;
	@Autowired 
	TokenHandler tokenHandler;
	@Value ("${BalayagesServer}")
	private String adress;
	@Value ("${ScannerUrl}")
	private String ScannerUrl;
	private String wsurl;
public Boolean getLaunched() {
		return Launched;
	}

	public void setLaunched(Boolean launched) {
		Launched = launched;
	}

public void StartBalayages () 
{
	wsurl=adress+ScannerUrl;
System.out.println("StartBalayages Launched at"+wsurl);
setLaunched(true);
	WebClient.RequestHeadersSpec<?> request = WebClient
			  .create(wsurl)
		  .get()
		  .header("x-auth-token",tokenHandler.getJwt());
	ResponseEntity response = request
			  .retrieve()
			  .bodyToMono(ResponseEntity.class)
			  .block();
}

}
