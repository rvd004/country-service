package com.nordea.countryapi.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface CountryService {
    List<LinkedHashMap<String, String>> getCountries();

    LinkedHashMap<String, Object> getCountryByCode(String countryCode);
}
