package com.ex.javafx.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import com.ex.dto.Cheque;


public class ChequeService {
	private String mainPath = "http://127.0.0.1:9018/";

	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IChequeService proxy = rtarget.proxy(IChequeService.class);

	public boolean printCheque(Cheque cheque) {
		return this.proxy.printCheque(cheque);
	}

}
