package com.ex.resteasy.client.proxy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

public interface IPrintingService {
	
	@POST
	@Path("/doc")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean printDocument(String fileName);
	
	@POST
	@Path("/docs")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean printDocuments(List<String> fileNames);
}
