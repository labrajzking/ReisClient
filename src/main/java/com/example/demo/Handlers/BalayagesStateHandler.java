package com.example.demo.Handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class BalayagesStateHandler   {
@Autowired 
	TokenHandler tokenHandler;
@Autowired 
BalayagesResultsHandler balayagesHandler;
private String status="";
@Autowired 
BalayagesActivatorHandler balayagesActivator;
@Value("${jobs.enabled:true}")
private boolean isEnabled=true;
@Value("${jobs.enabled:true}")
private boolean Launched=false;
@Value ("${BalayagesServer}")
private String adress;
@Value ("${ProgressionUrl}")
private String ProgressionUrl;
private Double progression;
private Boolean forcedone=false;
@Scheduled (fixedRate=1000*60)
private void GetState () 
{
	Launched=balayagesActivator.getLaunched();
	if (isEnabled && Launched)
	{
	WebClient.RequestHeadersSpec<?> request = WebClient
			  .create(adress+ProgressionUrl)
		  .get()
		  .header("x-auth-token",tokenHandler.getJwt());
	String response = request
			  .retrieve()
			  .bodyToMono(String.class)
			  .block();
	JSONObject obj=new JSONObject (response);
	System.out.println(obj);
	progression=(Double)obj.get("percent");
	status=(String) obj.get("status");
	System.out.println(status);
	if (status.equals("COMPLETED"))
	{ forcedone=true;
		isEnabled=false;
		balayagesHandler.GetBalayages();
	}
	}
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Double getProgression() {
	return progression;
}
public void setProgression(Double progression) {
	this.progression = progression;
}
public Boolean getForcedone() {
	return forcedone;
}
public void setForcedone(Boolean forcedone) {
	this.forcedone = forcedone;
}

}