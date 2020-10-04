package com.nordea.countryapi.controller;

import com.nordea.countryapi.worker.CountryWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to expose two endpoints to access country related information
 *
 * @author Raghu
 */
@RestController
public class CountryEndpointImpl implements CountryEndpoint {

    @Autowired
    CountryWorker countryWorker;


    public CountryEndpointImpl(CountryWorker countryWorker) {
        this.countryWorker = countryWorker;
    }

    public ResponseEntity<String> getCountries() {
        String resultJson = countryWorker.getCountryListJson();
        return new ResponseEntity<>(resultJson, HttpStatus.OK);
    }

    public ResponseEntity<String> getCountryByCode(String countryCode) {
        String resultJson = countryWorker.getCountryInfo(countryCode);
        return new ResponseEntity<>(resultJson, HttpStatus.OK);
    }
}


