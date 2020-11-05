package com.mmkalva.genesys.service;

import java.util.List;

import com.mmkalva.genesys.api.PersistanceParametersDT;

public interface ApplicationService {

	public List<String> processFile(PersistanceParametersDT parameter);
	

}
