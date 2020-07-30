package com.ex.javafx.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.ex.dto.Cheque;


public interface IChequeService {
	@POST
	@Path("/cheques/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean saveCheques(List<Cheque> cheques);

	@POST
	@Path("/cheque/print")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean printCheque(Cheque cheque);

}
