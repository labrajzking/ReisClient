package com.example.demo.Controllers;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.POJOS.BalayagesProgression;
import com.example.demo.POJOS.Outcome;
import com.example.demo.services.FinalResultsService;
import com.example.demo.services.GetResultsService;
import com.example.demo.services.ProgressionService;
import com.example.demo.services.StartBalayagesService;
@RestController
public class UserController {
@Autowired 
ModelMapper modelMapper;
@Autowired 
GetResultsService getresultsservice;
@Autowired
StartBalayagesService startbalayagesservice;
@Autowired 
FinalResultsService finalservice;
@Autowired 
ProgressionService progressionservice;
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping ("/GetResults")
public void GetResults () throws IOException
{
	getresultsservice.GetResults();
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping ("/StartBalayages")
public void StartBalayages () throws IOException
{
	startbalayagesservice.StartBalayages();
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping ("/GetFinalResults")
public Outcome retunnResults() throws IOException
{
	return finalservice.returnOutcome();
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping ("/Balayagesprogression")
public BalayagesProgression returnProgression ()
{
	return progressionservice.returnProgression();
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping ("/ResultsSavingProgression")
public Boolean returnResultsProgression()
{
return progressionservice.returnResultsProgression();
}
}