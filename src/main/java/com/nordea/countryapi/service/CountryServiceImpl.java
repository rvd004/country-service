package com.nordea.countryapi.service;

import com.google.common.net.HttpHeaders;
import com.nordea.countryapi.worker.CountryAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * CountryAPIServiceProxy to call the country rest service via Spring Reactive WebClient
 *
 * @author Raghu
 */
@Slf4j
@Service
@Configuration
public class CountryServiceImpl implements CountryService {

    public static final String COUNTRY_RS_ALL_URI = "/all";

    public static final String COUNTRY_RS_NAME_URI = "/alpha/";

    public static final String COUNTRY_RS_ALL_FILTER = "?fields=name;alpha2Code;alpha3Code";

    public static final String COUNTRY_RS_NAME_FILTER = "?fields=name;alpha2Code;alpha3Code;capital;population;flag";

    private final WebClient webClient;

    @Autowired
    public CountryServiceImpl(@Value("${countries.baseurl}") String countriesBaseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(countriesBaseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .build();
    }

    /**
     * Method to get the list of all countries
     *
     * @return List<CountryResponseVO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LinkedHashMap<String, String>> getCountries() {

        List<LinkedHashMap<String, String>> rsCountryList = new ArrayList<>();

        try {
            rsCountryList = webClient
                    .get()
                    .uri(COUNTRY_RS_ALL_URI + COUNTRY_RS_ALL_FILTER)
                    .retrieve()
                    .onStatus(HttpStatus::isError, this::handleErrorResponse)
                    .bodyToMono(rsCountryList.getClass())
                    .block();
            log.error("Response from country rest service {}", rsCountryList);

        } catch (CountryAppException caEx) {
            log.error("CountryAppException while calling the country rest service [{}]", caEx.getMessage());
        } catch (Exception ex) {
            log.error("Exception while calling the country rest service [{}]", ex.getMessage());
        }


        return rsCountryList;
    }

    /**
     * Method to get the country info by passing country code
     *
     * @return List<CountryResponseVO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public LinkedHashMap<String, Object> getCountryByCode(String countryCode) {

        LinkedHashMap<String, Object> countryInfoMap = new LinkedHashMap<>();

        try {
            countryInfoMap = webClient
                    .get()
                    .uri(COUNTRY_RS_NAME_URI + countryCode + COUNTRY_RS_NAME_FILTER)
                    .retrieve()
                    .onStatus(HttpStatus::isError, this::handleErrorResponse)
                    .bodyToMono(countryInfoMap.getClass())
                    .block();
            log.error("Response from country rest service {}", countryInfoMap);

        } catch (CountryAppException caEx) {
            log.error("CountryAppException while calling the country info rest service [{}]", caEx.getMessage());
        } catch (Exception ex) {
            log.error("Exception while calling the country info rest service [{}]", ex.getMessage());
        }

        return countryInfoMap;
    }

    private Mono<CountryAppException> handleErrorResponse(ClientResponse clientResponse) {
        throw new CountryAppException(clientResponse.statusCode().toString());
    }

    private ExchangeFilterFunction logRequest() {
        return (clientReq, next) -> {
            log.info("Request: {} {}", clientReq.method(), clientReq.url());
            clientReq.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return next.exchange(clientReq);
        };
    }


}


