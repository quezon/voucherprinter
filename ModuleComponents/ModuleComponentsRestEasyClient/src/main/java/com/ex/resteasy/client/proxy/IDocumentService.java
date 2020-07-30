package com.ex.resteasy.client.proxy;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.ex.entity.Voucherable;

/*
 * Saves Vouchers and Documents except Cheque in a PDF File
 */
public interface IDocumentService {
	@POST
	@Path("/save/xdoc")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean saveDocumentToFile(Voucherable voucher);
	
	@GET
	@Path("/get/name")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public String getFileName(@RequestBody Map<String, String> map);
	
	@GET
	@Path("/get/digit")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Integer getDigitSize();
	
	@GET
	@Path("/get/max")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Long getMaxId();

}
