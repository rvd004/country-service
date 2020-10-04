package com.nordea.countryapi.worker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Custom exception class for Country API Application
 *
 * @author Raghu
 */
@Component
public class CountryAppException extends RuntimeException {

    private static final long serialVersionUID = 132323235L;

    @Getter
    @Setter
    private String exceptionMsg;

    /**
     * Public constructor
     */
    public CountryAppException() {
        super();
    }

    /**
     * Constructor with Message
     *
     * @param exceptionMessage
     */
    public CountryAppException(String exceptionMessage) {
        super();
        this.exceptionMsg = exceptionMessage;
    }

}


