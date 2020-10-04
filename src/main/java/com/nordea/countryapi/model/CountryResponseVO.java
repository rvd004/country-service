package com.nordea.countryapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Model Object for the Country restCountries API
 *
 * @author Raghu
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countries"
})
@ApiModel
@Data
public class CountryResponseVO {

    @JsonProperty("countries")
    private List<CountryListDetailVO> countries;

}



