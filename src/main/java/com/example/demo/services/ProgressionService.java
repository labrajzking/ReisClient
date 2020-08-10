package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Handlers.BalayagesResultsHandler;
import com.example.demo.Handlers.BalayagesStateHandler;
import com.example.demo.POJOS.BalayagesProgression;
@Service
public class ProgressionService implements ProgressionServiceI {
@Autowired 
BalayagesStateHandler stateHandler;
@Autowired
BalayagesResultsHandler resultshandler;
public BalayagesProgression returnProgression() {
	
	BalayagesProgression prog=new BalayagesProgression();
	prog.setForcedone(stateHandler.getForcedone());
	prog.setProgression(stateHandler.getProgression());
	return prog;
}
@Override
public Boolean returnResultsProgression() {
	System.out.println("Results Saving Is"+resultshandler.getDone());
	return resultshandler.getDone();
}
}
