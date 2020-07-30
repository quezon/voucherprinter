package com.ex.resteasy.client.service;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.web.bind.annotation.RequestBody;

import com.ex.entity.Voucherable;
import com.ex.resteasy.client.proxy.IDocumentService;

public class DocumentService {
	private String mainPath = "http://127.0.0.1:9017/doc";
	
	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IDocumentService proxy = rtarget.proxy(IDocumentService.class);
	
	public boolean saveVoucherToFile(Voucherable voucher) {
		return proxy.saveDocumentToFile(voucher);
	}
	
	
	//=================================================
	//         Document Info
	//=================================================
	
	public String getFileName(@RequestBody Map<String, String> map) {
		return proxy.getFileName(map);
	}
	
	public Integer getDigitSize() {
		return proxy.getDigitSize();
	}
	
	public Long getMaxId() {
		return proxy.getMaxId();
	}
}
