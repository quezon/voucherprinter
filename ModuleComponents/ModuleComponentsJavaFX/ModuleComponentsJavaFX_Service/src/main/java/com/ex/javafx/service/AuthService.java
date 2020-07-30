package com.ex.javafx.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class AuthService {
	private String mainPath = "http://127.0.0.1:9017/";

	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IAuthService proxy = rtarget.proxy(IAuthService.class);

	public String login(String username, String password) {
		return this.proxy.login(username, password);
	}

	public boolean logout(String token) {
		return this.proxy.logout(token);
	}
}
