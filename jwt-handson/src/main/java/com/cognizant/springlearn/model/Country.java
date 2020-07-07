package com.cognizant.springlearn.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {
	@NotNull
	@Size(min=2, max=2, message="Country code should be 2 characters")
	private String code;
	@NotNull
	private String name;
	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);
	
	public Country() {
		LOGGER.debug("Inside Country Constructor");
	}
	
	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		LOGGER.debug("Inside get code");
		return code;
	}

	public void setCode(String code) {
		LOGGER.debug("Inside set code");
		this.code = code;
	}

	public String getName() {
		LOGGER.debug("Inside get name");
		return name;
	}

	public void setName(String name) {
		LOGGER.debug("Inside set name");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}
	
}
