package com.mmkalva.genesys.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmkalva.genesys.api.FileContent;
import com.mmkalva.genesys.api.PersistanceParametersDT;
import com.mmkalva.genesys.entity.FileData;
import com.mmkalva.genesys.repository.FileDataRepository;
import com.mmkalva.genesys.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private FileDataRepository fileDataRepository;

	@Override
	public List<String> processFile(PersistanceParametersDT parameter) {
		List<String> stringList = new ArrayList<>();
		String readLine;
		try (BufferedReader bf = new BufferedReader(new FileReader("PracticeFile.csv"))) {
			if (parameter.getRowNumber() == null) {
				// Save all rows
				while ((readLine = bf.readLine()) != null) {
					getReader(stringList, readLine);

				}

			} else if (parameter.getRowNumber() % 2 != 0) {
				// Save odd numbered rows
				int line = 0;
				while ((readLine = bf.readLine()) != null) {
					if (line % 2 != 0) {// CHECKING WHETHER THE ROW IS ODD
						getReader(stringList, readLine);
					}
					line++;
				}

			} else if (parameter.getRowNumber() % 2 == 0) {
				// Save even numbered rows
				int line = 0;
				while ((readLine = bf.readLine()) != null) {
					if (line % 2 == 0) {// CHECKING WHETHER THE ROW IS EVEN

						getReader(stringList, readLine);
					}
					line++;
				}

			}
		} catch (IOException e) {
			System.out.println("There was a problem:" + e);

		}
		return stringList;
	}

	private void getReader(List<String> stringList, String readLine) {
		String[] values;
		readLine = readLine.replaceAll("\\uFEFF", "");
		System.out.println(readLine);
		 values = readLine.split(",");
		if (!"Row Number".equalsIgnoreCase(values[0])) {
			persisData(values);
			stringList.add(readLine);
		}
	}

	private void persisData(String[] values) {
		FileData fileData = new FileData();
		fileData.setRowNumber(Long.valueOf(values[0]));
		fileData.setData1(values[1]);
		fileData.setData2(values[2]);
		fileData.setData3(values[3]);
		fileDataRepository.save(fileData);
	}

	public void csvProcessFiles(Exchange exchange) {

		@SuppressWarnings("unchecked")
		List<FileContent> fileList = (List<FileContent>) exchange.getIn().getBody();

		for (FileContent fileContent : fileList) {
			FileData fileData = new FileData();
			fileData.setRowNumber(fileContent.getRowNumber());
			fileData.setData1(fileContent.getData1());
			fileData.setData2(fileContent.getData2());
			fileData.setData3(fileContent.getData3());
			fileDataRepository.save(fileData);
		}

	}

}
