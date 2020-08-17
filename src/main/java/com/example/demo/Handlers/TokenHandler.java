package com.example.demo.Handlers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.Dtos.AdminDto;
import com.example.demo.Dtos.AuthResponseDto;
import com.example.demo.POJOS.*;
@Order(value=1)
@Component
public class TokenHandler implements CommandLineRunner{
	private String jwt;
	@Autowired 
	ModelMapper modelMapper;
	@Value ("${BalayagesServer}")
	private String adress;
	@Value ("${TokenUrl}")
	private String TokenUrl;
	@Value("${RefreshTokenUrl}")
	private String RefreshTokenUrl;
	private Boolean tokencatched=false;
	public TokenHandler() {
	
	}
	
	
	private void GetToken (AdminDto admin)
	{
	WebClient.RequestHeadersSpec<?> request = WebClient
			  .create(adress+TokenUrl)
		  .post()
			  .body(BodyInserters.fromObject(admin));
	AuthResponseDto response = request
			  .retrieve()
			  .bodyToMono(AuthResponseDto.class)
			  .block();
	String TokenKey=response.getToken();
	System.out.println(TokenKey);
	this.jwt=TokenKey;
	tokencatched=true;
	}
	private AdminDto convertToADto (Admin admin)
	{ 
		modelMapper.getConfiguration()
		  .setFieldMatchingEnabled(true);
		AdminDto adminDto=modelMapper.map(admin,AdminDto.class);
		return adminDto;
	}
	
	@Scheduled(fixedRate=1000*60)
public void RefreshToken()
{
		if (tokencatched)
		{
	WebClient.RequestHeadersSpec<?> request = WebClient
			  .create(adress+RefreshTokenUrl)
		  .get()
		  .header("x-auth-token",jwt);
	AuthResponseDto response = request
			  .retrieve()
			  .bodyToMono(AuthResponseDto.class)
			  .block();
	String TokenKey=response.getToken();
	System.out.println("REFRESHED TOKEN");
	System.out.println(TokenKey);
	this.jwt=TokenKey;
	}
}
	public String getJwt() {
		return jwt;
	}


	public void setJwt(String jwt) {
		this.jwt = jwt;
	}


	@Override
	public void run(String... args) throws Exception {
		Admin admin= new Admin();
		admin.setUser_name("admin");
		admin.setPassword("changeit");
		AdminDto admindto=convertToADto(admin);
		GetToken(admindto);
		
	}

		
	}

