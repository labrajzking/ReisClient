package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Handlers.BalayagesResultsHandler;
import com.example.demo.Handlers.BalayagesStateHandler;
@Service
public class ProgressionService implements ProgressionServiceI {
@Autowired 
BalayagesStateHandler stateHandler;
@Autowired
BalayagesResultsHandler resultshandler;
public Double returnProgression() {
	System.out.println("Progression!!!!!"+stateHandler.getProgression());
	return stateHandler.getProgression();
}
@Override
public Boolean returnResultsProgression() {
	System.out.println("Results Saving Is"+resultshandler.getDone());
	return resultshandler.getDone();
}
}
