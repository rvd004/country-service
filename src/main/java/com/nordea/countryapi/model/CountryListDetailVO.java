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
        "country_code"
})
@ApiModel
@Data
public class CountryListDetailVO {

    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("name")
    private String name;

}



