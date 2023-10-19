package com.desafios.security;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class Crypto {
	public static String sha512(String text) throws Exception{
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
		messageDigest.reset();
		messageDigest.update(text.getBytes("utf8"));
		return String.format("%064x", new BigInteger(1, messageDigest.digest()));
	}
}
