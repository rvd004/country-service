package com.nordea.countryapi.controller;

import com.nordea.countryapi.common.APICommonConstants;
import com.nordea.countryapi.model.CountryResponseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CountryEndpoint {

    /**
     * Endpoint will provide the total list of country with basic information
     *
     * @return ResponseEntity<String>
     */
    @ResponseBody
    @ApiOperation(httpMethod = APICommonConstants.GET_METHOD_STR, value = APICommonConstants.COUNTRY_DET_INFO_STR)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = APICommonConstants.SUCCESS_MSG, response = CountryResponseVO.class),
            @ApiResponse(code = 404, message = APICommonConstants.NOT_FOUND_MSG),
            @ApiResponse(code = 500, message = APICommonConstants.ISE_MSG),
            @ApiResponse(code = 503, message = APICommonConstants.SERVICE_MSG)
    })
    @GetMapping(value = APICommonConstants.COUNTRY_CONTEXT_STR, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> getCountries();

    /**
     * Endpoint will provide additional information of the country code passed as param
     *
     * @param countryCode
     * @return
     */
    @ResponseBody
    @ApiOperation(httpMethod = APICommonConstants.GET_METHOD_STR, value = APICommonConstants.COUNTRY_INFO_NAME_STR)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = APICommonConstants.SUCCESS_MSG, response = CountryResponseVO.class),
            @ApiResponse(code = 404, message = APICommonConstants.NOT_FOUND_MSG),
            @ApiResponse(code = 500, message = APICommonConstants.ISE_MSG),
            @ApiResponse(code = 503, message = APICommonConstants.SERVICE_MSG)
    })
    @GetMapping(value = APICommonConstants.NAME_CONTEXT_STR, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> getCountryByCode(
            @ApiParam(name = APICommonConstants.CCODE_PATH_STR, required = true,
                    value = APICommonConstants.CCODE_INFO_STR, example = APICommonConstants.CCODE_EX_STR)
            @PathVariable(name = APICommonConstants.CCODE_PATH_STR) String countryCode);

}
