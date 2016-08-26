package com.cognizone.tests.manju.messageapi.model;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

@Component
public class Message {
	
	private BigInteger id;
	
	private String message;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
