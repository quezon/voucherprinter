package com.ex.resteasy.client.proxy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.ex.entity.cheque.Cheque;
import com.ex.entity.cheque.ChequeOfVoucher;


public interface IChequeService<T> {
	
	@POST
	@Path("/writeToPDF")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean writeChequeToPDF(Cheque cheque);
	
	@POST
	@Path("/writeToPDFs")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean writeChequeToPDFs(List<Cheque> cheques);

	@POST
	@Path("/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public void saveCheque(T cheque);

	@POST
	@Path("/saves")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public void saveChequesOfVoucher(List<ChequeOfVoucher> cheques);
	
	@POST
	@Path("/saves/many")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public void saveCheques(List<Cheque> cheques);
}
