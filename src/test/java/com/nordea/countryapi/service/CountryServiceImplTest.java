package com.nordea.countryapi.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryServiceImplTest {

    private final MockWebServer mockWebServer = new MockWebServer();

    private final CountryService countryService = new CountryServiceImpl(mockWebServer.url("/").toString());

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Get Countries list")
    public void getCountries() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("[{\n" +
                                "\t\t\t\"name\": \"Finland\",\n" +
                                "\t\t\t\"alpha2Code\": \"FI\"\n" +
                                "\t\t}\n" +
                                "\t]"));

        List<LinkedHashMap<String, String>> response = countryService.getCountries();
        assertNotNull(response, "Response is not initialized");
        assertEquals(1, response.size());
        assertThat(response.get(0).get("name"), is(equalTo("Finland")));
        assertThat(response.get(0).get("alpha2Code"), is(equalTo("FI")));
    }

    @Test
    @DisplayName("Get Country by code")
    public void getCountryByCode() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{\n" +
                                "\t\"name\": \"Finland\",\n" +
                                "\t\"alpha2Code\": \"FI\",\t\n" +
                                "\t\"capital\": \"Helsinki\",\n" +
                                "\t\"population\": 5491817,\n" +
                                "\t\"flag\": \"https://restcountries.eu/data/fin.svg\"\n" +
                                "}"));

        LinkedHashMap<String, Object> response = countryService.getCountryByCode("FI");
        assertNotNull(response, "Response is not initialized");
        assertThat(response.get("name"), is(equalTo("Finland")));
        assertThat(response.get("alpha2Code"), is(equalTo("FI")));
        assertThat(response.get("capital"), is(equalTo("Helsinki")));
        assertThat(response.get("population"), is(equalTo(5491817)));
        assertThat(response.get("flag"), is(equalTo("https://restcountries.eu/data/fin.svg")));
    }
}
