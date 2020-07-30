package com.ex.resteasy.client.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.ex.entity.cheque.Cheque;
import com.ex.entity.cheque.ChequeOfVoucher;
import com.ex.resteasy.client.proxy.IChequeService;


public class ChequeService {
	private String mainPath = "http://127.0.0.1:9018/cheque";

	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IChequeService proxy = rtarget.proxy(IChequeService.class);

	public boolean writeChequeToPDF(Cheque cheque) {
		return this.writeChequeToPDF(cheque);
	}
	
	public boolean writeChequeToPDFs(List<Cheque> cheques) {
		return this.writeChequeToPDFs(cheques);
	}
	
	public void saveCheque(ChequeOfVoucher cheque) {
		this.proxy.saveCheque(cheque);
	}
	
	public void saveChequesOfVoucher(List<ChequeOfVoucher> cheques) {
		this.proxy.saveChequesOfVoucher(cheques);
	}

	public void saveCheques(List<ChequeOfVoucher> cheques) {
		this.proxy.saveCheques(cheques);
	}
}
