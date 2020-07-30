package com.ex.javafx.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ex.dto.ChequeVoucher;

public interface IVoucherService<T> {

	@POST
	@Path("/edit")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean editVoucher(T voucher);

	@POST
	@Path("/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public long saveVoucher(T voucher);

	@POST
	@Path("/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public ChequeVoucher saveChequeVoucher(T voucher);

	@POST
	@Path("/save/print")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public long savePrintVoucher(T voucher);

	@POST
	@Path("/print")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Boolean printVoucher(Long id);

	@GET
	@Path("/get")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public T getVoucher(Long voucherId);

	@GET
	@Path("/gets")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public List<T> getVouchers();

	@GET
	@Path("/gets/date")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public List<T> getVouchersByDateBetween(ZonedDateTime from, ZonedDateTime to, String name);
}
