package com.ex.resteasy.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

public interface IAuthService {
	@POST
	@Path("/login")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public String login(String username, String password);

	@POST
	@Path("/logout")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public boolean logout(String token);
}
