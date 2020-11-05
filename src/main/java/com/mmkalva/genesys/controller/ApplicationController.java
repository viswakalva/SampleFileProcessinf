package com.mmkalva.genesys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmkalva.genesys.api.PersistanceParametersDT;
import com.mmkalva.genesys.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	 @PostMapping("/saveFile")
	  private List<String> saveFile(@RequestBody PersistanceParametersDT parameter) {
	    return applicationService.processFile(parameter);
	  }
	
}
