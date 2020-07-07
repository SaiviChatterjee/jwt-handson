package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.repository.CountryDao;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private CountryDao countryDao;
	
	public List<Country> getCountries(){
		return countryDao.getCountries();
	}
	
	public Country getCountry(String code) throws CountryNotFoundException {
		return countryDao.getCountry(code);
	}
	
	public Country addCountry(Country country) {
		return countryDao.addCountry(country);
	}

	public Country deleteCountry(String code) throws CountryNotFoundException {
		return countryDao.deleteCountry(code);
	}
}
