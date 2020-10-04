package com.nordea.countryapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Model Object for the Country restCountries API
 *
 * @author Raghu
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "country_code",
        "capital",
        "population",
        "flag_file_url"
})
@ApiModel
@Data
public class CountryInfoVO {

    @JsonProperty("capital")
    private String capital;
    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("flag_file_url")
    private String flag_file_url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("population")
    private Integer population;

}



