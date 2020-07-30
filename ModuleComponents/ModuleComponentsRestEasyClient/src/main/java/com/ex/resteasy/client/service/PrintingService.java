package com.ex.resteasy.client.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.ex.resteasy.client.proxy.IPrintingService;
import com.ex.util.FileNameUtil;

public class PrintingService {
	private String mainPath = "http://127.0.0.1:9017/print";

	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IPrintingService proxy = rtarget.proxy(IPrintingService.class);
	
	public boolean printDocument(String parentFolder, String endsWith, Long id) {
		String fileName = FileNameUtil.getFileName(parentFolder, endsWith, id);
		return this.proxy.printDocument(fileName);
	}
	
	public boolean printDocuments(List<String> fileNames) {
		return this.proxy.printDocuments(fileNames);
	}
}
