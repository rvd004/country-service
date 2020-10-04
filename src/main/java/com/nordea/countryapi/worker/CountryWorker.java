package com.nordea.countryapi.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordea.countryapi.model.CountryInfoVO;
import com.nordea.countryapi.model.CountryListDetailVO;
import com.nordea.countryapi.model.CountryResponseVO;
import com.nordea.countryapi.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Raghu
 */
@Slf4j
@Component
public class CountryWorker {


    private final CountryService countryService;

    @Autowired
    CountryWorker(CountryService countryService) {
        this.countryService = countryService;
    }

    public String getCountryListJson() {

        List<LinkedHashMap<String, String>> rsCountryList = countryService.getCountries();

        CountryResponseVO countryResponseVO = parseCountryListObject(rsCountryList);

        String resultJson = parseObjToJson(countryResponseVO);

        log.info("After parsing resultJson:: {}", resultJson);

        return resultJson;
    }

    public String getCountryInfo(String cCode) {

        LinkedHashMap<String, Object> countryInfoMap = countryService.getCountryByCode(cCode);

        CountryInfoVO countryInfoVO = parseCountryInfoObj(countryInfoMap);

        String resultJson = parseObjToJson(countryInfoVO);

        log.info("After parsing resultJson:: {}", resultJson);

        return resultJson;
    }

    private CountryResponseVO parseCountryListObject(List<LinkedHashMap<String, String>> rsCountryList) {

        CountryResponseVO countryResponseVO = new CountryResponseVO();

        List<CountryListDetailVO> countryDetailList = new ArrayList<>();

        if (rsCountryList != null) {
            rsCountryList.forEach(countryRespMap -> {
                CountryListDetailVO tempCountryDetail = new CountryListDetailVO();
                tempCountryDetail.setName(countryRespMap.get("name"));
                tempCountryDetail.setCountry_code(countryRespMap.get("alpha2Code"));
                countryDetailList.add(tempCountryDetail);
            });
        }

        countryResponseVO.setCountries(countryDetailList);

        log.info("After parsing:: {}", countryResponseVO);
        return countryResponseVO;

    }

    private CountryInfoVO parseCountryInfoObj(LinkedHashMap<String, Object> countryInfoMap) {

        CountryInfoVO countryInfoVO = new CountryInfoVO();

        if (countryInfoMap != null) {
            countryInfoVO.setName(countryInfoMap.get("name") != null ? countryInfoMap.get("name").toString() : "");
            countryInfoVO.setCountry_code(countryInfoMap.get("alpha2Code") != null ? countryInfoMap.get("alpha2Code").toString() : "");
            countryInfoVO.setCapital(countryInfoMap.get("capital") != null ? countryInfoMap.get("capital").toString() : "");
            countryInfoVO.setPopulation(countryInfoMap.get("population") != null ? (int) countryInfoMap.get("population") : 0);
            countryInfoVO.setFlag_file_url(countryInfoMap.get("flag") != null ? countryInfoMap.get("flag").toString() : "");
        }

        log.info("After parsing:: {}", countryInfoVO);
        return countryInfoVO;

    }

    private String parseObjToJson(Object sourceObj) {
        ObjectMapper mapper = new ObjectMapper();
        String parsedJson = "";
        try {
            parsedJson = mapper.writeValueAsString(sourceObj);
            System.out.println("ResultingJSONstring = " + parsedJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parsedJson;
    }


}


