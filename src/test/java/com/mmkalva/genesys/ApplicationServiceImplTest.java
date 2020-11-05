package com.mmkalva.genesys;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mmkalva.genesys.api.PersistanceParametersDT;
import com.mmkalva.genesys.entity.FileData;
import com.mmkalva.genesys.repository.FileDataRepository;
import com.mmkalva.genesys.service.impl.ApplicationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {

	@Mock
	private FileDataRepository fileDataRepository;

	@InjectMocks
	private ApplicationServiceImpl applicationServiceImpl;

	@Test(expected = Exception.class)
	public void testprocessFile() throws Exception {
		PersistanceParametersDT parameter = new PersistanceParametersDT();
		parameter.setRowNumber(2);
		FileData FileData = new FileData();
		Mockito.when(fileDataRepository.save(Mockito.any(FileData.class))).thenReturn(FileData);
		List<String> listString = applicationServiceImpl.processFile(parameter);
		//Verify the data 
	}

	
}
