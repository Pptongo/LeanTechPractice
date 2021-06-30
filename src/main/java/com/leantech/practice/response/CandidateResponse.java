package com.leantech.practice.response;

import com.leantech.practice.persistance.entities.CandidateEntity;

/**
 * The response used for web services related with the Candidates.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class CandidateResponse {
    
    /**
     * The name of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String name;

    /**
     * The last name of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String lastName;

    /**
     * The address of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String address;

    /**
     * The cellphone of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String cellphone;

    /**
     * The city name of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String cityName;

    /**
     * Receive a @see {@link CandidateEntity} and convert it into a CandidateResponse.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param candidate The @see {@link CandidateEntity} to be converted.
     */
    public CandidateResponse(CandidateEntity candidate) {
        this.name = candidate.getName();
        this.lastName = candidate.getLastName();
        this.address = candidate.getAddress();
        this.cellphone = candidate.getCellphone();
        this.cityName = candidate.getCityName();
    }

    public String getName() { return name; }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getCellphone() { return cellphone; }
    
    public String getCityName() { return cityName; }

}
