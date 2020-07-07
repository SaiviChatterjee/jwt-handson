package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
	Validator validator=factory.getValidator();
	
	@GetMapping("/country")
	public Country getCountryIndia() {
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		Country country=context.getBean("in",Country.class);
		return country;
	}
	@GetMapping("/countries")
	public List<Country> getAllCountries(){
		return countryService.getCountries();
	}
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		return countryService.getCountry(code);
	}
	@PostMapping("/countries")
	public Country addCountry(@RequestBody @Valid Country country) {
//		Set<ConstraintViolation<Country>> violations=validator.validate(country);
//		List<String> errors=new ArrayList<String>();
//		for(ConstraintViolation<Country> violation:violations) {
//			errors.add(violation.getMessage());
//		}
//		if(violations.size()>0) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.toString());
//		}
		return countryService.addCountry(country);
	}
	
	@DeleteMapping("/countries/{code}")
	public Country deleteCountry(@PathVariable String code) throws CountryNotFoundException {
		return countryService.deleteCountry(code);
	}
}
