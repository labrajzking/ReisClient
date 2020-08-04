package com.example.demo.Controllers;
import java.io.IOException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Dtos.UserDto;
import com.example.demo.entities.UserE;
import com.example.demo.services.FinalResultsService;
import com.example.demo.services.GetResultsService;
import com.example.demo.services.StartBalayagesService;
import com.example.demo.services.UserService;

@RestController
public class UserController {
@Autowired
UserService userservice;
@Autowired 
ModelMapper modelMapper;
@Autowired 
GetResultsService getresultsservice;
@Autowired
StartBalayagesService startbalayagesservice;
@Autowired 
FinalResultsService finalservice;
private UserE ConvertToEntity (UserDto userdto)
{
	modelMapper.getConfiguration()
	  .setFieldMatchingEnabled(true);
	return modelMapper.map(userdto,UserE.class);
}
private UserDto ConvertToDto (UserE user)
{
	modelMapper.getConfiguration()
	  .setFieldMatchingEnabled(true);
	return modelMapper.map(user,UserDto.class);
}
@CrossOrigin(origins = "http://localhost:4200")
@PostMapping ("/signup")
public UserDto signup (@RequestBody UserDto userdto)
{	System.out.println(userdto.getUsername());
System.out.println(userdto.getPassword());
	UserE user=ConvertToEntity(userdto);
	userservice.signup(user);
	UserDto userDTO=ConvertToDto(user);
	return userDTO;
}
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
public List<String> retunnResults()
{
	System.out.println("RESULTS");
	return finalservice.getFinalResults();
}
}
