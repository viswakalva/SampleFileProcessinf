package com.mmkalva.genesys.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.mmkalva.genesys.service.ApplicationService;

public class CamelConfig {
	
	@Autowired
	private ApplicationService appService;

	@Bean(name = "filerouteBuilder")
	public RouteBuilder subscribeRouteBuilder() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				DataFormat csvFile = new BeanIODataFormat("PracticeFile.csv","genesysSampleFile");
				
				onException(Exception.class).log("Exception occured").handled(true).log("*** Can Page if needed");
				
				from("file://C:\\Users\\manju\\Desktop\\Genesys?delete=true").log("File read").setHeader("extensionFormat", simple("${file:name.ext}")).unmarshal(csvFile)
				.bean(appService,"csvProcessFile");
				
			}

		};
	}

}
 