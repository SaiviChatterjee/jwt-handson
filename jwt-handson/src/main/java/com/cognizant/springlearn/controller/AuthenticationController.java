package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
	
	private Logger LOGGER=LoggerFactory.getLogger(AuthenticationController.class);
	
	@GetMapping("/authenticate")
	public Map<String,String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Start");
		LOGGER.debug("Authorizaion Header:{}",authHeader);
		String user=getUser(authHeader);
		LOGGER.debug("Decoded AuthHeader:{}",user);
		Map<String,String> map=new HashMap<String, String>();
		map.put("token", generateJwt(user));
		LOGGER.info("End");
		return map;
	}
	
	private String getUser(String authHeader) {
		String decodedAuthHeader = new String(Base64.getDecoder().decode(authHeader.substring(6)));
		return decodedAuthHeader.split(":")[0];
	}
	
	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		
		builder.setIssuedAt(new Date());
		
		builder.setExpiration(new Date((new Date()).getTime()+1200000));
		builder.signWith(SignatureAlgorithm.HS256,"secretkey");
		
		String token=builder.compact();
		return token;
		
	}
}
