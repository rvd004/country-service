package com.nordea.countryapi.worker;

import com.nordea.countryapi.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class CountryWorkerTest {
    @Mock
    private CountryService countryService;
    private CountryWorker countryWorker;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        countryWorker = new CountryWorker(countryService);
    }


    @Test
    void getCountryListJson() {
        List<LinkedHashMap<String, String>> rsCountryList = new ArrayList<>();
        LinkedHashMap<String, String> country = new LinkedHashMap<>();
        country.put("name", "Finland");
        country.put("alpha2Code", "FI");
        rsCountryList.add(country);

        //given
        given(countryService.getCountries()).willReturn(rsCountryList);

        //when
        String result = countryWorker.getCountryListJson();

        //then
        assertEquals("{\"countries\":[{\"name\":\"Finland\",\"country_code\":\"FI\"}]}", result);
    }

    @Test
    void getCountryInfo() {
        LinkedHashMap<String, Object> country = new LinkedHashMap<>();
        country.put("name", "Finland");
        country.put("alpha2Code", "FI");

        //given
        given(countryService.getCountryByCode(anyString())).willReturn(country);

        //when
        String result = countryWorker.getCountryInfo("FI");

        //then
        assertEquals("{\"name\":\"Finland\",\"country_code\":\"FI\",\"capital\":\"\",\"population\":0,\"flag_file_url\":\"\"}", result);
    }
}
